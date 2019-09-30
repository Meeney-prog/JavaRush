package com.javarush.task.task30.task3008.client;

public class ClientGuiController extends Client {
    private ClientGuiModel model = new ClientGuiModel();
    private ClientGuiView view = new ClientGuiView(this);

    @Override
    protected SocketThread getSocketThread() {
        return new GuiSocketThread();
    }



    @Override
    public void run() {
        getSocketThread().run();
    }

    @Override
    public String getServerAddress() {
        return view.getServerAddress();
    }

    @Override
    public int getServerPort() {
        return view.getServerPort();
    }

    @Override
    public String getUserName() {
        return view.getUserName();
    }

    public ClientGuiModel getModel() {
        return model;
    }

    public class GuiSocketThread extends SocketThread {
        @Override
        public void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            model.setNewMessage(message);
            view.refreshMessages();
        }

        @Override
        public void informAboutAddingNewUser(String userName) {
            super.informAboutAddingNewUser(userName);
            model.addUser(userName);
            view.refreshUsers();
        }

        @Override
        public void informAboutDeletingNewUser(String userName) {
            super.informAboutDeletingNewUser(userName);
            model.deleteUser(userName);
            view.refreshUsers();
        }

        @Override
        public void notifyConnectionStatusChanged(boolean clientConnected) {
            super.notifyConnectionStatusChanged(clientConnected);
            view.notifyConnectionStatusChanged(clientConnected);
        }


    }

    public static void main(String[] args) {
        new ClientGuiController().run();
    }

}
