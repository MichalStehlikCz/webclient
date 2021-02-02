package cz.stehlik.game.game;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

@SuppressWarnings("ClassReferencesSubclass")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "COMMAND")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ClientMessageWantGame.class, name = "WANT_GAME")
})
public interface ClientMessage {

  /**
   * Command client sends to server.
   *
   * @return command
   */
  ClientCommand getCommand();
}
