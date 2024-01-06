package com.company;

import com.sun.corba.se.impl.orbutil.ObjectWriter;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServeurMT  extends Thread {
    static boolean isInscriptionEnCours=true;
    int nb=0;
    List<Joueur> listJoueur= new ArrayList<>();
    static ArrayList<String> coul=new ArrayList<>();
    public static void main(String[] args) {
        choisirCombinaison();
        new ServeurMT().start();
        System.out.println("suite de l'pplication");
    }

    public void run() {
        try {

            ServerSocket serverSocket = new ServerSocket(4000);
            System.out.println("DEMARRAGE DU SERVEUR");
            while (true) {
                Socket s = serverSocket.accept();
                nb++;
                System.out.println("Connexion du client " + nb);
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
                PrintWriter pw = new PrintWriter(os,true);

                ObjectOutputStream oos = new ObjectOutputStream(os);


                while(true){
                    String req = bw.readLine();
                    if(req.contains("##LOGIN##") && isInscriptionEnCours) {
                        String nomClient = req.substring(9,req.length());
                        Joueur  joueur =new Joueur(nomClient,0,1,socket);
                        listJoueur.add(joueur);
                        System.out.println("Le client  " + nomClient + " a rejooint le jeu");
                        pw.println("Vous etes le joueur Num " + nb );
                    }
                    else {
                        if(listJoueur.size()<2) {
                            pw.println("En attente d'autres participants ");
                            System.out.println("En attente d'autres participants");
                        }else {
                            isInscriptionEnCours=false;
                            pw.println("*********** Le jeu commence ***********" );
                            System.out.println("E*********** Le jeu commence ***********");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception cote serveur " + e.getMessage());
            }
        }
    }

    private static void choisirCombinaison() {
        Map<Integer,String> couleurs = new HashMap<>();
        couleurs.put(1,"BLEU");
        couleurs.put(2,"BLANC");
        couleurs.put(3,"ROUGE");
        couleurs.put(4,"JAUNE");
        couleurs.put(5,"GRIS");
        couleurs.put(6,"VERT");
        couleurs.put(7,"ORANGE");
        couleurs.put(8,"JAUNE");
        int c1,c2,c3,c4;
            c1 = (int) (10 * Math.random() % 8 + 1);

        do {
             c2 = (int) (10 * Math.random() % 8 + 1);
        }while (c1==c2);

        do {
            c3 = (int) (10 * Math.random() % 8 + 1);
        }while (c3==c2 || c3==c1);

        do {
            c3 = (int) (10 * Math.random() % 8 + 1);
        }while (c3==c2 || c3==c1);

        do {
            c4 = (int) (10 * Math.random() % 8 + 1);
        }while (c4==c3 || c4==c1 || c4==c2);


        System.out.println("AFFICHAGE DE LA COMBINAISON ");
        System.out.println(couleurs.get(c1));
        System.out.println(couleurs.get(c2));
        System.out.println(couleurs.get(c3));
        System.out.println(couleurs.get(c4));

        coul.add(couleurs.get(c1));
        coul.add(couleurs.get(c2));
        coul.add(couleurs.get(c3));
        coul.add(couleurs.get(c4));

    }

}
