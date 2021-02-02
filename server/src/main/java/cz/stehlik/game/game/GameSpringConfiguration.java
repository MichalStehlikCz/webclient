package cz.stehlik.game.game;

import com.google.errorprone.annotations.Immutable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Immutable
@Configuration
public class GameSpringConfiguration {

  @Bean
  public PlayerRepository playerRepository() {
    return new DefaultPlayerRepository();
  }

  @Bean
  public GameManager gameManager() {
    return new GameManager();
  }

  @Bean
  ClientCommandProcessor clientCommandProcessor(GameManager gameManager) {
    return new DefaultClientCommandProcessor(gameManager);
  }
}
