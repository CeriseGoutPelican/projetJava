/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

/**
 *
 * @author hugos
 */
import java.util.Random;
import simulateurzytho.Log;
 
/**
 * Classe qui gère le paquet de cartes
 * @author hugos
 */
public class Paquet{
    private Carte[] cartes = new Carte[52];

    /**
     * Constructeur
     */
    public Paquet(){
        int i = 0;
        for (short j=0; j<4; j++){
            for (short k=0; k<13;k++){
                cartes[i++] = new Carte(k, j);   
            }
        }
    }
       
    /**
     * Affiche le paquet entier dans l'ordre
     */
    protected void afficherPaquet(){
        for(int i=0; i<cartes.length;i++){
            new Log(i+1 + ": " + cartes[i].afficherCarte());
        }
        new Log("\n");
    }
       
    /**
     * Trouve la carte dans le paquet de façon linéaire
     * Utilisez cette méthode si le paquet est mélangé
     * @param carte
     * @return l'emplacement de la carte dans le paquet si elle y es
     * sinon retourne -1
     */
    protected int trouverCarte(Carte carte){
        for (int i=0;i<52;i++){
            if (Carte.memeCarte(cartes[i], carte)){
                return i;
            }
        }
        return -1;
    }
       
    /**
     * Get la carte spécifié du paquet
     * @param numCarte
     * @return la carte spécifié par son emplacement dans le paquet
     */
    protected Carte getCarte(int numCarte){
        return cartes[numCarte];
    }

    /**
     * Mélange les cartes
     */
    protected void melanger(){
        int length = cartes.length;
        Random random = new Random();
        for (int i=0;i<length;i++){
            int change = i + random.nextInt(length-i);
            echangerCartes(i, change);
        }
    }

    /**
     * Coupe le paquet
     */
    protected void coupePaquet(){
        Paquet tempPaquet = new Paquet();
        Random random = new Random();
        int numCoupe = random.nextInt(52);
        for (int i=0;i<numCoupe;i++){
            tempPaquet.cartes[i] = this.cartes[52-numCoupe+i];                   
        }
        for (int j=0;j<52-numCoupe;j++){
            tempPaquet.cartes[j+numCoupe] = this.cartes[j];                      
        }
        this.cartes = tempPaquet.cartes;
    }

    /**
     * Echange des cartes entre elles dans le paquet
     * Sert pour mélanger le jeu
     * @param i
     * @param change 
     */
    private void echangerCartes(int i, int change){             
        Carte temp = cartes[i];
        cartes[i] = cartes[change];
        cartes[change] = temp;
    }
}