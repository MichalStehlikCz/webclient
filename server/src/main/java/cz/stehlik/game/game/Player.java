package cz.stehlik.game.game;

/**
 * Player acts as global identification of player account.
 */
public interface Player {

  /**
   * Player identifier. Might allow reconnection in future, at the moment, it is used to connect
   * game with communication channel
   *
   * @return player id
   */
  String getId();
}
