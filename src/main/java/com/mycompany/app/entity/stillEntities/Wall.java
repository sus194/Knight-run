package com.mycompany.app.entity.stillEntities;

/**
 * The Wall class. It extends the StillEntity class.
 * 
 * @author Sukhraj
 */
public class Wall extends StillEntity {

    /**
     * The Wall csontructor. It calls the StillEntity constructor.
     * 
     * @param x The x position of the wall
     * @param y The y position of the wall
     * @param imgplace The name of the image file for the wall
     */
    public Wall(int x, int y, String imgplace) {
        super(x, y, imgplace);
    }
}
