package cz.stehlik.game.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.stehlik.game.game.ConnectedPlayer;
import cz.stehlik.game.game.Player;
import cz.stehlik.game.game.ServerMessage;
import java.util.Objects;
import reactor.core.publisher.FluxSink;

final class WebSocketPlayer implements ConnectedPlayer {

  private final Player player;
  private final ObjectMapper jsonMapper;
  private final FluxSink<String> sink;

  WebSocketPlayer(Player player,
      ObjectMapper jsonMapper,
      FluxSink<String> sink) {
    this.player = Objects.requireNonNull(player);
    this.jsonMapper = Objects.requireNonNull(jsonMapper);
    this.sink = Objects.requireNonNull(sink);
  }

  @Override
  public void sendMessage(ServerMessage message) {
    try {
      sink.next(jsonMapper.writeValueAsString(message));
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException("Failed to serialize supplied message " + message, e);
    }
  }

  @Override
  public void disconnect() {
    sink.complete();
  }

  @Override
  public String getId() {
    return player.getId();
  }

  @Override
  public String toString() {
    return "WebSocketPlayer{"
        + "player=" + player
        + '}';
  }
}
