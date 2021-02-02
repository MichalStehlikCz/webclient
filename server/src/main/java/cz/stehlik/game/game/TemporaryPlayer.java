package cz.stehlik.game.game;

import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Temporary player is account created when user tries a game and discarded when game finishes.
 */
final class TemporaryPlayer implements Player {

  private static final AtomicInteger ID_GENERATOR = new AtomicInteger(0);

  /**
   * Generate new temporary player and return it.
   *
   * @return new temporary player
   */
  static Player generate() {
    return new TemporaryPlayer(ID_GENERATOR.incrementAndGet());
  }

  private final String id;

  private TemporaryPlayer(int id) {
    this.id = "TEMP" + id;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public boolean equals(@Nullable Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TemporaryPlayer that = (TemporaryPlayer) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public String toString() {
    return "TemporaryPlayer{"
        + "id='" + id + '\''
        + '}';
  }
}
