package cz.stehlik.game.game;

public interface ClientCommandProcessor {

  /**
   * Process supplied command.
   *
   * @param player  the player that sent message via its client
   * @param command a command to be processed
   */
  void processCommand(Player player, ClientMessage command);
}
