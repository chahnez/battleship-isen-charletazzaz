/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.ArrayList;

public interface Ship {
    
    /**
     * Fonction qui retourne l'ensemble des positions d'un bateau.
     * @return
     */
    ArrayList<Point> getPositions();
    
    /**
     * Fonction qui retourne la zone d'un bateau.
     * C'est à dire toutes les positions + 1 case autour du bateau.
     * @return
     */
    ArrayList<Point> getArea();
    
}
