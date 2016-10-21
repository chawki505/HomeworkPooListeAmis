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
        System.out.print("\nLa nationaliter :");
        System.out.println("\n1- Algerie\n2- France\n3- Espagne\n4- Canada \n5- Angleterre \n6- Italy \n7- USA \n8- Bresil \n9- Cameroun \n10- Japon");
        System.out.print("\nSelectionner-une de 1 a 10 :  ");
        String nat = getChoixString();
        switch (nat) {
            case "1":
                membre.add(new Personne(nom, "Algerie"));
                System.out.println("\nVos information en ete creer  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "2":
                membre.add(new Personne(nom, "France"));
                System.out.println("\nVos information en ete creer  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "3":
                membre.add(new Personne(nom, "Espagne"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "4":
                membre.add(new Personne(nom, "Canada"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "5":
                membre.add(new Personne(nom, "Angleterre"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "6":
                membre.add(new Personne(nom, "Italy"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "7":
                membre.add(new Personne(nom, "USA"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "8":
                membre.add(new Personne(nom, "Bresil"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "9":
                membre.add(new Personne(nom, "Cemeroun"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            case "10":
                membre.add(new Personne(nom, "Japon"));
                System.out.println("\nCreation de  " + membre.get(nombrePersonne).toString());
                nombrePersonne++;
                break;

            default:
                System.out.println("\nErreur de saisi alors on vous considere comme un Algerien !");
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
            System.out.print("\nVotre choix : ");
            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    creerPersonne();
                    break;
                case 2:
                    if (nombrePersonne > 1) {
                        afficherListPersonne();
                        System.out.print("\nchoix :");
                        choix = getChoixInt();
                        if (choix == 1) {
                            System.out.println("\n---> Desoler vous ne pouvez pas vous suprimer <---");
                        } else {
                            suprimerPersonne(membre.get(choix - 1));
                            System.out.println("\n---> la personne a ete suprimé <---");
                        }

                    } else System.out.println("\n---> Creer dabord une personne avant de suprimé <---");
                    break;
                case 3:
                    if (nombrePersonne > 0)
                        afficherListPersonne();
                    else System.out.println("\n---> il n y a aucune personne pour le moment <---");
                    break;
                case 4:
                    System.out.println("\n----------------> Retour <----------------");
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
            System.out.print("\nVotre choix : ");

            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    if (personne.getNombreAmi() > 0)
                        personne.afficherListAmis();
                    else System.out.println("\n----> vous navez aucun ami , veuillez en ajouter <----");
                    break;
                case 2:
                    if (nombrePersonne < 2)
                        System.out.println("\n----> il n y a pas encore d'autre utilisateur <----");
                    else {
                        afficherListPersonne();
                        System.out.print("\nchoix :");
                        choix = getChoixInt();
                        if ((membre.indexOf(personne) + 1) == choix) {
                            System.out.println("\n----> vous ne pouvez pas vous ajouter a votre propre liste d'ami <----");
                        } else {
                            if (!personne.rechercheAmi(membre.get(choix - 1))) {
                                personne.ajouterAmi(membre.get(choix - 1));
                                System.out.println("\n----> vous avez un nouveau ami <----");
                            } else System.out.println("\n---> vous etes deja ami avec cette personne <---");
                        }
                    }
                    break;
                case 3:
                    if (personne.getNombreAmi() < 1)
                        System.out.println("\n---> vous ne pouvez suprimer quelq'un tant que votre liste d'ami est vide <---");
                    else {
                        personne.afficherListAmis();
                        System.out.print("\nchoix :");
                        choix = getChoixInt();
                        personne.suprimerAmi(personne.getListeAmis().get(choix - 1));
                        System.out.println("\n---> Ami suprimer <----");
                    }
                    break;

                case 4:
                    System.out.println(personne.toString());
                    break;

                case 5:
                    personne.afficherAmiEtranger();
                    break;
                case 6:
                    System.out.println("\n----------------> Retour <----------------");
                    run = false;
                    break;
            }


        }

    }


    void interactionMenuPrincipale() {

        System.out.println("\n======= Bienvenu dans le reseau social ! ====== ");
        creerPersonne();
        boolean run = true;
        while (run) {

            System.out.println("\n======= Menu =======");
            System.out.println("\n1- administration \n2- utilisateur (propre au premier utilisateur creer )\n3- Changer d'utilisateur\n4- quitter");
            System.out.print("\nChoix:");
            int choix = getChoixInt();
            switch (choix) {
                case 1:
                    panelAdmin();
                    break;
                case 2:
                    /*utilisation de votre profile creer en 1er  */

                    panelUser(membre.get(0));

                    break;

                case 3:
                    /*pour choisire une autre personne */
                    if (nombrePersonne >= 2) {
                        afficherListPersonne();
                        System.out.print("choix :");
                        choix = getChoixInt();
                        panelUser(membre.get(choix - 1));
                    } else System.out.println("--> Creer d'autre personne ! <---");
                    break;
                case 4:
                    System.out.println("\n----------------> Retour <----------------");
                    run = false;
                    break;


            }
        }

    }
}
