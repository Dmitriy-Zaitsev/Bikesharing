package by.epam.bikesharing.command.factory;

import by.epam.bikesharing.SessionRequestContent;
import by.epam.bikesharing.command.ActionCommand;
import by.epam.bikesharing.command.EmptyCommand;
import by.epam.bikesharing.command.client.CommandEnum;
import by.epam.bikesharing.resource.MessageManager;

public class ActionFactory {

    public ActionCommand defineCommand(SessionRequestContent request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getRequestParameters().get("command")[0];
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}