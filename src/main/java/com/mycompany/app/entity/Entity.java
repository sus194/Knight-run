package com.mycompany.app.entity;

import java.awt.Point;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.mycompany.app.control.Board;

/**
 * The general class for all entities in the game. All entities will use this as a base class.
 * The class sets the position of each entity, the entities name, and the image of each entity.
 * 
 * @author Sukhraj
 */
public class Entity {
    public Point pos;
    protected BufferedImage image;
    public String name;

    /**
     * Constructor for the Entity class. It sets the point position of the entity and loads the image based 
     * on the image file name.
     * 
     * @param x The x position of the entity on the map
     * @param y The y position of the entity on the map
     * @param imgplace The name of the image file for the entity
     */
    public Entity(int x, int y, String imgplace) {
        this.pos = new Point(x, y);
        loadImage(imgplace);
        //putting the place of the img in loadImage
    }
    /**
     * A method to return the name of the entity as a String
     * 
     * @return the name of the entity
     */
    public String tostring() {
        return name;
    }

    /**
     * A function to load the image of the entity
     * 
     * @param imgplace the name of the image file to load
     * @exception exc Tries to read the image, if it fails, prints a statement stating it failed.
     */
    protected void loadImage(String imgplace) {
        try{
            //creating the token for the img of the entity by using the imgplace 
            //image = ImageIO.read(new File("../img/001-knight.png"));
            image = ImageIO.read(getClass().getResourceAsStream(imgplace));
        }

        catch(IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
        }
    }

    /**
     * A function to draw the image of the entity to the board. It calls the graphics drawImage() function
     * and provides the image and the position on the board in which it should be populated.
     * 
     * @param g The graphics object used for creating graphics in java
     * @param observer The image observer to allow use of images
     */
    public void draw(Graphics g, ImageObserver observer) {
        // with the Point class, note that pos.getX() returns a double, but 
        // pos.x reliably returns an int. https://stackoverflow.com/a/30220114/4655368
        // this is also where we translate board grid position into a canvas pixel
        // position by multiplying by the tile size.
        g.drawImage(image, pos.x* Board.TILE_SIZE,pos.y*Board.TILE_SIZE, observer);
    }

    /**
     * A function to return the position of the entity as a point (an x and y value).
     * 
     * @return the position of the entity as a point
     * @see Point
     */
    public Point getPos() {
        return pos;
    }

    public void setPos(int x, int y) {
        pos.setLocation(x, y);
    }
    
}
