import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chawki on 16/10/16.
 */
public class Facebook {

    private ArrayList<Personne> membre = new ArrayList<>();
    private int nombrePersonne = 0;


    //methode pour la lecture clavier pour les entier
    private int getChoixInt() {
        int choix;
        Scanner sc = new Scanner(System.in);
        choix = sc.nextInt();
        return choix;
    }


    //methode pour la lecture clavier pour les chaine de caractere
    private String getChoixString() {
        String choix;
        Scanner sc = new Scanner(System.in);
        choix = sc.nextLine();
        return choix;
    }

    public int getNombrePersonne() {
        return nombrePersonne;
    }

    public void setNombrePersonne(int nombrePersonne) {
        this.nombrePersonne = nombrePersonne;
    }


    //Affichage de tous les personne avec leur information
    void afficherListPersonne() {
        for (int i = 0; i < membre.size(); i++) {
            System.out.println((i + 1) + ")" + membre.get(i).toString());
        }
    }

    //suprimer la personne et la suprimer aussi dans la liste ami des autre membre
    void suprimerPersonne(Personne personne) {

        for (int i = 0; i < membre.size(); i++) {//boucle pour suprimé la personne
            if (personne.getNom().equals(membre.get(i).getNom())) {
                membre.remove(i);
                nombrePersonne--;
                break;
            }
        }
        for (int i = 0; i < membre.size(); i++) {//boucle pour suprimer la personne de la liste ami des autre membre
            membre.get(i).suprimerAmi(personne);
        }

    }


    //methode pour creer une personne
    private void creerPersonne() {
        System.out.print("Donnez un nom :");
        String nom = getChoixString();
        System.out.print("La nationaliter :");
        System.out.println("\n1- Algerie\n2- France\n3- Espagne\n4- Canada");
        System.out.print("Selectionner-une de 1 a 4 :  ");
        String nat = getChoixString();
        switch (nat) {
            case "1":
            case "Algerie":
            case "algerie":
            case "DZ":
            case "dz":
                membre.add(new Personne(nom, "Algerie"));
                System.out.println("Vos information en ete creer  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;
            case "2":
            case "France":
            case "france":
            case "FR":
            case "fr":
                membre.add(new Personne(nom, "France"));
                System.out.println("Creation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;
            case "3":
            case "Espagne":
            case "espagne":
            case "ES":
            case "es":
                membre.add(new Personne(nom, "Espagne"));
                System.out.println("Creation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "4":
            case "Canada":
            case "canada":
                membre.add(new Personne(nom, "Canada"));
                System.out.println("Creation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            default:
                System.out.println("Erreur de saisi alors on vous considere comme un Algerien !");
                membre.add(new Personne(nom, "Algerie"));
                System.out.println("Creation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;
        }
    }


    //methode priver pour le menu administrateur
    private void panelAdmin() {
        boolean run = true;
        while (run) {
            System.out.println("\n==== Menu Admin ====");
            System.out.println("\n1- creer une personne \n2- Suprimer une personnne \n3- Affichcer tous les utilisateur  \n4- Retour");
            System.out.print("Votre choix : ");
            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    creerPersonne();
                    break;
                case 2:
                    if (nombrePersonne > 0) {
                        afficherListPersonne();
                        System.out.print("choix :");
                        choix = getChoixInt();
                        if (choix != 1) {
                            suprimerPersonne(membre.get(choix - 1));
                            System.out.println("la personne a ete suprimé");
                        } else System.out.println(" Desoler vous ne pouvez pas vous suprimer ");

                    } else System.out.println("creer dabord une personne avant de suprimé");
                    break;
                case 3:
                    if (nombrePersonne > 0)
                        afficherListPersonne();
                    else System.out.println("il n y a aucune personne pour le moment ");
                    break;
                case 4:
                    run = false;
                    break;
            }

        }
    }

    //methode priver pour le menu utilisateur (par defaut c'est le premier utilisateur enrengistrer)
    private void panelUser(Personne personne) {

        boolean run = true;
        while (run) {
            System.out.println("\n==== Menu User (" + personne.getNom() + ") ====");
            System.out.println("\n1- Afficher votre liste ami \n2- Ajouter un ami \n3- Suprmier un ami \n4- Afficher vos information \n5- Afficher vos amis etranger \n6- Retour");
            System.out.print("Votre choix : ");

            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    if (personne.getNombreAmi() > 0)
                        personne.afficherListAmis();
                    else System.out.println("vous navez aucun ami , veuilez en ajouter");
                    break;
                case 2:
                    if (nombrePersonne < 2)
                        System.out.println("il n y a pas encore d'autre utilisateur ");
                    else {
                        afficherListPersonne();
                        System.out.print("choix :");
                        choix = getChoixInt();
                        if (choix == 1) {
                            System.out.println("vous ne pouvez pas vous ajouter sois meme a votre liste d'ami");
                        } else {
                            if (!personne.rechercheAmi(membre.get(choix - 1))) {
                                personne.ajouterAmi(membre.get(choix - 1));
                                System.out.println("vous avez un nouveau ami");
                            } else System.out.println("vous etes deja ami avec cette personne");
                        }
                    }
                    break;
                case 3:
                    if (personne.getNombreAmi() < 1)
                        System.out.println("vous ne pouvez suprimer quelqun tant que votre liste d'ami est vide");
                    else {
                        personne.afficherListAmis();
                        System.out.print("choix :");
                        choix = getChoixInt();
                        personne.suprimerAmi(personne.getListeAmis().get(choix - 1));
                        System.out.println(" ami suprimer");
                    }
                    break;

                case 4:
                    System.out.println(personne.toString());
                    break;

                case 5:
                    personne.afficherAmiEtranger();
                    break;
                case 6:
                    run = false;
                    break;
            }


        }

    }


    void interactionMenuPrincipale() {

        System.out.println("====== Bienvenu dans le reseau ! ===== ");
        creerPersonne();
        boolean run = true;
        while (run) {

            System.out.println("\n====Menu====");
            System.out.println("1- administration \n2- utilisateur \n3- quitter");
            System.out.print("\nChoix:");

            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    panelAdmin();
                    break;
                case 2:

                    //TODO:creer un methode pour basculer entre les utilisateur
                    /*pour choisire la personne */
//                    afficherListPersonne();
//                    System.out.print("choix :");
//                    choix = getChoixInt();
//                    panelUser(membre.get(choix - 1));


                    /*utiliser votre profile*/
                    panelUser(membre.get(0));
                    break;

                case 3:
                    run = false;
                    break;


            }
        }

    }
}
