package com.mycompany.app;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.mycompany.app.entity.movingEntities.Player;
import com.mycompany.app.entity.movingEntities.MovingEnemy;

import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class PlayerMovementTest {

    //The test map used
    //1 is a wall, 0 is an empty tile, and 2 is a herb
    int map[][]=  {
        {1, 1, 1, 1, 1},
        {1, 0, 2, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 2, 1},
        {1, 1, 1, 1, 1}
    };

    /**
     * Testing right movement when a player has an open space on the right
     * The player should move one spot to the right.
     */
    @Test
    public void rightFreePlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        int right = KeyEvent.VK_RIGHT;
        player.setPos(1, 1);
        player.keyPressed(right, moveens);

        assertTrue(player.getPos().getX() == 2 && player.getPos().getY() == 1); 
    }

     /**
     * Testing right movement when a player does not have an open space on the right. 
     * The player should stay in the same spot and have the same x and y values
     */
    @Test
    public void rightBlockedPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        player.setPos(3, 1);
        int right = KeyEvent.VK_RIGHT;
        player.keyPressed(right, moveens);

        assertTrue(player.getPos().getX() == 3 && player.getPos().getY() == 1);
    }
    
    /**
     * Testing right movement when an object that's not a wall is to the right.
     * The player should move one spot to the right.
     */
    @Test
    public void rightCharacterPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        player.setPos(1, 1);
        int right = KeyEvent.VK_RIGHT;
        player.keyPressed(right, moveens);

        assertTrue(player.getPos().getX() == 2 && player.getPos().getY() == 1);
    }

    /**
     * Testing left movement when a player has an open space on the left
     * The player should move one spot to the left.
     */
    @Test
    public void leftFreePlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        int left = KeyEvent.VK_LEFT;
        player.setPos(2, 2);
        player.keyPressed(left, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 2);
    }

     /**
     * Testing left movement when a player does not have an open space on the left. 
     * The player should stay in the same spot and have the same x and y values
     */
    @Test
    public void leftBlockedPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        player.setPos(1, 1);
        int left = KeyEvent.VK_LEFT;
        player.keyPressed(left, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 1);
    }
    
    /**
     * Testing left movement when an object that's not a wall is to the left.
     * The player should move one spot to the left.
     */
    @Test
    public void leftCharacterPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        player.setPos(3, 1);
        int left = KeyEvent.VK_LEFT;
        player.keyPressed(left, moveens);

        assertTrue(player.getPos().getX() == 2 && player.getPos().getY() == 1);
    }

    /**
     * Testing up movement when a player has an open space above the player.
     * The player should move one spot up.
     */
    @Test
    public void upFreePlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        int up = KeyEvent.VK_UP;
        player.setPos(1, 2);
        player.keyPressed(up, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 1);
    }

     /**
     * Testing up movement when a player does not have an open space above the player. 
     * The player should stay in the same spot and have the same x and y values
     */
    @Test
    public void upBlockedPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        player.setPos(1, 1);
        int up = KeyEvent.VK_UP;
        player.keyPressed(up, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 1);
    }
    
    /**
     * Testing up movement when an object that's not a wall is above the player.
     * The player should move one spot to the up.
     */
    @Test
    public void upCharacterPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        player.setPos(2, 2);
        int up = KeyEvent.VK_UP;
        player.keyPressed(up, moveens);

        assertTrue(player.getPos().getX() == 2 && player.getPos().getY() == 1);
    }

    /**
     * Testing down movement when a player has an open space below the player.
     * The player should move one spot down.
     */
    @Test
    public void downFreePlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        int down = KeyEvent.VK_DOWN;
        player.setPos(1, 1);
        player.keyPressed(down, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 2);
    }

     /**
     * Testing down movement when a player does not have an open space below the player. 
     * The player should stay in the same spot and have the same x and y values
     */
    @Test
    public void downBlockedPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        player.setPos(1, 3);
        int down = KeyEvent.VK_DOWN;
        player.keyPressed(down, moveens);

        assertTrue(player.getPos().getX() == 1 && player.getPos().getY() == 3);
    }
    
    /**
     * Testing down movement when an object that's not a wall is below the player.
     * The player should move one spot to the down.
     */
    @Test
    public void downCharacterPlayerMovementTest() {
        Player player = new Player(map);
        ArrayList<MovingEnemy> moveens = new ArrayList<MovingEnemy>();
        
        player.setPos(3, 2);
        int down = KeyEvent.VK_DOWN;
        player.keyPressed(down, moveens);

        assertTrue(player.getPos().getX() == 3 && player.getPos().getY() == 3);
    }

}
