package com.mycompany.app.entity.stillEntities;

/**
 * The StillEnemy class. It extends the StillEntity Class
 * 
 * @author Sukhraj
 */
public class StillEnemy extends StillEntity {

    /**
     * The constructor for the StillEnemy class. It calls the 
     * StillEntity constructor and sets a value of -1.
     * 
     * @param x The x position of the still enemy
     * @param y The y position of the still enemy
     * @param imgplace The name of the image file for the still enemy
     */
    public StillEnemy(int x, int y, String imgplace) {
        super(x, y, imgplace);
        value = -1;
       
        
    }
    
}
