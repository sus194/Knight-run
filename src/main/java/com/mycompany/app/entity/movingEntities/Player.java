package com.mycompany.app.entity.movingEntities;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import com.mycompany.app.control.Board;

/**
 * The Player Class. It extends the MovingEntity class. It handles all things pretaining to the Player
 * including player movement and the score of the player.
 * 
 * @author Sukhraj
 */
public class Player extends MovingEntity {

    private int score;
    public int map1[][];
    public boolean released;
    /**
     * Constructor for the player class. It calls the MovingEntity constructor
     * and creates a copy of the 2d integer map for the board.
     * 
     * @param map the board map as a 2d integer array
     */
    public Player(int [][]map) {
        super(1, 3,"../img/001-knight.png");
        score = 0;
        
        map1 = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map1[i][j] = map[i][j];
            }
        }
        released = true;
    }

    /**
     * Function to process player movement. It checks the given key event and if the key
     * pressed corresponds to an arrow key, it will move the player corresponding to it.
     * It will also updating the MovingEnemies as to the location of the player so the enemies can find the player.
     * 
     * @param e the key event being processed
     * @param moveens A list of moving enemies
     */
    public void keyPressed(int key, ArrayList<MovingEnemy> moveens) {
        // every keyboard get has a certain code. get the value of that code from the
        // keyboard event so that we can compare it to KeyEvent constants
  
        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP && map1[pos.y-1][pos.x] != 1) {
            for (MovingEnemy moveen : moveens) {
                moveen.boolmap[pos.y][pos.x] = true;
            }
            pos.translate(0, -1);
        }
        if (key == KeyEvent.VK_RIGHT && map1[pos.y][pos.x+1] != 1) {
            for (MovingEnemy moveen : moveens) {
                moveen.boolmap[pos.y][pos.x] = true;
            }
            pos.translate(1, 0);
        }
        if (key == KeyEvent.VK_DOWN && map1[pos.y+1][pos.x] != 1) {
            for (MovingEnemy moveen : moveens) {
                moveen.boolmap[pos.y][pos.x] = true;
            }
            pos.translate(0, 1);
        }
        if (key == KeyEvent.VK_LEFT && map1[pos.y][pos.x-1] != 1) {
            for (MovingEnemy moveen : moveens) {
                moveen.boolmap[pos.y][pos.x] = true;
            }
            pos.translate(-1, 0);
        }
    }

    /**
     * This gets called once every tick, before the repainting process happens
     *  so we can do anything needed in here to update the state of the player.
     * Currently, the function is a check to ensure the player cannot go out of bounds on the map.
     */
    public void tick() {

        // prevent the player from moving off the edge of the board sideways
        if (pos.x < 1) {
            pos.x = 1;
        } else if (pos.x >= Board.COLUMNS-1) {
            pos.x = Board.COLUMNS - 2;
        }
        // prevent the player from moving off the edge of the board vertically
        if (pos.y < 1) {
            pos.y = 1;
        } else if (pos.y >= Board.ROWS-1) {
            pos.y = Board.ROWS - 2;
        }
    }

    /**
     * Function to return the score as a string
     * 
     * @return the score
     */
    public String getScore() {
        return String.valueOf(score);
    }
    /**
     * Function to update the score. It takes the amount and adds it to the current player score.
     * 
     * @param amount the amount to update the score by
     */
    public void addScore(int amount) {
        score += amount;
    }

    public int getIntScore() {
        return score;
    }
        
    
}
