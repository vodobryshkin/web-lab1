import network.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server fcgiServer = new Server();
        fcgiServer.listenAndServe();
    }
}
