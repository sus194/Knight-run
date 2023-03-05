package com.mycompany.app.control;

import com.mycompany.app.entity.movingEntities.MovingEnemy;
import com.mycompany.app.entity.movingEntities.Player;
import com.mycompany.app.entity.stillEntities.BlueHerbs;
import com.mycompany.app.entity.stillEntities.Doctor;
import com.mycompany.app.entity.stillEntities.GoldenHerbs;
import com.mycompany.app.entity.stillEntities.Grass;
import com.mycompany.app.entity.stillEntities.Herbs;
import com.mycompany.app.entity.stillEntities.StillEnemy;
import com.mycompany.app.entity.stillEntities.StillEntity;
import com.mycompany.app.entity.stillEntities.Wall;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 * The board class for the game. Handles all board related functions such as entity population, board reading,
 * board drawing, Action events on the board, checking for game over and board logic between entities.
 * 
 * @author Sukhraj and Justin
 */
public class Board extends JPanel implements ActionListener, KeyListener {

    //the delay for the timer
    private final int DELAY = 200;
    //the number of mountains
    //public static final int NUM_mountians;
    //Size of each tile
    public static final int TILE_SIZE = 30;
    //Number of rows on the board
    public static final int ROWS = 27;
    //Number of columns on the board
    public static final int COLUMNS = 26;
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    //the background map
    int map[][];
    //objects that appear on the board
    private Player player;
    private Doctor doc;
    private ArrayList<Herbs> herbs;
    private ArrayList<StillEntity> stillEntities;
    private ArrayList<MovingEnemy> movingEnemies;
    private ArrayList<StillEnemy> stillEnemies;
    //important variables for signaling the start and end of the game 
    //they are very neccesory
    int start;
    int end;



    //counter for the apperence of the bonus reward
    private int counter;
    Screen screen;
    boolean win;
    boolean gameEnded = false;
    public GameTimer gameTimer;
    
