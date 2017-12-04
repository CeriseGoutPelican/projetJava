/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import java.util.ArrayList;
import java.util.List;
import simulateurzytho.Humain.Client.Client;
import simulateurzytho.Log;
import simulateurzytho.SimulateurZytho;

/**
 * Classe qui gère le jeu en lui même
 * @author hugos
 */

public class Poker {

    /**
     * Programme principale d'un tour de Poker
     * @param nbrJoueurs
     */
    public static void Poker(int nbrJoueurs) {
    
        List<Object> clients = new ArrayList<>();
        clients = SimulateurZytho.listeClients;
    
        // variables
        Paquet paquetPoker = new Paquet();
        int compteurCarte = 0;
        int compteurBrulee = 0;
        int compteurBoard = 0;
        Board board = new Board();
        Classement classement = new Classement();

        // initialisation   
        Joueur[] joueur = new Joueur[nbrJoueurs];
        int[][] points = new int[nbrJoueurs][nbrJoueurs];

        for(int i=0;i<3;i++){
            paquetPoker.melanger();
        }

        // Coupe du paquet
        paquetPoker.coupePaquet();

        // Initialise joueurs
        for (int i=0;i<nbrJoueurs;i++){
            int index = (0 + (int)(Math.random() * clients.size()));
            Client client = (Client) clients.get(index);
            joueur[i] = new Joueur(client);
        }

        // Process principal
        // Distribue cartes cachées aux joueurs
        for (int i=0;i<2;i++){
            for (int j=0;j<nbrJoueurs;j++){
                joueur[j].setCarte(paquetPoker.getCarte(compteurCarte++), i);
            }
        }

        // Start distrib board

        // Brule une carte avant le flop
        board.setCarteBrulee(paquetPoker.getCarte(compteurCarte++), compteurBrulee++);

        //Distribue le flop
        for (int i=0; i<3;i++){
            board.setCarteBoard(paquetPoker.getCarte(compteurCarte++), compteurBoard++);
        }

        // Brule une carte avant le turn
        board.setCarteBrulee(paquetPoker.getCarte(compteurCarte++), compteurBrulee++);

        // Distribue le turn
        board.setCarteBoard(paquetPoker.getCarte(compteurCarte++), compteurBoard++);

        // Brule une carte avant la river
        board.setCarteBrulee(paquetPoker.getCarte(compteurCarte++), compteurBrulee++);

        // Distribue la river
        board.setCarteBoard(paquetPoker.getCarte(compteurCarte++), compteurBoard++);

        //------------------------
        // Fin de la distribution du board
        //------------------------

        new Log("Les cartes sont distribuées et le paquet est complet...\n");

        // afficher le paquet
        paquetPoker.afficherPaquet();

        //afficher le board
        board.afficherBoard();

        // afficher les cartes brulées
        board.afficheCartesBrulees();
        
        // afficher les cartes des joueurs
        new Log("Les cartes des joueur sont les suivantes :\n");
        for (int i=0;i<nbrJoueurs;i++){
            joueur[i].afficherCartesJoueur(joueur[i].getClient().getPrenom());
        }

        //------------------------
        // Debut de l'évaluation des mains
        //------------------------
        for (int i=0;i<nbrJoueurs;i++){
            EvalMain mainAEval = new EvalMain();

            //Remplit avec les cartes de joueur           
        
            for (int j=0;j<joueur[i].quantiteCartesCachees();j++){
                mainAEval.ajouterCarte(joueur[i].getCarte(j),j);
            }

            //Remplit avec les cartes du board
            for (int j=joueur[i].quantiteCartesCachees();j<(joueur[i].quantiteCartesCachees()+board.tailleBoard());j++){
                mainAEval.ajouterCarte(board.getCarteBoard(j-joueur[i].quantiteCartesCachees()),j);
            }
                        
            new Log(joueur[i].getClient().getPrenom() + ", valeur de la main : " + mainAEval.evaluerMain() + " -- Points : " + mainAEval.getPoints() + " points");    
            //Ajoute les points dans un tableau trié par numero de joueur; 0 = joueur 1, 1 = joueur 2, etc 
            points[i][0]=mainAEval.getPoints();
            points[i][1]=i;
        }
        
        classement.trieParScore(points);
        System.out.println(" ----> Les joueurs " + joueur[points[points.length-1][1]].getClient().getPrenom() + " et " + joueur[points[points.length-2][1]].getClient().getPrenom() + " sont out");
        joueur[points[points.length-1][1]].getClient().setPopularite(-2);
        joueur[points[points.length-2][1]].getClient().setPopularite(-2);

        System.out.println(" ----> Vainqueur de ce tour, " + joueur[points[0][1]].getClient().getPrenom());
        joueur[points[0][1]].getClient().setPopularite(5);
    }   
    
}