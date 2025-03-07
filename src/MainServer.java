/**
 *
 * @author Riccardo Poggiani
 */

public class MainServer {
    public static void main(String[] args) {
        Server s = new Server(2000);
        while(true){
            s.ricevi();
            s.invia();
        }
    }
}
