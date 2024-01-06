package com.company;

import java.io.Serializable;

public class Joueur implements Serializable {
    private String login;
    private int score;
    private int statut;

    public Joueur(String login, int score, int statut) {
        this.login = login;
        this.score = score;
        this.statut = statut;
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
