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
     *
     * @return
     */
    ArrayList<Point> getPositions();
    
    /**
     *
     * @return
     */
    ArrayList<Point> getArea();
    
}
