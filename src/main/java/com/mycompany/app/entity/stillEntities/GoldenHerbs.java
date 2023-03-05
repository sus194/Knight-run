package com.mycompany.app.entity.stillEntities;

/**
 * The class for the Golden Herb. It is an extension of the Herb class.
 * The value of the Golden Herb is set to 3.
 * 
 * @author Sukhraj
 */
public class GoldenHerbs extends Herbs {
    
    /**
     * The constructor for the GoldenHerb class. It extends the Herb class
     * and calls the Herb constructor to set its image and x,y position.
     * The constructor also sets the value of the herb to 3.
     * 
     * @param x The x position of the golden herb
     * @param y The y position of the golden herb
     */
    public GoldenHerbs(int x, int y) {
        super(x, y, "../img/golden-pothos.png");
        visible = false;
        value = 3;
        name = "goldenherb";
    }
    
}
