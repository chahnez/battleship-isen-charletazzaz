/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.json.*;

import com.charletazzaz.battleship.isen.harness.JettyHarness;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 *
 * @author Vivien
 */
public class GameServletTest extends JettyHarness {
    
    @Test
    public void canInitializeBothGrids() throws Exception {
        JsonObject req = Json.createReader(new StringReader(get(getServletUri()))).readObject();
        
        JsonArray GridEnemyShipsPositions = req.getJsonObject("grid_enemy").getJsonArray("jsonShipsPositions");
        JsonArray GridMeShipsPositions = req.getJsonObject("grid_me").getJsonArray("jsonShipsPositions");
        
        assertEquals(GridEnemyShipsPositions.size(), 15);
        assertEquals(GridMeShipsPositions.size(), 15);
    }
      
    @Test
    public void canHumanPlayOnGrid() throws Exception {
        playPosition(1, 3);
        JsonObject req = Json.createReader(new StringReader(get(getServletUri()))).readObject();
        JsonArray GridEnemyPlayedPositions = req.getJsonObject("grid_enemy").getJsonArray("jsonPlayedPositions");
        
        assertTrue(GridEnemyPlayedPositions.toString().contains("1-3"));
    }
    
    @Test
    public void canComputerPlayOnGrid() throws Exception {
        playPosition(1, 3);
        JsonObject req = Json.createReader(new StringReader(get(getServletUri()))).readObject();
        JsonArray GridMePlayedPositions = req.getJsonObject("grid_me").getJsonArray("jsonPlayedPositions");
        
        assertEquals(GridMePlayedPositions.size(), 1);
    }
    
    @Test
    public void canResetGame() throws Exception {
        playPosition(1, 3);
        JsonObject req = Json.createReader(new StringReader(get(getServletUri()))).readObject();
        JsonArray GridEnemyPlayedPositions = req.getJsonObject("grid_enemy").getJsonArray("jsonPlayedPositions");
        
        assertEquals(GridEnemyPlayedPositions.size(), 1);
        
        resetGame();
        
        req = Json.createReader(new StringReader(get(getServletUri()))).readObject();
        GridEnemyPlayedPositions = req.getJsonObject("grid_enemy").getJsonArray("jsonPlayedPositions");
        
        assertEquals(GridEnemyPlayedPositions.size(), 0);                
    }
    
    /**
     * Envoie la methode DELETE sur le endpoint de la servlet.
     *
     * @return -
     * @throws IOException
     */
    private String resetGame() throws IOException {
        return delete(getServletUri());

    }

    private String getServletUri() {
        return getBaseUri() + "/game";
    }

    /**
     * Envoie la methode POST sur le endpoint de la servlet
     *
     * @return -
     * @throws IOException
     */
    private String playPosition(int i, int j) throws IOException {
        Map<String, String> params = new HashMap<>();
        params.put("i", String.valueOf(i));
        params.put("j", String.valueOf(j));
        params.put("action", "click");
        return post(getServletUri(), params);
    }
    
}
