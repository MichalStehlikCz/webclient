package cz.stehlik.game.game;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

final class GameManager {

  private GameBuilder builder;
  private final Map<String, Game> gameById;

  GameManager() {
    this.builder = new GameBuilder();
    this.gameById = new ConcurrentHashMap<>(6);
  }

  private void addGame(Game game) {
    var old = gameById.putIfAbsent(game.getId(), game);
    if (old != null) {
      // should not happen...
      throw new IllegalStateException(
          "Game with id " + game.getId() + " already registered (" + old + ')');
    }
  }

  public synchronized void wantGame(Player player) {
    if (builder.addPlayer(player)) {
      addGame(builder.build());
      builder = new GameBuilder();
    }
  }

  @Override
  public String toString() {
    return "GameManager{"
        + "builder=" + builder
        + ", gameIds=" + gameById.keySet()
        + '}';
  }
}
