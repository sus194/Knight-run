package com.mycompany.app.control;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JPanel implements ActionListener{
    //Size of each Tile
    public static final int TILE_SIZE = 30;
    //Number of rows on the board
    public static final int ROWS = 27;
    //Number of columns on the board
    public static final int COLUMNS = 26;
    //Title of the game
    private static final String TITLE = "Knight Run!";
    //Components of the Start menu
    JButton play = new JButton("play");
    JButton exit = new JButton("exit");
    JLabel title = new JLabel(TITLE);
    String scoreText =  "";
    JLabel score = new JLabel( "");
    JLabel displayPicture;
    ImageIcon picture;
    JPanel buttonPanel = new JPanel();
    Screen screen;

    public Menu(BorderLayout layout, Screen screen) {
        super(layout);
        this.screen = screen;

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        
    }


    
    
}
