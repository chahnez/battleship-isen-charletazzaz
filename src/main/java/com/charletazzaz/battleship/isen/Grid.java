/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.ArrayList;

public interface Grid {
    
    /**
     *  Permet de placer aléatoirement 5 bateaux (de taille 1 à 5) sur la grille.
     *  La fonction effectue plusieurs tests afin que le bateau ne sorte pas
     *  de la grille ainsi qu'une vérification des chevauchements.  
     */
    void putRandomShips();

    /**
     * Fonction qui permet de jouer sur une position donnée.
     * @param position position
     */
    void play(Point position);
    
    /**
     * Permet de savoir si une position donnée a déjà été jouée.
     * @param point point
     * @return isPlayed
     */
    boolean isPlayed(Point point);
    
    /**
     * Retourne la liste des positions déja jouées.
     * @return PlayedPositionsList
     */
    ArrayList<Point> getPlayedPositions();
    
    /**
     * Retourne la liste des positions des bateaux sur la grille.
     * @return ShipsPositionsList
     */
    ArrayList<Point> getShipsPositions();
    
    /**
     * Retourne True lorsque tous les bateaux ont été coulés sur cette grille.
     * @return isWin
     */
    boolean isWin();
    
    /**
     * Permet de convertir la grille en un tableau ce qui est utile pour 
     * l'affichage dans la console ainsi que les tests.
     * @return CharArray2D
     */
    char[][] toCharArray();
}
