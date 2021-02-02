package cz.stehlik.game.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.stehlik.game.game.ClientCommandProcessor;
import cz.stehlik.game.game.ClientMessage;
import cz.stehlik.game.game.ConnectedPlayer;
import cz.stehlik.game.game.Player;
import cz.stehlik.game.game.PlayerRepository;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

public class WebSocketCommunicator implements WebSocketHandler {

  private final ObjectMapper jsonMapper;
  private final PlayerRepository playerRepository;
  private final ClientCommandProcessor commandProcessor;
  private final Map<String, ConnectedPlayer> connectedPlayers = new ConcurrentHashMap<>(5);

  WebSocketCommunicator(ObjectMapper jsonMapper, PlayerRepository playerRepository,
      ClientCommandProcessor commandProcessor) {
    this.jsonMapper = Objects.requireNonNull(jsonMapper);
    this.playerRepository = Objects.requireNonNull(playerRepository);
    this.commandProcessor = Objects.requireNonNull(commandProcessor);
  }

  private void processMessage(Player player, WebSocketMessage rawMessage) {
    var message = rawMessage.getPayloadAsText();
    ClientMessage command;
    try {
      command = jsonMapper.readValue(message, ClientMessage.class);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Unable to deserialize message " + message, e);
    }
    commandProcessor.processCommand(player, command);
  }

  private void disposePlayer(ConnectedPlayer connectedPlayer) {
    connectedPlayers.remove(connectedPlayer.getId(), connectedPlayer);
  }

  private void registerPlayer(Player player, FluxSink<String> sink) {
    var connectedPlayer = new WebSocketPlayer(player, jsonMapper, sink);
    var old = connectedPlayers.put(player.getId(), new WebSocketPlayer(player, jsonMapper, sink));
    sink.onDispose(() -> disposePlayer(connectedPlayer));
    if (old != null) {
      old.disconnect("Duplicate connection detected, disconnecting");
    }
  }

  @Override
  public Mono<Void> handle(WebSocketSession session) {
    var player = playerRepository.getTemporary();
    Mono<Void> input = session.receive()
        .doOnNext(message -> processMessage(player, message))
        .then();
    Flux<String> source = Flux.create(sink -> registerPlayer(player, sink));
    var output = session.send(source.map(session::textMessage));
    return Mono.zip(input, output).then();
  }

  @Override
  public String toString() {
    return "WebSocketCommunicator{"
        + "playerRepository=" + playerRepository
        + ", commandProcessor=" + commandProcessor
        + ", connectedPlayers=" + connectedPlayers
        + '}';
  }
}
