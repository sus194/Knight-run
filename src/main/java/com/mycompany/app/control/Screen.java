package com.mycompany.app.control;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/**
 * The screen class for the program. It handles the creation of the screen, the initialization of all the JPanels,
 * the creation of the main menu and the logic for when to start the game and exit the program.
 * 
 * @author Justin and Sukhraj
 */
public class Screen extends JFrame implements ActionListener {
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

    //The primary layout for the game
    CardLayout cLayout = new CardLayout();

    //Panels to display
    JPanel panel = new JPanel();
    Board game = new Board(this);
    JLabel displayPicture;
    ImageIcon picture;
    Menu menu = new Menu(new BorderLayout(), this);
    JPanel buttonPanel = new JPanel();


    /**
     * The screen constructor. It sets the default close operation to exit on close as well
     * as sets the window size preference to (Tile size * Columns) for the Width and (Tile size * rows) for the height
     */
    public Screen() {
        //Set frame defaults
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
    }

    /**
     * The method to start the game. It sets ups the card layout used to naviage between the game and the menu screen.
     * It also calls the setupPanel() and drawStart() functions to setup the rest up the screen. Finally, it sets all 
     * other properties of the game such as setting resizable to false and the title.
     */
    public void start() {
        //Set the layout to a card layout
        panel.setLayout(cLayout);
        cLayout.addLayoutComponent(panel, "menu");
        setupPanel();
        drawStart();
        drawPicture();
        this.setResizable(false);
        this.pack();
        this.setTitle("Knight Game!");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * A function to set the panel up for the game. The function sets listeners of the Play button as well as the exist button. 
     * It also adds the menu and board panel to the main panel. Finally, the main panel is added the the JFrame and is set to show
     * the menu.
     */
    public void setupPanel() {
        //Add listeners for the play screen and exit
        play.addActionListener(this);
        exit.addActionListener(this);
        //adding children to parent Panel
        panel.add(menu,"Menu");
        panel.add(game,"Game");

        add(panel);
        cLayout.show(panel,"Menu");
    }

    /**
     * A function to draw the title screen picture. It tries reading the image file using ImageIcon then adds
     * image to the menu.
     * 
     * @throws ex Throws a string stating there was an error opening the image
     * @author Justin
     */
    public void drawPicture() {
        try {
            picture = new ImageIcon(getClass().getResource("Picture.png"));
            displayPicture = new JLabel(picture);
            menu.add(displayPicture);
        }
        catch (Exception ex) {
            System.out.println("Error opening image file: " + ex.getMessage());
        }
    }

    /**
     * Function to draw the Start menu of the game. It sets the dimensions of the start and exit buttons, then
     * adds the two buttons to a seperate button panel. It then centers the text of the title and changes its font before
     * adding the button panel and the title to the menu panel. It then sets the default background to white.
     */
    private void drawStart() {
        //Set dimensions of the buttons
        play.setPreferredSize(new Dimension(300, 100));
        exit.setPreferredSize(new Dimension(300, 100));
        //Add the buttons to the button panel
        buttonPanel.add(play);
        buttonPanel.add(exit);
        menu.add(buttonPanel, BorderLayout.SOUTH);
        //Set the title appearance
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setVerticalAlignment(JLabel.CENTER);

        //add the menu buttons to the start
        menu.add(title, BorderLayout.NORTH);
    }

    /**
     * Function to check for actions. It checks the source of an action event and determines what to do based on that.
     * If the source of the action was from the exit button the game will exit, otherwise if it was from the play button,
     * it will start a new game
     * 
     * @param e A type of action event. Allows you to access the properties of an action event
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        //Checks which button was pressed in the menu
        if (source == exit) {
            System.exit(0);
        }
        else if (source == play) {
            game.startGame();
            game.setVisible(true);
            game.requestFocusInWindow();
            game.addKeyListener(game);

        } 

    } 



    /**
     * Function to setup the game over screen. It switches the screen to the menu screen then changes text
     * based on whether the player won a game or not. If the game was won the score and time spent playing will be displayed
     * 
     * @param win A boolean of whether the player won the game
     * @param herbs The number of herbs collected by the player
     * @param mins Number of minutes played 
     * @param secs Number of seconds played
     */
    public void gameOver(Boolean win, String herbs, int mins, int secs) {
        try {
            
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Switches to the Menu panel
        game.setVisible(false);

        //Setting new text for the menu
        title.setText("GAME OVER");
        play.setText("Play Again?");
        String winText = win? "You Win!" : "You Lose!";
        if (win) {
            String timeText = mins == 0? secs + " seconds" : mins + " minute(s) " + secs + " seconds";
            String scoreText = ( winText + "<br>" + "You had a score of: " + herbs +
            "<br>" + "A time of " + timeText);
            score.setText("<html><div style='text-align: center;'>" + scoreText + "</div></html>");
        }

        else {
            String scoreText = ( "<h2>" + winText + "</h2>");
            score.setText("<html><div style='text-align: center;'>" + scoreText + "</div></html>");
        }
        
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setVerticalAlignment(JLabel.CENTER);
        score.setFont(new Font("Serif", Font.BOLD, 25));
        menu.add(score, BorderLayout.CENTER);
        menu.remove(displayPicture);

        cLayout.show(panel, "menu");
    }
}