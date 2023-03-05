package com.mycompany.app.entity.stillEntities;

/**
 * Class for the blue herb. The blue herb is an extension of the herb class.
 * The blue herb gives the player +1 score when collecting it.
 * 
 * @author Suhkraj
 */
public class BlueHerbs extends Herbs {
    /**
     * The constructor for the BlueHerbs class. It calls the super class for the blue herbs. 
     * It also assigns the value of the blue herb and the name.
     * 
     * @param x The x position of the blue herb
     * @param y The y position of the blue herb
     */
    public BlueHerbs(int x, int y) {
        super(x, y, "../img/001-leaf.png");
        visible = true;
        value = 1;
        name = "blueherb";
    }
    
}
