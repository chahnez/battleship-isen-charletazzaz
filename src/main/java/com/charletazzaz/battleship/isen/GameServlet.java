/*
 * Projet BattleShip ISEN JEE
 * Vivien CHARLET
 * Chahnez AZZAZ
 */
package com.charletazzaz.battleship.isen;

import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.json.JsonArray;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, Grid> game = getGameSessionOrInitialize(req);
        JsonObjectBuilder json = Json.createObjectBuilder();
        
        for (Map.Entry<String, Grid> entry : game.entrySet()) {
            String key = entry.getKey();
            Grid value = entry.getValue();
            
            JsonArrayBuilder jsonShipsPositions = Json.createArrayBuilder();
            for (Point p : entry.getValue().getShipsPositions()) {
                jsonShipsPositions.add(p.x + "-" + p.y);
            }
            
            JsonArrayBuilder jsonPlayedPositions = Json.createArrayBuilder();
            for (Point p : entry.getValue().getPlayedPositions()) {
                jsonPlayedPositions.add(p.x + "-" + p.y);
            }
            
            JsonObject grid = Json.createObjectBuilder()
                        .add("jsonShipsPositions", jsonShipsPositions)
                        .add("jsonPlayedPositions", jsonPlayedPositions)
                        .add("isWin", entry.getValue().isWin())
                .build();
            
            json.add(entry.getKey(), grid);
        }               
        
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        out.print(json.build());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Grid> game = getGameSessionOrInitialize(req);

        Grid grid_me = game.get("grid_me");
        Grid grid_enemy = game.get("grid_enemy");

        String click_i = req.getParameter("i");
        String click_j = req.getParameter("j");
        String action = req.getParameter("action");

        if (action != null && action.equals("click")) {
            Random rd = new Random();
            grid_me.play(new Point(rd.nextInt(10), rd.nextInt(10)));
            grid_enemy.play(new Point(Integer.parseInt(click_i), Integer.parseInt(click_j)));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("game_servlet");
    }
    
    protected Map<String, Grid> getGameSessionOrInitialize(HttpServletRequest req){
        
        if (req.getSession().getAttribute("game_servlet") != null) {
            return (Map<String, Grid>) req.getSession().getAttribute("game_servlet");            
        }
        
        Map<String, Grid> map = new HashMap<>();
        
        Grid grid_me = new GridImpl();
        grid_me.putRandomShips();
        map.put("grid_me", grid_me);
        
        Grid grid_enemy = new GridImpl();
        grid_enemy.putRandomShips();
        map.put("grid_enemy", grid_enemy);
        
        req.getSession().setAttribute("game_servlet", map);
        return map;
    }
}
