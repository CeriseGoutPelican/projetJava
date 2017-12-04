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
     *  un client issu de la classe Client
     */
    public Joueur(Client client){
        this.client = client;
    }

    /**
     * Overload de Joueur avec des arguments supp
     * @param client
     *  un client issu de la classe Client
     * @param carte1
     *  une premiere carte issu de la classe Carte
     * @param carte2
     *  une deuxieme carte issu de la classe Carte
     */
    public Joueur(Client client, Carte carte1, Carte carte2){
        cartesCachees[0] = carte1;
        cartesCachees[1] = carte2;
        
        this.client = client;
    }

    /**
     * Retourne le client
     * @return le client
     */
    public Client getClient() {
        return client;
    }
    
    /**
     * Set les cartes cachées du joueur
     * @param carte
     *  une carte
     * @param numCarte
     *  numero de la carte
     */
    protected void setCarte(Carte carte, int numCarte){
       cartesCachees[numCarte] = carte;
    }

    /**
     * Get les cartes cachées
     * @param numCarte
     *  numero de la carte
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
     *  prenom du joueur
     */
    protected void afficherCartesJoueur(String prenomJoueur){
        new Log("Cartes cachées de " + prenomJoueur + " :");
        for (int i=0;i<2;i++){
            new Log(cartesCachees[i].afficherCarte());
        }
        new Log("\n");
    }
}