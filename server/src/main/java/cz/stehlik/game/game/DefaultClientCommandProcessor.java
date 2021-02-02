package cz.stehlik.game.game;

public final class DefaultClientCommandProcessor implements ClientCommandProcessor {

  private final GameManager gameManager;

  public DefaultClientCommandProcessor(GameManager gameManager) {
    this.gameManager = gameManager;
  }

  @Override
  public void processCommand(Player player, ClientMessage command) {

  }

  @Override
  public String toString() {
    return "DefaultClientCommandProcessor{"
        + "gameManager=" + gameManager
        + '}';
  }
}
