package com.mycompany.app;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.mycompany.app.entity.movingEntities.MovingEnemy;
import com.mycompany.app.entity.movingEntities.Player;
import com.mycompany.app.entity.stillEntities.BlueHerbs;
import com.mycompany.app.entity.stillEntities.Doctor;
import com.mycompany.app.entity.stillEntities.Herbs;

import java.util.ArrayList;

public class GameStateTest {
    //The test map used
    //1 is a wall, 0 is an empty tile, and 2 is a herb
    int map[][]=  {
        {1, 1, 1, 1, 1},
        {1, 0, 2, 3, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 1, 1, 1, 1}
    };

    int map2[][]=  {
        {1, 1, 1, 1, 1},
        {1, 0, 2, 0, 1},
        {1, 0, 0, 0, 1},
        {1, 0, 2, 0, 1},
        {1, 1, 1, 1, 1}
    };

    /**
     * A Test for when an emeny catches a player. It is caught when the enemy is one square away to 
     * make the game look better.
     */
    @Test
    public void gameOverFromRightTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        MovingEnemy enemy = new MovingEnemy(2, 1, "", map);

        assertTrue(enemy.iscaught(player.getPos()));
    }

    /**
     * A Test for when an emeny catches a player. It is caught when the enemy is one square away to 
     * make the game look better.
     */
    @Test
    public void gameOverFromLeftTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        MovingEnemy enemy = new MovingEnemy(0, 1, "", map);

        assertTrue(enemy.iscaught(player.getPos()));
    }

        /**
     * A Test for when an emeny catches a player. It is caught when the enemy is one square away to 
     * make the game look better.
     */
    @Test
    public void gameOverFromUpTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        MovingEnemy enemy = new MovingEnemy(1, 2, "", map);

        assertTrue(enemy.iscaught(player.getPos()));
    }

    /**
     * A Test for when an emeny catches a player. It is caught when the enemy is one square away to 
     * make the game look better.
     */
    @Test
    public void gameOverFromBottomTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        MovingEnemy enemy = new MovingEnemy(1, 0, "", map);

        assertTrue(enemy.iscaught(player.getPos()));
    }
    
    /**
     * A Test to ensure the game doesn't falsly state the game is over.
     */
    @Test
    public void gameNotOverTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        MovingEnemy enemy = new MovingEnemy(3, 3, "", map);

        assertTrue(!enemy.iscaught(player.getPos()));
    }

    /**
     * A test to check if the player and doctor position is the same
     */
    @Test
    public void reachDoctorTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        Doctor doctor = new Doctor(1, 1, "");
        assertTrue(doctor.getPos().getX() == player.getPos().getX() && doctor.getPos().getY() == player.getPos().getY());
    }

    /**
     * A test to check if the player and doctor position is not the same
     */
    @Test
    public void notReachedDoctorTest() {
        Player player = new Player(map);
        player.setPos(1, 1);
        Doctor doctor = new Doctor(1, 2, "");
        assertFalse(doctor.getPos().getX() == player.getPos().getX() && doctor.getPos().getY() == player.getPos().getY());
    }

    /**
     * Various tests for blue herb collection since regular rewards impact the game completion condition.
     */
    @Test
    public void blueHerbCollectionTest() {
        Player player = new Player(map2);
        player.setPos(1, 1);
        ArrayList<Herbs> herbs = new ArrayList<>();
        herbs.add(new BlueHerbs(2, 1));
        herbs.add(new BlueHerbs(2, 3));
        //First test: If the player is not in the same position as any of the herbs, they should not be collected.
        for (int i = 0; i < herbs.size(); i++) {
            if (player.getPos().equals(herbs.get(i).getPos())) {
                herbs.remove(herbs.get(i));
            }
        }
        assertTrue(herbs.size() == 2);
        player.setPos(2, 1);
        //Second test: If the player is in the same position as any of the herbs, only that specific herb should be collected.
        for (int i = 0; i < herbs.size(); i++) {
            if (player.getPos().equals(herbs.get(i).getPos())) {
                herbs.remove(herbs.get(i));
            }
        }
        assertTrue(herbs.size() == 1);
        player.setPos(2, 3);
        //Third test: Player has collected all regular rewards
        for (int i = 0; i < herbs.size(); i++) {
            if (player.getPos().equals(herbs.get(i).getPos())) {
                herbs.remove(herbs.get(i));
            }
        }
        assertTrue(herbs.size() == 0);
    }
}
