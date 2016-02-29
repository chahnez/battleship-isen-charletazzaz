/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {

    @Test
    public void canCreateEmptyGridTest() {
        GridImpl grid = new GridImpl();
        char[][] arrayGrid = grid.toCharArray();                
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(arrayGrid[i][j], '~');
            }
        }
    }
    
    @Test
    public void canPutRandomShipsOnGridTest() {
        GridImpl grid = new GridImpl();
        for (int k = 0; k < 10; k++) {
            grid.putRandomShips();
            char[][] arrayGrid = grid.toCharArray();
            int cpt = 0;
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (arrayGrid[i][j] == 'O') {
                        cpt++;
                    }
                }
            }
            assertEquals(15, cpt);             
        }        
    }

    @Test
    public void canPlayTest() {
        GridImpl grid = new GridImpl();
        Point position = new Point(4, 6);
        grid.play(position);
        assertTrue(grid.isPlayed(position));
    }

    @Test
    public void canPlayMultiplePositionsTest() {
        Grid grid = new GridImpl();
        Random rd = new Random();

        for (int i = 0; i < 10; i++) {
            Point position = new Point(rd.nextInt(10), rd.nextInt(10));
            grid.play(position);
            assertTrue(grid.getPlayedPositions().contains(position));
        }
    }

    @Test
    public void canWinOneShipTest() {
        GridImpl grid = new GridImpl();
        grid.ships.add(new ShipImpl(2, ShipDirection.HORIZONTAL, new Point(4,6)));
        
        assertFalse(grid.isWin());        
        grid.play(new Point(4, 6));
        assertFalse(grid.isWin());
        grid.play(new Point(5, 6));
        assertTrue(grid.isWin());
    }
    
    @Test
    public void canWinRandomShipsTest() {
        Grid grid = new GridImpl();
        grid.putRandomShips();
        
        assertFalse(grid.isWin());
        
        for(Point position : grid.getShipsPositions()){
            grid.play(position);
        }
        
        assertTrue(grid.isWin());
    }
    
    @Test
    public void shipOverlapTest() {
        GridImpl grid = new GridImpl();
        grid.ships.add(new ShipImpl(5, ShipDirection.HORIZONTAL, new Point(1,2)));
        grid.ships.add(new ShipImpl(3, ShipDirection.VERTICAL, new Point(5,5)));

        assertTrue("1", grid.testShipOverlap(new ShipImpl(3, ShipDirection.VERTICAL, new Point(5,5))));
        assertTrue("2", grid.testShipOverlap(new ShipImpl(3, ShipDirection.VERTICAL, new Point(6,5))));
        assertFalse("3", grid.testShipOverlap(new ShipImpl(3, ShipDirection.HORIZONTAL, new Point(1,4))));
        assertFalse("4", grid.testShipOverlap(new ShipImpl(3, ShipDirection.VERTICAL, new Point(7,5))));
    }

    @Test
    public void canGetToStringRepresentation() {
        GridImpl grid = new GridImpl();

        grid.ships.add(new ShipImpl(5, ShipDirection.HORIZONTAL, new Point(1,2)));
        grid.ships.add(new ShipImpl(3, ShipDirection.VERTICAL, new Point(5,5)));

        grid.play(new Point(2, 2));
        grid.play(new Point(2, 5));

        assertEquals('.', grid.toCharArray()[2][5]);
        assertEquals('X', grid.toCharArray()[2][2]);
        assertEquals('~', grid.toCharArray()[0][0]);
        assertEquals('O', grid.toCharArray()[1][2]);

        System.out.println(grid);

        assertEquals(grid.toString(),
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "~ O X O O O ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "~ ~ . ~ ~ O ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ O ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ O ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n" +
                "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ \n"
        );
    }
}
