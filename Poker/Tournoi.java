/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Classe qui gère les tournois
 * @author hugos
 */
public class Tournoi {

    /**
     * Lance un tournoi
     * @throws Exception
     *  Gere les exceptions de getNombreDeJoueurs et getJouerOuObserver
     */
    public static void tournoi() throws Exception{
        //Variables
        int nbrJoueurs;
        int partiesJouees = 0;
         
        //Start message
        System.out.println("Bienvenue dans le tournoi de poker du Zytho");
        System.out.println("Le tournoi te coûtera 1€ d'inscription");
        System.out.println("Cependant, la victoire ici peut te rapporter jusqu'à 6€");
        System.out.println("Il y a des observateur qui consomment une bière le temps du tournoi");
        System.out.println("Et les joueurs qui entre chaque manche peuvent boire ou non");
        String choix = getJouerOuObserver();
        System.out.println("Combien de participant y a t-il ?");
        if (choix.equals("jouer")){
            int nbrJoueursRestant = getNombreDeJoueurs();
            while(nbrJoueursRestant >= 2)
            {
                nbrJoueursRestant = unTour(nbrJoueursRestant);
                //Entre chaque tour le joueur peut acheter et consommer sa boisson
                partiesJouees += 1;
           }
            System.out.println("Il y a eu " + partiesJouees + " parties jouées");
        }
        if (choix.equals("observer")){
            nbrJoueurs = getNombreDeJoueurs();
            Poker.Poker(nbrJoueurs);
        }
    }
    
    /**
     * Lance un tour de jeu et supprime deux joueurs pour le suivant
     * @param nbrJoueurs
     * nombre de joueurs
     * @return nombre de joueurs restant
     */
    public static int unTour(int nbrJoueurs) {
        int nbrJoueursRestant = nbrJoueurs;
        Poker.Poker(nbrJoueursRestant);
        return nbrJoueursRestant = nbrJoueursRestant - 2;
    }
     
    /**
     * Get le choix du joueur
     * @return le choix en String
     * @throws Exception 
     *  Exception si choix différent des deux possibilités
     */
    protected static String getJouerOuObserver() throws Exception{
            
        System.out.println("Ecrit ta réponse (jouer/observer) :");
        Scanner input = new Scanner(System.in);
        String choix = input.nextLine();

        if ((choix.equals("jouer")) || (choix.equals("observer"))){}
        else{
            throw new Exception("Erreur: La décision n'est pas valide");
        }    

        return choix;
    }
    
     /**
     * Get le nombre de joueurs
     * @return le nombre de joueur en integer
     * @throws Exception
     *  Exception si le nombre de joueurs n'est pas compris 
     * entre 1 et le nombre de joueurs max
     */
    protected static int getNombreDeJoueurs() throws Exception{
        int intJoueurs = 0;
        int nbrJoueursMax = 20;
        String entreeUtilisateur = "";

        //Demande le nombre de joueur à l'utilisateur
        System.out.println("Entrer un nombre de joueurs (1-" + nbrJoueursMax + "):");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try { 
               entreeUtilisateur = br.readLine(); 
            } catch (IOException ioe) { 
               System.out.println("Erreur: IO erreur lors de l'essai de la lecture de l'input!"); 
               System.exit(1); 
            }

        //Convertit l'input en integer
        try {
           intJoueurs = Integer.parseInt(entreeUtilisateur);
            } catch (NumberFormatException nfe) {
               System.out.println("Erreur: L'input n'est pas un Integer valide !"); 
               System.exit(1); 
            }

        if ((intJoueurs<1) || (intJoueurs> nbrJoueursMax)){
            throw new Exception("Erreur: Le nombre de joueurs doit être compris entre 1 et la limite de cette partie : " + nbrJoueursMax);
        }       

        return intJoueurs;
    }
}
