import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    DatagramSocket dSocket;
    DatagramPacket inPacket;
    DatagramPacket outPacket;

    byte[] bufferIn, bufferOut;
    String messageIn, messageOut;

    int serverPort;

    InetAddress clientAddress;
    int clientPort;

    public static final String BLUE = "\u001B[34m";
    public static final String RESET = "\u001B[0m";

    public Server(int serverPort) {
        this.serverPort = serverPort;
        try {
            DatagramSocket dSocket = new DatagramSocket(serverPort);
            System.out.println(BLUE + "- SERVER IN ASCOLTO SULLA PORTA " + serverPort + RESET);
        } catch (BindException ex) {
            System.out.println(BLUE + "ERRORE: SERVER NON IN ASCOLTO PER PORTA OCCUPATA" + RESET);
        } catch (IllegalArgumentException ex) {
            System.out.println(BLUE + "ERRORE: SERVER NON IN ASCOLTO PER NUMERO DI PORTA NON VALIDO" + RESET);
        } catch (IOException ex) {
            System.err.println(BLUE + "ERRORE DEL SERVER NELLA FASE DI BINDING" + RESET);
        }
    }

    public void ricevi(){
        bufferIn = new byte[256];
        inPacket = new DatagramPacket(bufferIn, bufferIn.length);

        try {
            dSocket.receive(inPacket);
        } catch (IOException e) {
            System.out.println(BLUE + "ERRORE: MESSAGGIO NON RICEVUTO" + RESET);
        }

        clientAddress = inPacket.getAddress();
        clientPort = inPacket.getPort();

        System.out.println(BLUE + "- IL MESSAGGIO RICEVUTO DAL CLIENT " + clientAddress + "DALLA PORTA " + clientPort + " E': " + messageIn + RESET);
    }

    public void manda(){
        messageOut = BLUE + "Ciao Client, ho ricevuto il tuo messaggio!" + RESET;
        bufferOut = messageOut.getBytes();
        outPacket = new DatagramPacket(bufferOut, bufferOut.length, clientAddress, clientPort);

        try {
            dSocket.send(outPacket);
        } catch (IOException e) {
            System.out.println(BLUE + "ERRORE: MESSAGGIO NON SPEDITO" + RESET);
        }

    }

    public void termina() {
        dSocket.close();
        System.out.println(BLUE + "- CHIUSURA SERVER" + RESET);
    }

}