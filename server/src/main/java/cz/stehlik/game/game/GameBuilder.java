package cz.stehlik.game.game;

import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;

public class GameBuilder {

  private static final AtomicInteger GAME_ID_GENERATOR = new AtomicInteger(0);

  private volatile @Nullable String gameId;
  private volatile @Nullable Player player1;
  private volatile @Nullable Player player2;

  /**
   * Value of field gameId.
   *
   * @return value of field gameId
   */
  public @Nullable String getGameId() {
    return gameId;
  }

  /**
   * Set value of field gameId.
   *
   * @param gameId the new value to be set
   */
  @SuppressWarnings("NullableProblems")
  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  /**
   * Value of field player1.
   *
   * @return value of field player1
   */
  public @Nullable Player getPlayer1() {
    return player1;
  }

  /**
   * Set value of field player1.
   *
   * @param newPlayer1 the new value to be set
   */
  public synchronized void setPlayer1(@Nullable Player newPlayer1) {
    if ((newPlayer1 != null) && newPlayer1.equals(player2)) {
      throw new IllegalArgumentException("Cannot add player " + newPlayer1
          + " to game - he is already present");
    }
    this.player1 = newPlayer1;
  }

  /**
   * Value of field player2.
   *
   * @return value of field player2
   */
  public @Nullable Player getPlayer2() {
    return player2;
  }

  /**
   * Set value of field player2.
   *
   * @param newPlayer2 the new value to be set
   */
  public synchronized void setPlayer2(@Nullable Player newPlayer2) {
    if ((newPlayer2 != null) && newPlayer2.equals(player1)) {
      throw new IllegalArgumentException("Cannot add player " + newPlayer2
          + " to game - he is already present");
    }
    this.player2 = newPlayer2;
  }

  /**
   * Add player. Sets player1 if empty, player2 if player1 is not null and player2 is null, or
   * throws exception when both players are used.
   *
   * @param player a player to be added
   */
  public synchronized boolean addPlayer(Player player) {
    if (player.equals(player1) || player.equals(player2)) {
      // if player to be added is already in game, do nothing
      return isReady();
    }
    if (player1 == null) {
      setPlayer1(player);
      return player2 != null;
    }
    if (player2 == null) {
      setPlayer2(player);
      return player1 != null;
    }
    throw new IllegalStateException("Cannot add player to game that is already full");
  }

  public boolean isReady() {
    return (player1 != null) && (player2!= null);
  }

  public Game build() {
    if (gameId == null) {
      gameId = "ANONYMOUS$" + GAME_ID_GENERATOR.incrementAndGet();
    }
    if (player1 == null) {
      throw new IllegalStateException("Cannot start game - player1 not specified");
    }
    if (player2 == null) {
      throw new IllegalStateException("Cannot start game - player1 not specified");
    }
    return new Game(gameId, player1, player2);
  }

  @Override
  public String toString() {
    return "GameBuilder{"
        + "player1=" + player1
        + ", player2=" + player2
        + '}';
  }
}
