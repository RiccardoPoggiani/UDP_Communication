import java.io.IOException;
import java.net.*;

public class Client {
    DatagramSocket dSocket;
    DatagramPacket inPacket;
    DatagramPacket outPacket;

    byte[] bufferIn, bufferOut;
    String messageIn, messageOut;

    int clientPort;

    InetAddress serverAddress;
    int serverPort;

    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public Client() {
        try {
            dSocket = new DatagramSocket();
            serverAddress = InetAddress.getLocalHost();
            System.out.println(GREEN + "- INDIRIZZO DEL SERVER TROVATO: " + serverAddress + RESET);
        } catch (UnknownHostException e) {
            System.out.println(GREEN + "ERRORE: INDIRIZZO DEL SERVER NON TROVATO" + RESET);
        } catch (SocketException e) {
            System.out.println(GREEN + "ERRORE: CREAZIONE DATAGRAM SOCKET" + RESET);
        }
    }

    public void manda(int serverPort){
        messageOut = GREEN + "Ciao Server!" + RESET;
        outPacket = new DatagramPacket(messageOut.getBytes(), messageOut.length(), serverAddress, serverPort);

        try {
            dSocket.send(outPacket);
        } catch (IOException e) {
            System.out.println(GREEN + "ERRORE NELLA SPEDIZIONE DEL MESSAGGIO" + RESET);
        }
    }

    public void ricevi(){
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);

        dSocket.receive(inPacket);
    }


}
