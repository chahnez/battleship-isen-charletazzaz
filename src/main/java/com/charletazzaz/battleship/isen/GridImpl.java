/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class GridImpl implements Grid {

    ArrayList<ShipImpl> ships = new ArrayList<>();
    ArrayList<Point> positionsPlayed = new ArrayList<>();
    
    @Override
    public void putRandomShips() {
        ships.clear();

        Random rd = new Random();

        int i = 1;
        while (i <= 5) {

            ShipDirection dir = ShipDirection.values()[rd.nextInt(2)];
            Point position;
            if (dir == ShipDirection.HORIZONTAL) {
                position = new Point(rd.nextInt(10 - i), rd.nextInt(10));
            } else {
                position = new Point(rd.nextInt(10), rd.nextInt(10 - i));
            }
            
            ShipImpl ship = new ShipImpl(i, dir, position);

            if (!testShipOverlap(ship)) {
                ships.add(ship);
                i++;
            }
        }
    }
    
    /**
     * Permet de vérifier si un bateau chevauche un autre bateau déjà
     * présent sur la grille.
     * @param ship
     * @return Overlap
     */
    public boolean testShipOverlap(ShipImpl ship) {
        for (Point point : ship.getArea()) {
            if (getShipsPositions().contains(point)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void play(Point position){
        positionsPlayed.add(position);        
    }
    
    @Override
    public boolean isPlayed(Point point){
        return positionsPlayed.contains(point);
    }
    
    @Override
    public ArrayList<Point> getPlayedPositions(){
        return positionsPlayed;
    }   
    
    @Override
    public ArrayList<Point> getShipsPositions() {
        ArrayList<Point> positions = new ArrayList<>();
        for (ShipImpl ship : ships) {
            positions.addAll(ship.getPositions());
        }
        return positions;
    }
    
    @Override
    public boolean isWin(){
        return positionsPlayed.containsAll(getShipsPositions());
    }   
    
    @Override
    public char[][] toCharArray() {
        char[][] array = new char[10][10];
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Point point = new Point(i, j);
                if (getShipsPositions().contains(point) && positionsPlayed.contains(point)) {
                    array[i][j] = 'X';                    
                }
                else if (getShipsPositions().contains(point)) {
                    array[i][j] = 'O'; 
                }
                else if (positionsPlayed.contains(point)) {
                    array[i][j] = '.';
                }
                else {
                    array[i][j] = '~'; 
                }                
            }
        }        
        return array;
    }    
    
    @Override
    public String toString() {
        char[][] grid = toCharArray();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                s.append(grid[j][i]);
                s.append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }
}
