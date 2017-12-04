/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulateurzytho.Poker;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author hugos
 */
public class Classement {
       
    /**
     * Trie le tableau en fonction du score et non du numéro du joueur
     * @param score
     *  tableau de score 
     */
    public void trieParScore(int score[][]){

        Arrays.sort(score, new Comparator<int[]>() {
            @Override
            public int compare(final int[] entry1, final int[] entry2) {
                final int score = entry1[0];
                final int joueur = entry2[0];
                return Integer.compare(joueur,score);
            }
        });   
    }    
}
