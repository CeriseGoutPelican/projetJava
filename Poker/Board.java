/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import simulateurzytho.Log;

/**
 * Classe qui gère le board
 * @author hugos
 */
public class Board {
    /**
     * Board contenant 5 cartes à la fin de la distribution des cartes
     */
    private Carte[] board = new Carte[5];
    
    /**
     * Cartes brulées contenant les cartes brulées entre chaque distribution
     * sur le board (flop, turn, river)
     */
    private Carte[] cartesBrulees = new Carte[3];

    /**
     * Constructeur
     */
    public Board(){
    }

    /**
     * Setter pour les cartes du board
     * @param carte 
     *  correspond à une carte
     * @param numCarte
     *  numero de la carte
     */
    protected void setCarteBoard(Carte carte, int numCarte){
            this.board[numCarte] = carte;
    }

    /**
     * Getter pour les cartes du board
     * @param numCarte
     *  numero de la carte
     * @return la carte présente sur le board
     * correspondant à son numéro
     */
    protected Carte getCarteBoard(int numCarte){
            return this.board[numCarte];
    }

    /**
     * Setter cartes brulées
     * @param carte
     * correspond à une carte
     * @param numCarte
     *  numéro de la carte
     */
    protected void setCarteBrulee(Carte carte, int numCarte){
            this.cartesBrulees[numCarte] = carte;
    }

    /**
     * Getter cartes brulées
     * @param numCarte
     *  numero de la carte  
     * @return la carte brulée en fonction de son numéro
     */
    protected Carte getCarteBrulee(int numCarte){
            return this.cartesBrulees[numCarte];
    }

    /**
     * Donne la taille du board
     * @return nombre de carte sur le board
     */
    protected int tailleBoard(){
            return board.length;
    }

    /**
     * Affiche le board
     */
    protected void afficherBoard(){
            new Log("Le board contient les cartes suivantes :");
            for(int i =0; i<board.length;i++){
                    new Log(i+1 + ": " + getCarteBoard(i).afficherCarte());
            }
            new Log("\n");
    }

    /**
     * Affiche les cartes brulées
     */
    protected void afficheCartesBrulees(){
            new Log("Les cartes brulées sont :");
            for(int i =0; i<cartesBrulees.length;i++){
                    new Log(i+1 + ": " + getCarteBrulee(i).afficherCarte());
            }
            new Log("\n");
    }
 
}
