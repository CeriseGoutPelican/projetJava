/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import java.util.Arrays;
 
/**
 * Classe qui gère l'évaluation de la main des joueurs
 * @author hugos
 */
public class EvalMain {
    private Carte[] cartesDisponibles = new Carte[7];

    private final static short UN = 1;
    private final static short DEUX = 2;
    private final static short TROIS = 3;
    private final static short QUATRE = 4;
    
    /**
     * Les points dépendent des combinaisons de carte
     * Plus la combinaison est grosse plus le nombre de points qu'elle rapporte est élevé
     */
    public int points;

    /**
     * Constructeur
     */
    public EvalMain(){
    }
    
    /**
     * Getter pour le nombres de points
     * @return le nombre de points
     */
    public int getPoints(){
        return points;        
    }

    /**
     * Ajouter les cartes ensemble
     * pour pouvoir comparer les cartes du joueur et du board
     * avec les différentes combinaisons
     * @param carte
     * @param i 
     */
    protected void ajouterCarte(Carte carte, int i){
        cartesDisponibles[i] = carte;
    }

    /**
     * Get les cartes disponibles
     * celles ajoutés ensembles
     * @param i
     * @return les cartes disponibles
     */
    protected Carte getCarte(int i){
        return cartesDisponibles[i];
    }

    /**
     * Nombre de cartes disponibles
     * @return la taille de cartesDisponibles
     */
    protected int nbCartes(){
        return cartesDisponibles.length;
    }

    /**
     * Trie les cartes par valeur
     */
    protected void trieParValeur(){
            Arrays.sort(cartesDisponibles, new comparateurValeur());
    }

    /**
     * Trie les cartes par couleur
     */
    protected void trieParCouleur(){
            Arrays.sort(cartesDisponibles, new comparateurCouleur());
    }

    /**
     * Trie les cartes par couleur puis par valeur
     */
    protected void trieParCouleurPuisValeur(){
            Arrays.sort(cartesDisponibles, new comparateurCouleur());
            Arrays.sort(cartesDisponibles, new comparateurValeur());
    }

    /**
     * Trie les cartes par valeur puis par couleur
     */
    protected void trieParValeurPuisCouleur(){
            Arrays.sort(cartesDisponibles, new comparateurValeur());
            Arrays.sort(cartesDisponibles, new comparateurCouleur());
    }

    /**
     * Evalue la main du joueur
     * @return la valeur de la main
     */
    protected String evaluerMain(){
        String resultatMain = new String();
        short[] compteurValeur = new short[13];
        short[] compteurCouleur = new short[4];

        // initialisations
        for (int i=0;i<compteurValeur.length;i++){
            compteurValeur[i] =0;
        }

        for (int i=4;i<compteurCouleur.length;i++){
            compteurCouleur[i] = 0;
        }

        // Boucle les cartes triées et les valeurs
        for(int i=0; i<cartesDisponibles.length;i++){
            compteurValeur[ cartesDisponibles[i].getValeur() ]++;
            compteurCouleur[ cartesDisponibles[i].getCouleur() ]++;
        }

        //trie cartes pour evaluation
        this.trieParValeurPuisCouleur();

        // check quinte flush royal
        resultatMain = checkQuinteFlushRoyale(compteurValeur, compteurCouleur);
        points = 10;

        // check quinte flush
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkQuinteFlush(compteurValeur, compteurCouleur);
            points = 9;
        }

