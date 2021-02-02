package cz.stehlik.game.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

public final class ServerMessageStartGame implements ServerMessage {

  private final int gameId;

  @JsonCreator(mode = Mode.PROPERTIES)
  ServerMessageStartGame(int gameId) {
    this.gameId = gameId;
  }

  @Override
  public ServerCommand getCommand() {
    return ServerCommand.START_GAME;
  }

  /**
   * Value of field id.
   *
   * @return value of field id
   */
  public int getGameId() {
    return gameId;
  }

  @Override
  public String toString() {
    return "ServerMessageStartGame{"
        + "gameId=" + gameId
        + '}';
  }
}
