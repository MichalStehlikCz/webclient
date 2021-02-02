package cz.stehlik.game.game;

public final class ClientMessageWantGame implements ClientMessage {

  @Override
  public ClientCommand getCommand() {
    return ClientCommand.WANT_GAME;
  }

  @Override
  public String toString() {
    return "ClientMessageWantGame{}";
  }
}
