/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import java.util.*;

/**
 * Classe qui gère les cartes
 * @author hugos
 */
public class Carte {

    /**
     * Représente la valeur de la carte
     */
    private short valeur;
            
    /**
     * Représente la couleur de la carte
     */
    private short couleur;
    
    /**
     * Ensemble des valeurs
     */
    private static String[] valeurs = {"As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Dame", "Roi"};
    
    /**
     * Ensemble des couleurs
     */
    private static String[] couleurs = {"Carreau", "Trefle", "Coeur", "Pique"};

    /**
     * Constructeur d'une carte
     * @param valeur 
     *  valeur de la carte
     * @param couleur 
     *  couleur de la carte
     */
    public Carte(short valeur, short couleur){
            this.valeur = valeur;
            this.couleur = couleur;
    }

    /**
     * Getter couleur de la carte
     * @return couleur de la carte
     */
    public short getCouleur(){
            return couleur;
    }

    /**
     * Getter valeur de la carte
     * @return la valeur de la carte
     */
    public short getValeur(){
            return valeur;
    }

    /**
     * Setter couleur de la carte
     * @param couleur
     *  couleur de la carte
     */
    protected void setCouleur(short couleur){
            this.couleur = couleur;
    }

    /**
     * Setter valeur de la carte
     * @param valeur
     *  valeur de la carte
     */
    protected void setValeur(short valeur){
            this.valeur = valeur;
    }

    /**
     * Valeur de la carte en string
     * @param __valeur
     *    valeur de la carte en int
     * @return la valeur de la carte en string
     */
    public static String valeurAsString(int __valeur){
            return valeurs[__valeur];
    }

    /**
     * Couleur de la carte en string
     * @param __couleur
     *  valeur de la couleur en int
     * @return la valeur de la carte en string
     */
    public static String couleurAsString(int __couleur){
            return couleurs[__couleur];
    }
    
    /**
     * Override de la fonction toString pour afficher les cartes
     * sous la forme (valeur de la carte) de (couleur de la carte) 
     * @return String avec (valeurdelacarte) de (couleurdelacarte)
     */
    @Override
    public String toString(){
            return valeur + " de " + couleur;
    }

     /**
     * Afficher carte en string
     * @return la carte avec sa valeur et sa couleur
     */
    protected String afficherCarte(){
            return valeurs[valeur] + " de " + couleurs[couleur];
    }

    /**
     * Determine si deux cartes sont les mêmes (As de Pique == As de Pique)
     * @param carte1
     *  premiere carte
     * @param carte2
     *  deuxième carte
     * @return True si les cartes sont identique sinon False
     */
    public static boolean memeCarte(Carte carte1, Carte carte2){
            return (carte1.valeur == carte2.valeur && carte1.couleur == carte2.couleur);
    }      

}

/**
 * Classe qui compare les valeurs
 * @author hugos
 */
class comparateurValeur implements Comparator<Object>{
    /**
     * override de la fonction compare pour les valeurs
     * @param carte1
     *  première carte
     * @param carte2
     *  deuxième carte
     * @return la comparaison entre les deux objets
     * @throws ClassCastException 
     */
    @Override
    public int compare(Object carte1, Object carte2) throws ClassCastException{
            // vérifie que les deux objets carte 1 et carte 2 sont passés dedans
            if (!((carte1 instanceof Carte) && (carte2 instanceof Carte))){
                    throw new ClassCastException("Un object Carte est attendu.  Parameter 1 de la classe: " + carte1.getClass()
                                    + " Parametere 2 de la classe: " + carte2.getClass());
            }

            short valeur1 = ((Carte)carte1).getValeur();
            short valeur2 = ((Carte)carte2).getValeur();

            return valeur1 - valeur2;
    }
}

/**
 * Classe qui compare les couleurs
 * @author hugos
 */
class comparateurCouleur implements Comparator<Object>{
    /**
     * override de la fonction compare pour les couleurs
     * @param carte1
     *  première carte
     * @param carte2
     *  deuxième carte
     * @return la comparaison entre les deux objets
     * @throws ClassCastException 
     */
    @Override
    public int compare(Object carte1, Object carte2) throws ClassCastException{
            // vérifie que les deux objets carte1 et carte2 sont passés dedans
            if (!((carte1 instanceof Carte) && (carte2 instanceof Carte))){
                    throw new ClassCastException("Un objet Carte est attendu  Parametere 1 de la classe: " + carte1.getClass()
                                    + " Parametere 2 de la classe: " + carte2.getClass());
            }

            short couleur1 = ((Carte)carte1).getCouleur();
            short couleur2 = ((Carte)carte2).getCouleur();

            return couleur1 - couleur2;
    }
}
