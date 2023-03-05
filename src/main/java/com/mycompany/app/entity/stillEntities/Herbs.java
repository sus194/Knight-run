package com.mycompany.app.entity.stillEntities;

/**
 * The Herb class. It extends the StillEntity Class.
 * 
 * @author Sukhraj
 */
public class Herbs extends StillEntity {
    public boolean visible;
    /**
     * The constructor for the Herbs class. It calls the 
     * StillEntity constructor and sets it's own name as "herb".
     * 
     * @param x The x position of the herbs
     * @param y The y position of the herbs
     * @param imgplace The name of the image file for the herbs
     */
    public Herbs(int x, int y, String imgplace) {
        super(x, y, imgplace);
        
    }
    
}
