import java.io.Serializable;

public class Echange implements Serializable {
    private int num;
    private int joueur;

    public Echange(int num, int joueur) {
        this.num=num;
        this.joueur=joueur;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getJoueur() {
        return joueur;
    }

    public void setJoueur(int joueur) {
        this.joueur = joueur;
    }

    @Override
    public String toString() {
        return "Echange{" +
                "num=" + num +
                ", joueur=" + joueur +
                '}';
    }
}
