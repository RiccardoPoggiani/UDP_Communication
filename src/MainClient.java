/**
 *
 * @author Riccardo Poggiani
 */

public class MainClient {
    public static void main(String[] args) {
        Client c = new Client();
        c.invia(2000);
        c.ricevi();
        c.chiudi();
    }
}