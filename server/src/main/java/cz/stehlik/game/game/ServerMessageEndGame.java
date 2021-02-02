package cz.stehlik.game.game;

import org.checkerframework.checker.nullness.qual.Nullable;

public final class ServerMessageEndGame implements ServerMessage {

  private final int gameId;
  private final @Nullable String message;

  public ServerMessageEndGame(int gameId, @Nullable String message) {
    this.gameId = gameId;
    this.message = message;
  }

  @Override
  public ServerCommand getCommand() {
    return ServerCommand.END_GAME;
  }

  /**
   * Value of field gameId.
   *
   * @return value of field gameId
   */
  public int getGameId() {
    return gameId;
  }

  /**
   * Value of field message.
   *
   * @return value of field message
   */
  public @Nullable String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ServerMessageEndGame{"
        + "gameId=" + gameId
        + ", message='" + message + '\''
        + '}';
  }
}
