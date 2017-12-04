import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.Scanner;

public class BlackjackServer {
    private static boolean RUNNING = true;
    private static BlackjackServer instance;
    private static Server server;

    private BlackjackServer(Listener listener) {
        if (!startServer(listener)) {
            RUNNING = false;
        }
    }

    private BlackjackServer() {
        if (!startServer(new BlackjackListener())) {
            RUNNING = false;
        }
    }

    static BlackjackServer getInstance() {
        Log.set(Log.LEVEL_INFO);
        if (instance == null) {
            instance = new BlackjackServer();
            return instance;
        }
        return instance;
    }

    static BlackjackServer getInstance(Boolean debug) {
        if (debug) {
            Log.set(Log.LEVEL_DEBUG);
        } else {
            Log.set(Log.LEVEL_INFO);
        }
        return getInstance();
    }

    public static void main(String args[]) {
        new BlackjackServer();

        Scanner scanner = new Scanner(System.in);
        String help = "Available commands are:\r\n" +
                "connected - display the number of connected user\r\n" +
                "stop - shutdown the server\r\n" +
                "help - display this text";
        while (RUNNING) {
            if (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                switch (command) {
                    case "connected":
                        Log.info("Connected users: " + server.getConnections().length);
                        break;
                    case "stop":
                        server.stop();
                        RUNNING = false;
                        break;
                    case "help":
                        Log.info(help);
                        break;
                    default:
                        Log.info(command + " not found.");
                        Log.info(help);
                }
            }
        }
    }

    private boolean startServer(Listener listener) {
        server = new Server();

        try {
            Log.debug("Binding to port: " + Network.PORT + "... ");
            server.bind(Network.PORT);
            Log.debug("Bound to port" + Network.PORT);
        } catch (IOException e) {
            Log.debug("failed");
            Log.error("Unable to bind to port: " + e.getMessage());
            server.stop();
            return false;
        }
        Network.register(server);
        Log.debug("Adding server listener");
        server.addListener(listener);
        Log.debug("Starting server... ");
        server.start();
        Log.debug("Server started successfully");
        return true;
    }
}
