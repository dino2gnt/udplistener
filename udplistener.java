import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

/**
 * The goal is to listen for a UDP connection from a specific host arg[0] on a specific port [arg1] 
 * and discard all other packets.
 *
 */
public class udplistener {
    private final DatagramSocket socket;

    public udplistener(int port, InetAddress localAddr) throws SocketException {
        socket = new DatagramSocket(port, localAddr);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Syntax: udplistener <local ipaddress to listen on, or 0.0.0.0 for wildcard> <source ipaddress or ANY for all> <destination port>");
            return;
        }

        String srcHost = args[1];
        int port = Integer.parseInt(args[2]);

        try {
            InetAddress localAddr = InetAddress.getByName(args[0]);
            System.out.println("\n==== Listening on " + localAddr + ":" + port + " for packets from " + srcHost + " ====\n");
            udplistener listener = new udplistener(port, localAddr);
            listener.service(srcHost);
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }

    private void service(String srcHost) throws IOException {
        while (true) {

            DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
            socket.receive(request);
            int clientPort = request.getPort();
            InetAddress sourceAddress = request.getAddress();

            String str = new String(
                    request.getData(),
                    request.getOffset(),
                    request.getLength(),
                    StandardCharsets.US_ASCII // 'murica
            );

            if (srcHost.equalsIgnoreCase("ANY")) {
                System.out.println("==== Got packet from " + sourceAddress + ":" + clientPort + " -\n");
                System.out.println("\t" + str + "\n====\n");
            } else {
                InetAddress source = InetAddress.getByName(srcHost);
                if (source.equals(sourceAddress)) {
                    System.out.println("===== Got packet from " + sourceAddress + ":" + clientPort + " -\n");
                    System.out.println("\t" + str + "\n====\n");
                }
            }
        }
    }
}