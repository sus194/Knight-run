package com.mycompany.app.entity.stillEntities;

import com.mycompany.app.entity.Entity;

/**
 * The StillEntity class. It extends the Entity class.
 * 
 * @author Sukhraj
 */
public class StillEntity extends Entity{
    public int value;
    public boolean collision = false;
    //map of the the board in terms of the 

    /**
     * The constructor for the StillEntity Class.
     * It calls the Entity constructor.
     * 
     * @param x The x position of the entity
     * @param y The y position of the entity
     * @param imgplace The name of the image file for the still entity
     */
    public StillEntity(int x, int y, String imgplace) {
        super(x, y, imgplace);
        
    }

}
