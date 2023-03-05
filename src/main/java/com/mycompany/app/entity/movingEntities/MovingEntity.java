package com.mycompany.app.entity.movingEntities;

import com.mycompany.app.entity.Entity;

/**
 * The MovingEntity class. It extends the Entity class.
 * 
 * @author Sukhraj
 */
public class MovingEntity extends Entity {

    /**
     * The constructor for the MovingEntity class.
     * It calls the Entity constructor and sets its name
     * 
     * @param x The x position of the moving entity on the map
     * @param y The y position of the moving entity on the map
     * @param imgplace The name of the image file for the moving entity
     */
    public MovingEntity(int x, int y, String imgplace) {
        super(x, y, imgplace);
        
    }
}
