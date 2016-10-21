import java.util.ArrayList;

/**
 * Created by chawki on 16/10/16.
 */
public class Personne {

    private String nom;
    private String nationalitee;
    private ArrayList<Personne> listeAmis = new ArrayList<>();
    private int nombreAmi = 0;

    Personne(String nom, String nationaliter) {
        this.nom = nom;
        this.nationalitee = nationaliter;
    }


    Personne(Personne personne) {
        this.nom = personne.getNom();
        this.nationalitee = personne.getNationaliter();
    }


    public String getNationaliter() {
        return nationalitee;
    }

    public String getNom() {
        return nom;
    }

    public void setNombreAmi(int nombreAmi) {
        this.nombreAmi = nombreAmi;
    }

    public int getNombreAmi() {
        return nombreAmi;
    }

    public ArrayList<Personne> getListeAmis() {
        return listeAmis;
    }

    public String getNationalitee() {
        return nationalitee;
    }


    //methode pour ajouter un ami dans la liste
    void ajouterAmi(Personne personne) {
        listeAmis.add(new Personne(personne));
        nombreAmi++;

    }


    //methode pour suprimer une personne dans la liste dami
    void suprimerAmi(Personne personne) {
        for (int i = 0; i < listeAmis.size(); i++) {

            if (personne.nom.equals(listeAmis.get(i).nom)) {
                listeAmis.remove(i);
                nombreAmi--;
                break;
            }
        }
    }

    //methode pour trouver une personne si elle est dans la liste amis
    boolean rechercheAmi(Personne personne) {
        boolean val = false;
        int indice = 0;
        while (!val && indice < listeAmis.size()) {
            if (personne.getNom().equals(listeAmis.get(indice).getNom()))
                val = true;
            else indice++;
        }

        return val;
    }

    //afficher la liste dami
    void afficherListAmis() {
        for (int i = 0; i < listeAmis.size(); i++) {
            System.out.println((i + 1) + ")" + listeAmis.get(i).toString());
        }
    }

    //methode pour afficher vos amis etranger
    void afficherAmiEtranger() {
        int compteur = 0;
        if (nombreAmi != 0) {
            for (int i = 0; i < listeAmis.size(); i++) {
                if (!listeAmis.get(i).nationalitee.equals(nationalitee)) {
                    System.out.println(listeAmis.get(i).toString());
                    compteur++;
                }
            }
            System.out.println("---> Vous avez " + compteur + " ami(s) etranger <---");
        } else System.out.println("---> Vous n'avez pas encore d'amis <---");
    }

    void afficherAmiMemeNationalitee() {
        int compteur = 0;
        if (nombreAmi != 0) {
            for (int i = 0; i < listeAmis.size(); i++) {
                if (listeAmis.get(i).nationalitee.equals(nationalitee)) {
                    System.out.println(listeAmis.get(i).toString());
                    compteur++;
                }

            }
            System.out.println("---> Vous avez " + compteur + " ami(s) de meme nationaliter  <---");
        } else System.out.println("---> Vous n'avez pas encore d'amis <---");
    }

    @Override
    public String toString() {
        return " Nom : " + nom + " / " + "Nationalitee : " + nationalitee + ", " + nombreAmi + "ami(s)";
    }
}
