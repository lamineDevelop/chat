package com.company;

import java.io.Serializable;
import java.net.Socket;

public class Joueur implements Serializable {
    private String login;
    private int score;
    private int statut;

    private Socket socket;

    public Joueur(String login, int score, int statut,Socket socket) {
        this.login = login;
        this.score = score;
        this.statut = statut;
        this.socket=socket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Joueur() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
}
