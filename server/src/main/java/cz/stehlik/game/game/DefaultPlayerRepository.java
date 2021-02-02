package cz.stehlik.game.game;

import java.util.NoSuchElementException;

public final class DefaultPlayerRepository implements PlayerRepository {

  @Override
  public Player getPlayer(String id) {
    throw new NoSuchElementException("Player " + id + " not found");
  }

  @Override
  public Player getTemporary() {
    return TemporaryPlayer.generate();
  }
}
