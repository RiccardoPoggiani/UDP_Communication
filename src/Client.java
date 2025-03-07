import java.io.IOException;
import java.net.*;

/**
 *
 * @author Riccardo Poggiani
 */

public class Client {
    DatagramSocket dSocket;
    DatagramPacket inPacket;
    DatagramPacket outPacket;

    byte[] buffer;
    String message;
    String response;

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

    public void invia(int serverPort){
        message = GREEN + "Ciao Server!" + RESET;
        outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);

        try {
            dSocket.send(outPacket);
            System.out.println(GREEN + "- MESSAGGIO INVIATO" + RESET);
        } catch (IOException e) {
            System.out.println(GREEN + "ERRORE NELL'INVIO DEL MESSAGGIO" + RESET);
        }
    }

    public void ricevi(){
        buffer = new byte[256];
        inPacket = new DatagramPacket(buffer, buffer.length);

        try {
            dSocket.receive(inPacket);
            System.out.println(GREEN + "- MESSAGGIO RICEVUTO" + RESET);
        } catch (IOException e) {
            System.out.println(GREEN + "ERRORE NELLA RICEZIONE DEL MESSAGGIO" + RESET);
        }

        response = new String(inPacket.getData(), 0, inPacket.getLength());

        System.out.println(GREEN + "- IL SERVER HA RISPOSTO: " + response + RESET);
    }

    public void chiudi(){
        dSocket.close();
        System.out.println("- COMUNICAZIONE CHIUSA");
    }
}
