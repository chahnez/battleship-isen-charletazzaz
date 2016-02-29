/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class ShipTest {
         
    @Test
    public void canGetPositionsTest() {
        Random rd = new Random();
        for (int i = 0; i < 100; i++) {
            ShipImpl ship = new ShipImpl(
                    rd.nextInt(5) + 1, 
                    ShipDirection.values()[rd.nextInt(2)], 
                    new Point(rd.nextInt(11),rd.nextInt(11))
            );
            
            ArrayList<Point> positions = new ArrayList<>();
            for (int j = 0; j < ship.size; j++) {
                if (ship.direction == ShipDirection.HORIZONTAL) {
                    positions.add(new Point(ship.origin.x + j, ship.origin.y));
                }
                else {
                    positions.add(new Point(ship.origin.x, ship.origin.y + j));                    
                }
            }
            
            assertEquals(ship.getPositions(), positions);
        }
    }    
    
    @Test
    public void canGetAreaTest() {
        ShipImpl ship = new ShipImpl(5, ShipDirection.HORIZONTAL, new Point(2,2));
        ArrayList<Point> area = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 3; j++) {
                area.add(new Point(i, j));                
            }
        }
        assertEquals(area, ship.getArea());
    }
    
    
    @Test
    public void canGetToStringRepresentation() {
        Ship ship = new ShipImpl(5, ShipDirection.HORIZONTAL, new Point(4,6));
        assertEquals(ship.toString(), "Ship [5] x: 4 y: 6 HORIZONTAL");
    }
    
}