        // check carre
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkCarre(compteurValeur);
            points = 8;
        }

        // check full
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkFull(compteurValeur);
            points = 7;
        }

        // check couleur
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkCouleur(compteurValeur, compteurCouleur);
            points = 6;
        }

        // check suite
        if (resultatMain == null || resultatMain.length() == 0){
            this.trieParValeur();
            resultatMain = checkSuite(compteurValeur);
            points = 5;
        }

        // check brelan
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkBrelan(compteurValeur);
            points = 4;
        }

        // check double pair
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkDoublePaire(compteurValeur);
            points = 3;
        }

        // check pair
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkPaire(compteurValeur);
            points = 2;
        }

        // check hauteur
        if (resultatMain == null || resultatMain.length() == 0){
            resultatMain = checkHauteur(compteurValeur);
            points = 1;
        }
        return resultatMain;
    }
    
    /**
     * Check si le joueur à une quinte flush royale
     * @param compteurValeur
     * @param compteurCouleur
     * @return le résultat avec la couleur de la quinte si le joueur en a une
     */
    private String checkQuinteFlushRoyale(short[] compteurValeur, short[] compteurCouleur){
        String resultat = "";

        // check si il y a les 5 valeurs qui se suivent sinon ce n'est pas une royale        
        if ((compteurValeur[9] >= 1 &&       /* 10 */
            compteurValeur[10] >= 1 &&   /* Valet */
            compteurValeur[11] >= 1 &&  /* Dame */
            compteurValeur[12] >= 1 &&  /* Roi */
            compteurValeur[0] >= 1)    /* As */
            && (compteurCouleur[0] > 4 || compteurCouleur[1] > 4 ||
                compteurCouleur[2] > 4 || compteurCouleur[3] > 4)){
                rechercheQuinteFlushRoyale:
                    for (int i=0;i<3;i++){
                        // Check si la 1er carte est un as.
                        // l'as doit être en position 0, 1 or 2
                        if (cartesDisponibles[i].getValeur() == 0){  
                            for (int j=1;j<4-i;j++){
                                if ((cartesDisponibles[i+j].getValeur() == 9 &&
                                    cartesDisponibles[i+j+1].getValeur() == 10 &&
                                    cartesDisponibles[i+j+2].getValeur() == 11 &&
                                    cartesDisponibles[i+j+3].getValeur() == 12)
                                    &&
                                    (cartesDisponibles[i].getCouleur() == cartesDisponibles[i+j].getCouleur() &&
                                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i+j+1].getCouleur() &&
                                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i+j+2].getCouleur() &&
                                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i+j+3].getCouleur())){
                                        //Quinte flush royal trouvé, break et return.
                                        resultat = "Quinte Flush Royale! Couleur : " + Carte.couleurAsString(cartesDisponibles[i].getCouleur());
                                        break rechercheQuinteFlushRoyale;                             
                                }
                            }
                        }                              
                    }
        }              
        return resultat;
    }

    /** 
     * Check si le joueur à une quinte flush 
     * ce qui correspond à 5 cartes consecutives de la même couleur.
     * @param compteurValeur
     * @param compteurCouleur
     * @return le résultat avec la couleur de la quinte et la carte la plus haute si le joueur en a une
     */
    private String checkQuinteFlush(short[] compteurValeur, short[] compteurCouleur){
        String resultat = "";

        if (compteurCouleur[0] > 4 || compteurCouleur[1] > 4 ||
            compteurCouleur[2] > 4 || compteurCouleur[3] > 4){
                for (int i=cartesDisponibles.length-1;i>3;i--){
                    if ((cartesDisponibles[i].getValeur()-UN == cartesDisponibles[i-UN].getValeur() &&
                        cartesDisponibles[i].getValeur()-DEUX == cartesDisponibles[i-DEUX].getValeur() &&
                        cartesDisponibles[i].getValeur()-TROIS == cartesDisponibles[i-TROIS].getValeur() &&
                        cartesDisponibles[i].getValeur()-QUATRE == cartesDisponibles[i-QUATRE].getValeur())
                        &&
                        (cartesDisponibles[i].getCouleur() == cartesDisponibles[i-UN].getCouleur() &&
                        cartesDisponibles[i].getCouleur() == cartesDisponibles[i-DEUX].getCouleur() &&
                        cartesDisponibles[i].getCouleur() == cartesDisponibles[i-TROIS].getCouleur() &&
                        cartesDisponibles[i].getCouleur() == cartesDisponibles[i-QUATRE].getCouleur())){
                            // Found royal flush, break and return.
                            resultat = "Quinte Flush! Couleur : " + Carte.valeurAsString(cartesDisponibles[i].getValeur()) + " hauteur de " + Carte.couleurAsString(cartesDisponibles[i].getCouleur());
                            break;
                    }
                }
        }
        return resultat;
    }

    /**
     * Check si le joueur à un carré
     * ce sont 4 cartes de même valeur : 2-2-2-2, 3-3-3-3, etc...
     * @param compteurValeur
     * @return le résultat avec la valeur des cartes qui forme le carré si le joueur en a un
     */
    private String checkCarre(short[] compteurValeur){
        String resultat = "";

        for (int i=0;i<compteurValeur.length;i++){
            if (compteurValeur[i] == QUATRE){
                resultat = "Carré de " + Carte.valeurAsString(i);
                break;
            }
        }      
        return resultat;
    }


    /**
     * Check si le joueur à un full
     * C'est 3 cartes de même valeur et 2 cartes d'une même autre valeur 
     * EX: V-V-V-3-3
     * @param compteurValeur
     * @return le résultat avec la valeur des cartes du brelan et de celle de la paire
     * si le joueur a un full
     */
    private String checkFull(short[] compteurValeur){
        String resultat = "";
        short valeurBrelan = -1;
        short valeurPaire = -1;

        for (int i=compteurValeur.length;i>0;i--){
            if ((valeurBrelan < (short)0) || (valeurPaire < (short)0)){
                if ((compteurValeur[i-UN]) > 2){
                    valeurBrelan = (short) (i-UN);                                     
                }
                else if ((compteurValeur[i-UN]) > 1){
                    valeurPaire = (short)(i-UN);
                }
            }
            else
            {
                break;
            }
        }

        if ((valeurBrelan >= (short)0) && (valeurPaire >= (short)0)){
            resultat = "Full avec brelan de : " + Carte.valeurAsString(valeurBrelan) + " et paire de " + Carte.valeurAsString(valeurPaire);
        }

        return resultat;
    }

    /**
     * Check si le joueur a une couleur
     * c'est à dire 5 cartes de même couleur
     * @param compteurValeur
     * @param compteurCouleur
     * @return le résultat avec la carte la plus haute de la couleur si le joueur en a une
     */
    private String checkCouleur(short[] compteurValeur, short[] compteurCouleur){
        String resultat = "";

        // Vérifiez qu'au moins 1 couleur a 5 cartes ou plus.
        if (compteurCouleur[0] > 4 || compteurCouleur[1] > 4 ||
            compteurCouleur[2] > 4 || compteurCouleur[3] > 4){
            for (int i=cartesDisponibles.length-1;i>3;i--){
                if (cartesDisponibles[i].getCouleur() == cartesDisponibles[i-UN].getCouleur() &&
                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i-DEUX].getCouleur() &&
                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i-TROIS].getCouleur() &&
                    cartesDisponibles[i].getCouleur() == cartesDisponibles[i-QUATRE].getCouleur()){
                        //Couleur trouvé, break et return.
                        resultat = "Couleur! Hauteur : " + Carte.valeurAsString(cartesDisponibles[i].getValeur()) + " de " + Carte.couleurAsString(cartesDisponibles[i].getCouleur());
                        break;
                }
            }                      
        }
        return resultat;
    }

    /**
     * Check si le joueur a une suite
     * C'est 5 cartes consécutives sans prendre en compte la couleur
     * @param compteurValeur
     * @return le resultat avec la carte la plus haute de la suite si le joueur en a une
     */
    private String checkSuite(short[] compteurValeur){
        String resultat = "";

        //Boucle le tableau de valeurs pour vérifier 5 rangs consécutifs
        //Indice ayant une valeur supérieure à zéro
        for (int i=compteurValeur.length;i>4;i--){
            if ((compteurValeur[i-1] > 0) &&
                (compteurValeur[i-2] > 0) &&
                (compteurValeur[i-3] > 0) &&
                (compteurValeur[i-4] > 0) &&
                (compteurValeur[i-5] > 0)){
                    resultat = "Suite avec " + Carte.valeurAsString(i-1) + " carte la plus haute";
                    break;
            }
        }
        return resultat;
    }

    /**
     * Check si le joueur a une brelan
     * c'est 3 cartes de même valeur
     * @param compteurValeur
     * @return le résultat avec la valeur du brelan si le joueur en a un
     */
    private String checkBrelan(short[] compteurValeur){
        String resultat = "";

        // Boucle le tableau de valeurs pour check les 5 consecutifs
        // indexé avec une valeur plus grande que 0
        for (int i=compteurValeur.length;i>0;i--){
            if (compteurValeur[i-1] > 2){
                resultat = "Brelan de " + Carte.valeurAsString(i-1);
                break;
            }
        }
        return resultat;
    }

    /**
     * Check si le joueur a deux paires
     * C'est à dire 2 fois deux cartes de même valeur entre elles EX: 2-2-6-6
     * @param compteurValeur
     * @return le résultat avec la valeur de chaque paire
     * en commencant par la plus haute si le joueur en a
     */
    private String checkDoublePaire(short[] compteurValeur){
        String resultat = "";
        short valeurPremierePaire = -1;
        short valeurSecondePaire = -1;

        for (int i=compteurValeur.length;i>0;i--){
            if ((valeurPremierePaire < (short)0) || (valeurSecondePaire < (short)0)){                        
                if (((compteurValeur[i-UN]) > 1) && (valeurPremierePaire < (short)0)){
                    valeurPremierePaire = (short) (i-UN);                                       
                }
                else if ((compteurValeur[i-UN]) > 1){
                    valeurSecondePaire = (short)(i-UN);
                }
            }
            else
            {
                // Paire de 2 trouvé, break boucle.
                break;  
            }
        }

        if ((valeurPremierePaire >= (short)0) && (valeurSecondePaire >= (short)0)){
            if (valeurSecondePaire == (short)0){
                //Les as servent de premier rang mais sont en bas du tableau des rangs.
                // échange des places pour que les as apparaissent en premier comme la paire la plus haute
                resultat = "Double Paire: " + Carte.valeurAsString(valeurSecondePaire) + " et " + Carte.valeurAsString(valeurPremierePaire);
            }
            else
            {
                resultat = "Double Paire: " + Carte.valeurAsString(valeurPremierePaire) + " et " + Carte.valeurAsString(valeurSecondePaire);
            }                      
        }

        return resultat;
    }
 
    /**
     * Check si le joueur a une paire
     * ce qui correspond à deux cartes de même valeurs
     * @param compteurValeur
     * @return le résultat avec la valeur de la paire si le joueur en a une
     */
    private String checkPaire(short[] compteurValeur){
        String resultat = "";

        for (int i=compteurValeur.length;i>0;i--){
            if((compteurValeur[i-UN]) > 1){
                resultat = "Une Paire : " + Carte.valeurAsString(i-UN);       
                break;
            }
        }
        return resultat;
    }

    // 
    /**
     * Check la hauteur du joueur
     * La hauteur correspond à la carte la plus haute des 7 cartes (main + board)
     * @param compteurValeur
     * @return le résultat avec la carte la plus haute du joueur
     * si le joueur a aucune combinaison
     */
    private String checkHauteur(short[] compteurValeur){
        String resultat = "";

        for (int i=compteurValeur.length;i>0;i--){
            if((compteurValeur[i-UN]) > 0){
                resultat = "Hauteur : " + Carte.valeurAsString(i-UN);
                break;
            }
        }
        return resultat;
    }
      
}
