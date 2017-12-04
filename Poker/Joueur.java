/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import simulateurzytho.Humain.Client.Client;
import simulateurzytho.Log;

/**
 * Classe qui gère les joueurs
 * @author hugos
 */
public class Joueur {
    private final Carte[] cartesCachees = new Carte[2];
    private final Client client;
    
    /**
     * Constructeur Joueur
     * @param client 
     */
    public Joueur(Client client){
        this.client = client;
    }

    /**
     * Overload de Joueur avec des arguments supp
     * @param client
     * @param carte1
     * @param carte2
     */
    public Joueur(Client client, Carte carte1, Carte carte2){
        cartesCachees[0] = carte1;
        cartesCachees[1] = carte2;
        
        this.client = client;
    }

    /**
     * Retourne le client
     * @return 
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * Set les cartes cachées du joueur
     * @param carte
     * @param numCarte
     */
    protected void setCarte(Carte carte, int numCarte){
       cartesCachees[numCarte] = carte;
    }

    /**
     * Get les cartes cachées
     * @param numCarte
     * @return les cartes cachées
     */
    protected Carte getCarte(int numCarte){
        return cartesCachees[numCarte];
    }

    /**
     * Donne la quantité de cartes cachées du joueur
     * @return la taille de cartesCachees
     */
    protected int quantiteCartesCachees(){
        return cartesCachees.length;
    }

    /**
     * Affiche les cartes du joueur
     * @param prenomJoueur
     */
    protected void afficherCartesJoueur(String prenomJoueur){
        new Log("Cartes cachées de " + prenomJoueur + " :");
        for (int i=0;i<2;i++){
            new Log(cartesCachees[i].afficherCarte());
        }
        new Log("\n");
    }
}