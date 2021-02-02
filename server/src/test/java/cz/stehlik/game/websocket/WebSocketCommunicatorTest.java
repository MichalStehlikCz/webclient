package cz.stehlik.game.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.stehlik.game.game.ClientMessageWantGame;
import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
final class WebSocketCommunicatorTest {

  private final int port;
  private final ObjectMapper jsonMapper;

  WebSocketCommunicatorTest(@LocalServerPort int port, ObjectMapper jsonMapper) {
    this.port = port;
    this.jsonMapper = jsonMapper;
  }

  @Test
  void createGameTest() throws JsonProcessingException {
    WebSocketClient client = new ReactorNettyWebSocketClient();
    var wantGameMessage = jsonMapper.writeValueAsString(new ClientMessageWantGame());
    var client1Messages = new ArrayList<String>(4);
    var session1 = client.execute(
        URI.create("ws://localhost:" + port + "/game"),
        session -> session.send(
            Mono.just(session.textMessage(wantGameMessage)))
            .thenMany(session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .doOnNext(client1Messages::add)
                .log())
            .then())
        .block(Duration.ofSeconds(10L));
  }
}