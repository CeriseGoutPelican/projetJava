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
    private Carte[] board = new Carte[5];
    private Carte[] cartesBrulees = new Carte[3];

    /**
     * Constructeur
     */
    public Board(){
    }

    /**
     * Setter pour les cartes du board
     * @param carte
     * @param numCarte
     */
    protected void setCarteBoard(Carte carte, int numCarte){
            this.board[numCarte] = carte;
    }

    /**
     * Getter pour les cartes du board
     * @param numCarte
     * @return
     */
    protected Carte getCarteBoard(int numCarte){
            return this.board[numCarte];
    }

    /**
     * Setter cartes brulées
     * @param carte
     * @param numCarte
     */
    protected void setCarteBrulee(Carte carte, int numCarte){
            this.cartesBrulees[numCarte] = carte;
    }

    /**
     * Getter cartes brulées
     * @param cardNum
     * @return
     */
    protected Carte getCarteBrulee(int cardNum){
            return this.cartesBrulees[cardNum];
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
