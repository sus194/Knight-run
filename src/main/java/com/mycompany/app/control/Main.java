package com.mycompany.app.control;

/**
 * The main class of this program. Only has one function, the main method.
 * 
 * @author Justin
 */
public class Main { 
    /**
     * Main method for the game. Is sets a new Screen up and starts the program.
     * @param args Arguments when the main methos is run (empty)
     */
    public static void main( String[] args ) {
        Screen screen = new Screen();
        screen.start();
    }

    
}