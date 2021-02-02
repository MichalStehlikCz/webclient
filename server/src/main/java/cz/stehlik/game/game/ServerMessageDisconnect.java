package cz.stehlik.game.game;

public final class ServerMessageDisconnect implements ServerMessage {

  private final String message;

  public ServerMessageDisconnect(String message) {
    this.message = message;
  }

  @Override
  public ServerCommand getCommand() {
    return ServerCommand.DISCONNECT;
  }

  /**
   * Value of field message.
   *
   * @return value of field message
   */
  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "ServerMessageDisconnect{"
        + "message='" + message + '\''
        + '}';
  }
}
