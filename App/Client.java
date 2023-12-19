import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 4000);
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bw = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            while (true) {
                int aleatoire = (int) (100*Math.random());
                pw.write(aleatoire);
                if (bw != null)
                    System.out.println("Réponse du serveur" + bw.readLine());
            }
        } catch (Exception e) {
            System.out.println("Exception coté client  " + e.getMessage());
        }


    }
}
