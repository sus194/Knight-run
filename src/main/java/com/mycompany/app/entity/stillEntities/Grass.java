package com.mycompany.app.entity.stillEntities;

/**
 * The Grass class. It is an extension of the StillEntity class.
 * 
 * @author Sukhraj
 */
public class Grass extends StillEntity {

    /**
     * The constructor for the Grass class. It calls the StillEntity Constructor.
     * 
     * @param x The x position of the grass
     * @param y The y position of the grass
     */
    public Grass(int x, int y) {
        super(x, y, "../img/grass.png");
    }
    
}
