package cz.stehlik.game.game;

public interface PlayerRepository {

  Player getPlayer(String id);

  Player getTemporary();
}
