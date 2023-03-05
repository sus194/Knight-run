package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.mycompany.app.control.Board;
import com.mycompany.app.control.Screen;
import com.mycompany.app.entity.movingEntities.Player;
import com.mycompany.app.entity.stillEntities.BlueHerbs;
import com.mycompany.app.entity.stillEntities.GoldenHerbs;
import com.mycompany.app.entity.stillEntities.StillEnemy;
import java.util.concurrent.TimeUnit;


public class ScoreAndTimerTest {
    //The test map used
    //1 is a wall, 0 is an empty tile, and 2 is a herb
    int map[][]=  {
        {1, 1, 1, 1, 1},
        {1, 0, 2, 3, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1}
    };

    /**
     * Upon hitting an herb, the player should increase in score.
     */
    @Test
    public void herbScoreTest() {
        Player player = new Player(map);
        BlueHerbs herb = new BlueHerbs(1, 2);
        player.addScore(herb.value);
        assertTrue(player.getIntScore() == 1);
    }

    /**
     * Upon hitting an herb, the player should increase in score.
     */
    @Test
    public void goldHerbScoreTest() {
        Player player = new Player(map);
        GoldenHerbs herb = new GoldenHerbs(1, 2);
        player.addScore(herb.value);
        assertTrue(player.getIntScore() == 3);
    }

    /**
     * The player should have a starting score of 0.
     */
    @Test
    public void defaultScoreTest() {
        Player player = new Player(map);
        assertTrue(player.getIntScore() == 0);
    }

    /**
     * The player when stepping on a still enemy should lose a point
     */
    @Test
    public void enemyScoreTest() {
        Player player = new Player(map);
        StillEnemy enemy = new StillEnemy(1, 3, "");
        player.addScore(enemy.value);
        assertTrue(player.getIntScore() == -1);
    }
    
    /**
     * Test to ensure the timer of the game is working. Waits 2 seconds after the start of the game to allow everything to
     * initialize
     */
    @Test
    public void timerTest() {
        Screen screen = new Screen();

        Board board = new Board(screen);

        board.startGame();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }

        assertTrue(board.gameTimer.getSecs() == 1);
    }


    
}
