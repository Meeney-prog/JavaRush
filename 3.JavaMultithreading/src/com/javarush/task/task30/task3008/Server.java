package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static Map<String, Connection> connectionMap;

    static {
        connectionMap = new ConcurrentHashMap<>();
    }

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
            try {
                pair.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Failed to send message.");
            }
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            super();
            this.socket = socket;
        }

        public void run() {
            ConsoleHelper.writeMessage("Connection with " + socket.getRemoteSocketAddress() + " has been established");
            String userName = "";
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Something went wrong.");
            }
            if (!userName.isEmpty()) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            }
            ConsoleHelper.writeMessage("Connection with server has been closed.");
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                String name = message.getData();
                if (message == null || name == null || name.equals("") || connectionMap.containsKey(name)) {
                    continue;
                }
                connectionMap.put(name, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return name;
            }
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() != MessageType.TEXT) {
                    ConsoleHelper.writeMessage("Message isn't a text.");
                } else {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = ConsoleHelper.readInt();
        ServerSocket serverSocket = new ServerSocket(port);
        ConsoleHelper.writeMessage("Server is up.");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                new Handler(socket).start();
            } catch (Exception e) {
                ConsoleHelper.writeMessage(e.getMessage());
                serverSocket.close();
                break;
            }
        }
    }
}
