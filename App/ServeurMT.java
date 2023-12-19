import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServeurMT  extends Thread {
    int nb=0;
    public static void main(String[] args) {
        new ServeurMT().start();
        System.out.println("suite de l'pplication");
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("DEMARRAGE DU SERVEUR");
            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("Connexion du client " + nb++);
                new Conversation(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class Conversation extends Thread {
        Socket socket;

        public Conversation(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                InputStream is = this.socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bw = new BufferedReader(isr);

                OutputStream os = this.socket.getOutputStream();
                //ObjectOutputStream oos = new ObjectOutputStream(os);
                PrintWriter pw = new PrintWriter(os);
                while(true){
                    String req = bw.readLine();
                    System.out.println("Message du client " + req);
                    pw.write("Le serveur a bien reçu le message" );
                }
            } catch (Exception e) {
                System.out.println("Exception coté serveur " + e.getMessage());
            }
        }
    }
}
