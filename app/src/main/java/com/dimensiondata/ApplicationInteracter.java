package com.dimensiondata;


import com.dimensiondata.hibernate.server.entity.Server;
import com.dimensiondata.console.ConsoleInteractor;

import java.util.List;

public class ApplicationInteracter {

    private ConsoleInteractor consoleInteractor;

    public ApplicationInteracter(ConsoleInteractor consoleInteractor) {
        this.consoleInteractor = consoleInteractor;
    }

    public void showHelp() {
        consoleInteractor.printNewLine("help - to display this message");
        consoleInteractor.printNewLine("countServers - to display the current number of servers present");
        consoleInteractor.printNewLine("addServer - to display the current number of servers present");
        consoleInteractor.printNewLine("editServer - to change the name of a server identified by id " +
                "(takes 2 additional args - the id and the new name)");
        consoleInteractor.printNewLine("deleteServer - to delete a server (takes one more arg - the id of the server to delete)");
        consoleInteractor.printNewLine("listServers - to display details of all servers servers");
        consoleInteractor.printNewLine("quit - quit");
    }

    public void showCountServer(int count) {
        consoleInteractor.printNewLine(String.format("the number of servers is %s", count));
    }

    public void showServers(List<Server> servers) {
        consoleInteractor.printNewLine("list of servers...");
        consoleInteractor.printNewLine(servers);
    }

    public void showCommandDoesNotExist() {
        consoleInteractor.printError("command does not exist!");
        showHelp();
    }

    public String getId() {
        consoleInteractor.printNewLine("please enter the id:");
        return consoleInteractor.readLine();
    }

    public String getName() {
        consoleInteractor.printNewLine("please enter the name:");
        return consoleInteractor.readLine();
    }

    public void showError(Exception e) {
        consoleInteractor.printError(e.getMessage());
        consoleInteractor.printError(e.getCause());
    }
}
