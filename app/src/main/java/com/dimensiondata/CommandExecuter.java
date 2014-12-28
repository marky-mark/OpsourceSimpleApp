package com.dimensiondata;

import com.dimensiondata.hibernate.HibernateServiceException;
import com.dimensiondata.hibernate.server.ServerService;
import com.dimensiondata.hibernate.server.entity.Server;

public class CommandExecuter {

    private ApplicationInteracter interactor;
    private ServerService serverService;

    public CommandExecuter(ApplicationInteracter interactor, ServerService serverService) {
        this.interactor = interactor;
        this.serverService = serverService;
    }

    public boolean execute(Command command) {

        switch (command) {
            case HELP:
                interactor.showHelp();
                break;
            case QUIT:
                return false;
            case COUNT_SERVERS:
                interactor.showCountServer(serverService.count());
                break;
            case ADD_SERVER:
                createServer();
                break;
            case DELETE_SERVER:
                deleteServer();
                break;
            case EDIT_SERVER:
                editServer();
                break;
            case LIST_SERVER:
                interactor.showServers(serverService.getAll());
                break;
            case NOT_EXIST:
                interactor.showCommandDoesNotExist();
        }

        return true;
    }

    private void editServer() {
        String idToEdit = interactor.getId();
        String nameToEdit = interactor.getName();

        try {
            serverService.update(new Server(idToEdit, nameToEdit));
        } catch (HibernateServiceException e) {
            interactor.showError(e);
        }
    }

    private void deleteServer() {
        String idToDelete = interactor.getId();

        try {
            serverService.delete(idToDelete);
        } catch (HibernateServiceException e) {
            interactor.showError(e);
        }
    }

    private void createServer() {
        String idToCreate = interactor.getId();
        String nameToCreate = interactor.getName();

        try {
            serverService.create(new Server(idToCreate, nameToCreate));
        } catch (HibernateServiceException e) {
            interactor.showError(e);
        }
    }
}
