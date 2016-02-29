/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.ArrayList;

public class ShipImpl implements Ship {    
      
    final int size;
    final ShipDirection direction;
    final Point origin;    
    
    public ShipImpl(int size, ShipDirection direction, Point origin) {
        this.size = size;
        this.direction = direction;
        this.origin = origin;
    }

    @Override
    public ArrayList<Point> getPositions() {
        ArrayList<Point> positions = new ArrayList<>();
        for (int j = 0; j < size; j++) {
            if (direction == ShipDirection.HORIZONTAL) {
                positions.add(new Point(origin.x + j, origin.y));
            }
            else {
                positions.add(new Point(origin.x, origin.y + j));
            }
        }
        return positions;
    }    
    
    @Override
    public ArrayList<Point> getArea(){
        ArrayList<Point> positions = new ArrayList<>();        
        
        for (Point position : getPositions()) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Point p = new Point(position.x + i, position.y + j);  
                    if(!positions.contains(p))
                        positions.add(p);
                }                
            }          
        }
        return positions;
    }

    @Override
    public String toString() {
        return "Ship [" + size + "] x: " + origin.x + " y: " + origin.y + " " + direction;
    }
}
