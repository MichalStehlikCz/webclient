package cz.stehlik.game.game;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@SuppressWarnings("ClassReferencesSubclass")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "COMMAND")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ServerMessageStartGame.class, name = "START_GAME"),
    @JsonSubTypes.Type(value = ServerMessageEndGame.class, name = "END_GAME"),
    @JsonSubTypes.Type(value = ServerMessageDisconnect.class, name = "DISCONNECT")
})
public interface ServerMessage {

  /**
   * Command being sent via this message.
   *
   * @return command
   */
  ServerCommand getCommand();
}