    /**
     * The Board constructor. It initializes the screen variable and sets the background and preferred screen dimension.
     * 
     * @param screen The JFrame screen the board is being displayed one
     */
    public Board(Screen screen) {
        this.screen = screen;
        this.setBackground(Color.green);
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));

    }

    /**
     * @author Sukhraj
     * Function to start the game. It calls all relevant methods to start the game as well as initializes all variables.
     */
    public void startGame() {
        //intializing player at position 0,0 on the board
        readMap();
        gameTimer = new GameTimer();
        
        player = new Player(map);
        movingEnemies = new ArrayList<>();
        addMovingEnemies();
        stillEnemies = populateStillEnemies();
        herbs = populateHerbs();
        stillEntities = populateStillEntities();
        counter = 0;
        start = 0;
        end = 0;
        win = false;
        doc = new Doctor(map[0].length-3, map.length-3, "../img/wizard.png");
        timer = new Timer(DELAY, this);
        timer.start();

    }

    /**
     * Function to check for actions. It checks the source of an action event and determines what to do based on that.
     * If the source of the action was a game over, the game over function will trigger. 
     * it will start a new game
     * @author Sukhraj
     * @param e A type of action event. Allows you to access the properties of an action event
     * @see ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        gameTimer.updateTime();
        //add the gave over here in this if statment
        counter++;
        if (gameEnded == true) {
            gameOver();
        }
        
        Random rand = new Random();
        if (start == 0) {
            start = rand.nextInt(40)+70;
        }
        if (counter == end) {
            for (Herbs herb : herbs){
                if (herb.name == "goldenherb"){
                    herb.visible = false;
                }
            }
            counter = 0;
            end = 0;
            start = 0;
        }
        else if (counter == start) {
            for (Herbs herb : herbs) {
                if (!herb.visible) {
                    herb.visible = true;
                }
            }
            end = counter + 10;
        }

        player.tick();
        
        
        //moves the enemies and the if statment is going to be used for showing the game over screen that has the time and score one it
        for (MovingEnemy moveen: movingEnemies) {
            moveen.catchknight(player);
            if (moveen.iscaught(player.pos)) {
                gameEnded = true;
            }
        }
         
        collectHerbs();
        trap();
        //the game complition screen that shows the score and time will happen here
        if (doc.pos.equals(player.pos) && herbs.size() == 1){
            win = true;
            gameEnded = true;
        }  
        repaint();
    }

    /**
     * Overridden function to paint all the components. It paints each piece of the board including all
     * entity types.
     * @author Sukhraj
     * @param g The graphics object used for creating graphics in java
     * @see Graphics
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (StillEntity stillen : stillEntities){
            stillen.draw(g,this);
        }

        for (Herbs herb : herbs) {
            if (herb.visible){
                herb.draw(g, this);
            }
        }
        for (MovingEnemy moveen: movingEnemies){
            moveen.draw(g, this);
        }
        for (StillEnemy still: stillEnemies){
            still.draw(g, this);
        }
        player.draw(g, this);
        doc.draw(g, this);
        drawTopUI(g);
        
    }


    /**
     * A private function to draw the Score and timer. It uses the System.currentTimeMillis() function to get the 
     * time spent on the game. It creates a rectangle for both the Score and Timer to be put in so that the text displayed
     * will be center and follows help from this stackoverflow link
     *  https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java/27740330#27740330
     * @author Justin
     * @param g The graphics object used for creating graphics in java
     * @see Graphics
     */
    private void drawTopUI(Graphics g) {

        // set the text to be displayed
        String text = "Herbs: " +  player.getScore();

        // set the text color and font
        g.setColor(new Color(52,128,168));
        g.setFont(new Font("GB18030 Bitmap", Font.BOLD, 25));
        FontMetrics metrics = g.getFontMetrics(g.getFont());

        // https://stackoverflow.com/questions/27706197/how-can-i-center-graphics-drawstring-in-java/27740330#27740330
        Rectangle scoreRect = new Rectangle(70, 10, 3, 1);
        int x = scoreRect.x + (scoreRect.width - metrics.stringWidth(text)) / 2;
        int y = scoreRect.y + ((scoreRect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        Rectangle timeRect = new Rectangle(400, 10, 3, 1);
        int xT = timeRect.x + (timeRect.width - metrics.stringWidth(text)) / 2;
        int yT = timeRect.y + ((timeRect.height - metrics.getHeight()) / 2) + metrics.getAscent();

        // draw the string
        g.drawString(text, x, y);
        if (gameTimer.getMins() == 0) {
            g.drawString(gameTimer.getSecText(), xT, yT);
        } else {
            g.drawString(gameTimer.getMinText(), xT, yT);
        }
    }

    /**
     * A required overriden function for a key typed. This function is required as the board implements KeyListener.
     * Left blank as nothing in this game requires typing.
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * A function to determine the logic after a key has been pressed. Once a key has been pressed
     * and released, the function will call the player.keyPressed() function and pass through the key event 
     * and moveens.
     * @author Sukhraj
     * @param e A type of key event. Allows you to access the properties of a key event
     * @see KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //for blocking the knight by moutains
        if (player.released) {
            player.keyPressed(e.getKeyCode(), movingEnemies);
        }
        player.released = false;
    }

    /**
     * A function to determine the logic on key release. The function sets the player.released variable to true.
     * @author Sukhraj
     * @param e A type of key event. Allows you to access the properties of a key event
     * @see KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        player.released = true;
    }


    /**
     * A function to populate herbs on the board. The function loops through the map and if it sees
     * a two or a three, it will add either a blue herb or a golden herb to the herb list.
     * @author Sukhraj
     * @return The list of herbs on the map
     */
    private ArrayList<Herbs> populateHerbs() {
        ArrayList<Herbs> herbsList = new ArrayList<>();

        // create the given number of blueherbs in random positions on the board.

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (map[i][j] == 2){
                    herbsList.add(new BlueHerbs(j, i));
                }
                else if (map[i][j] == 3) {
                    herbsList.add(new GoldenHerbs(j, i));
                }
            }
        }
        return herbsList;
    }

    /**
     * A function to check for collected herbs. The function loops through the list of herbs and checks if the 
     * @author Sukhraj
     * player has landed on a herb, it will then add the appropriate score to the player and remove all collected herbs.
     */
    private void collectHerbs() {
        // allow player to pickup herbs
        ArrayList<Herbs> collectedherbs = new ArrayList<>();
        for (Herbs herb : herbs) {
            // if the player is on the same tile as a herb, collect it
            if (player.getPos().equals(herb.getPos())) {
                // give the player some points for picking this up
                if (herb.visible) {
                    player.addScore(herb.value);
                }
                if(herb.name == "blueherb") {
                    collectedherbs.add(herb);
                }
                else {
                    herb.visible = false;
                } 
            }
        }
        // remove collected herbs from the board
        herbs.removeAll(collectedherbs);
    }

    /**
     * A function to read the map.txt file. The function will loop through the text file and convert it 
     * into a 2d integer array.
     * @author Sukhraj
     */
    public void readMap() {
        map = new int[ROWS][COLUMNS];
        try {
            //reading the map file
            InputStream m = getClass().getResourceAsStream("maps/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(m));
            int row = 0;
            int col = 0;

            while (row < ROWS && col < COLUMNS) {
                String line = br.readLine();
                while(col < COLUMNS) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    map[row][col] = num;
                    col++;
                }
                col = 0;
                row++;
            }

            br.close();
        }
        catch (Exception e) {
            System.out.println("gfds");
        }
    }
    /**
     * A private function that populates all the still entities in the game such as walls and the grass.
     * The function iterates through the 2d map and adds either grass or a wall if the value is a 0 or a 1.
     * @author Sukhraj
     * @return the list of still entities added to the game
     */
    private ArrayList<StillEntity> populateStillEntities() {
        String[] imgs = {"../img/001-mountain.png", "../img/tree.png"};
        Random rand = new Random();
        ArrayList<StillEntity> stillList = new ArrayList<>();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                stillList.add(new Grass(j, i));
                if (map[i][j] == 1){
                    stillList.add(new Wall(j,i, imgs[rand.nextInt(2)]));
                }
            }
        }
        return stillList;
    }

    /**
     * A private function that populates all the still enemies in the game. It iterates through the 
     * 2d integer map and populates an enemy if the value at the map position is a 4.
     * @author Sukhraj
     * @return the list of still enemies added to the game
     */
    private ArrayList<StillEnemy> populateStillEnemies() {
        ArrayList<StillEnemy> stillEnemList = new ArrayList<>();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (map[i][j] == 4) {
                    stillEnemList.add(new StillEnemy(j, i, "../img/trap-1.png"));
                }  
            }
        }
        return stillEnemList;
    }

    /**
     * A function that loops through every still enemy in the map. If the player lands on a still enemy,
     * it will adjust the players score accordingly and if the players score goes down to 0, it ends the game.
     * @author Sukhraj and Hamza
     */
    private void trap() {
        ArrayList<StillEnemy> collectedStillEnemies = new ArrayList<>();
        for (StillEnemy stillenem : stillEnemies) {
            
            if (player.getPos().equals(stillenem.getPos())) {
                collectedStillEnemies.add(stillenem);
                player.addScore(stillenem.value);
                if(Integer.valueOf(player.getScore()) <= 0) {
                    gameEnded = true;
                }
            }
        }
        stillEnemies.removeAll(collectedStillEnemies);
    }

    /**
     * A function to add all moving enemies to the board. The function iterates through the 2d integer array map and populates
     * a moving enemy if the value at the map position is a 5.
     * @author Sukhraj
     */
    private void addMovingEnemies() {
        String[] imgs = {"../img/001-onibi.png", "../img/002-monster.png", "../img/Orc.png", "../img/004-grim-reaper.png"};
        Random rand = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(map[i][j] == 5){
                    movingEnemies.add(new MovingEnemy(j, i, imgs[rand.nextInt(4)], player.map1));
                }
            }
        }
    }

    /**
     * A function to call when the game is over. It calls the Screen gameOver() function and passes on information
     * such as the amount of time played, the number of herbs collected, and whether or not the player won.
     * @author Sukhraj
     */
    public void gameOver() {
        gameTimer.setEnd();
        screen.gameOver(win, player.getScore(), gameTimer.getMins(), gameTimer.getSecs());
        
    }
}