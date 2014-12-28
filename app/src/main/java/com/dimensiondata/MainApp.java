package com.dimensiondata;

import com.dimensiondata.console.ConsoleInteractor;
import com.dimensiondata.console.SystemConsoleInteractor;
import com.dimensiondata.dataloader.DataLoaderException;
import com.dimensiondata.dataloader.ServerDataLoader;
import com.dimensiondata.dataloader.ServerDataLoaderImpl;
import com.dimensiondata.hibernate.server.HibernateServerService;
import com.dimensiondata.hibernate.HibernateUtil;
import com.dimensiondata.hibernate.server.ServerService;

import java.io.IOException;

public class MainApp {

    private ServerDataLoader serverDataLoader;
    private CommandExecuter commandExecuter;
    private ConsoleInteractor consoleInteractor;

    public MainApp() {
        ServerService serverService = new HibernateServerService();
        serverDataLoader = new ServerDataLoaderImpl(serverService);
        consoleInteractor = new SystemConsoleInteractor();
        ApplicationInteracter applicationInteracter = new ApplicationInteracter(consoleInteractor);
        commandExecuter = new CommandExecuter(applicationInteracter, serverService);
    }

    public void run(boolean loadData) throws IOException {

        if (loadData) {
            try {
                serverDataLoader.loadServerDataFromDefaultLocation();
            } catch (DataLoaderException e) {
                consoleInteractor.printError(e);
            }
        }

        commandExecuter.execute(Command.HELP);

        Command command;
        do {
            String option = consoleInteractor.readLine();
            command = Command.getCommand(option);
        } while (commandExecuter.execute(command));

    }

    public static void main(String[] args) throws IOException {

        try {
            new MainApp().run(shouldLoadData(args));
        } finally {
            HibernateUtil.shutdown();
        }
	}

    private static boolean shouldLoadData(String[] args) {
        return !(args.length == 1 && args[0].equals("--not-load-data"));
    }
}
