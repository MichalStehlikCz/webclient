package cz.stehlik.game.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.errorprone.annotations.Immutable;
import cz.stehlik.game.game.ClientCommandProcessor;
import cz.stehlik.game.game.GameSpringConfiguration;
import cz.stehlik.game.game.PlayerRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

@Immutable
@Configuration
@Import({JacksonAutoConfiguration.class, GameSpringConfiguration.class})
public class GameWebSocketSpringConfiguration {

  @Bean
  public WebSocketCommunicator webSocketCommunicator(ObjectMapper jsonMapper,
      PlayerRepository playerRepository, ClientCommandProcessor commandProcessor) {
    return new WebSocketCommunicator(jsonMapper, playerRepository, commandProcessor);
  }

  @Bean
  public HandlerMapping webSocketHandlerMapping(WebSocketCommunicator webSocketHandler) {
    Map<String, WebSocketHandler> map = new HashMap<>(1);
    map.put("/game", webSocketHandler);
    SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
    handlerMapping.setOrder(1);
    handlerMapping.setUrlMap(map);
    return handlerMapping;
  }
}
