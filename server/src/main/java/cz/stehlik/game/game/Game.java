package cz.stehlik.game.game;

import java.util.Objects;

public final class Game {

  private final String id;
  private final Player player1;
  private final Player player2;

  Game(String id, Player player1, Player player2) {
    this.id = Objects.requireNonNull(id);
    this.player1 = Objects.requireNonNull(player1);
    this.player2 = Objects.requireNonNull(player2);
  }

  /**
   * Value of field id.
   *
   * @return value of field id
   */
  public String getId() {
    return id;
  }

  /**
   * Value of field player1.
   *
   * @return value of field player1
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * Value of field player2.
   *
   * @return value of field player2
   */
  public Player getPlayer2() {
    return player2;
  }

  @Override
  public String toString() {
    return "Game{"
        + "id='" + id + '\''
        + ", player1=" + player1
        + ", player2=" + player2
        + '}';
  }
}
