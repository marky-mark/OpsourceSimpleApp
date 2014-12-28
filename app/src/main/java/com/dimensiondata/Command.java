package com.dimensiondata;


public enum Command {
    HELP("help"),
    QUIT("quit"),
    COUNT_SERVERS("countServers"),
    ADD_SERVER("addServer"),
    DELETE_SERVER("deleteServer"),
    EDIT_SERVER("editServer"),
    LIST_SERVER("listServers"),
    NOT_EXIST();

    private String commandText;

    Command() {
    }

    Command(String commandText) {
        this.commandText = commandText;
    }

    public String getCommandText() {
        return commandText;
    }

    public static Command getCommand(String commandString) {
        for (Command command : Command.values()) {
            if (command.getCommandText() != null &&
                command.getCommandText().equals(commandString))
                return command;
        }
        return NOT_EXIST;
    }

}
