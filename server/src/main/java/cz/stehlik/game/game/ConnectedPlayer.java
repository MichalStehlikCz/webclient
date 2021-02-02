package cz.stehlik.game.game;

public interface ConnectedPlayer extends Player {

  /**
   * Send message to player's client.
   *
   * @param message a message to be delivered to player
   */
  void sendMessage(ServerMessage message);

  /**
   * Terminate player connection. It is recommended to use variant with disconnection message.
   */
  void disconnect();

  /**
   * Disconnect player with specified message. Used when duplicate connection is detected,
   * potentially can be used before server shutdown
   *
   * @param message a message to be displayed to disconnected player
   */
  default void disconnect(String message) {
    sendMessage(new ServerMessageDisconnect(message));
    disconnect();
  }
}
