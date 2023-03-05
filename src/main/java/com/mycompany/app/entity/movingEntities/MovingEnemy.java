package com.mycompany.app.entity.movingEntities;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * The MovingEnemy Class. It extends the MovingEntity class.
 * It handles all logic dealing with moving enemies inlcuding pathfinding
 * and what to do if the player is caught.
 * 
 * @author Sukhraj
 */
public class MovingEnemy extends MovingEntity {

    Stack<Point> path;
    boolean check = true;
    Queue<Point>gobak;
    public Boolean boolmap[][];
    
    /**
     * The constructor for a moving enemy. It calls the movingEntity constructor and
     * creates a 2d boolean array of the map.
     * 
     * @param x The x position of the enemy
     * @param y The y position of the enemy
     * @param imgplace The name of the image file for the entity
     * @param map The 2d integer array of the board map
     */
    public MovingEnemy(int x, int y, String imgplace, int map1[][]) {
        super(x, y, imgplace);
        path = new Stack<>();
        gobak = new LinkedList<>();
        
        boolmap = new Boolean[map1.length][map1[0].length];
        for (int i = 0; i < map1.length; i++) {
            for (int j = 0; j < map1[0].length; j++) {
                if(map1[i][j]!=1){
                    boolmap[i][j] = true;
                }

                else{
                    boolmap[i][j] = false;
                }
                
            }
        }
        

       
    }

    /**
     * This function emptie the stack of points the moving enemy has been to and attempts to 
     * find a new spot for the enemy to go ot.
     * 
     * @param map The map of the game as a 2d boolean array
     * @param p 
     * @param map1 The map of the game as a 2d integer array
     * @return
     */
    public Queue<Point> goback(){
        
        Queue<Point> q = new LinkedList<>();
        while (!path.isEmpty()) {
            
            if (boolmap[path.peek().y][path.peek().x-1] || (boolmap[path.peek().y][path.peek().x+1]) || (boolmap[path.peek().y+1][path.peek().x]) || (boolmap[path.peek().y-1][path.peek().x])) {
               
                q.add(path.peek());
                break;
            }
            
            else {
                
                q.add(path.peek());
            }
            
            path.pop();
        }
        return q;
    }

    /**
     * A boolean to check if a moving enemy has caught the player. It checks the position of the player
     * and if that position matches the position of the moving enemy, it returns a true boolean, otherwise false.
     * 
     * @param pos1 The position of the player
     * @return true if the player was caught false otherwise
     */
    public boolean iscaught(Point pos1) {
        int diffx = pos1.x - pos.x;
        //add diffy
        int diffy = pos1.y - pos.y;
        if ((Math.abs(diffy) == 1 && Math.abs(diffx) == 0) || (Math.abs(diffx) == 1 && Math.abs(diffy) == 0 )) {
            System.out.println(diffx + " " + diffy);
            return true;
        }
        return false;
    }

    /**
     * A pathfinding function for the moving enemies to catch the knight. This function takes 
     * the difference between the knights position and the enemies position and has the moving enemy 
     * go in the direction thats available and closest to the player.
     * 
     * @param knight the player
     */
    public void catchknight(Player knight) {
        
        int diffx = knight.getPos().x - pos.x;
        //add diffy
        int diffy = knight.getPos().y - pos.y;
        if (Math.abs(diffy) >= Math.abs(diffx)) {
            if (diffx < 0) {
                if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
    
                }
            }
    
            else if (diffx > 0) {
                if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
    
    
    
            else if (diffy < 0) {
                if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
    
            else if (diffy > 0) {
                if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
        }

        else {
            if (diffy < 0) {
                if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
    
    
            else if (diffy > 0) {
    
                if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
        
            else if (diffx < 0) {
                if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
    
                }
            }
    
            else if (diffx > 0) {
                if ( boolmap[pos.y][pos.x+1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(1, 0);
                }
    
                else if ( boolmap[pos.y][pos.x-1]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(-1, 0);
                }
    
                else if ( boolmap[pos.y-1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, -1);
                }
    
                else if ( boolmap[pos.y+1][pos.x]) {
                    boolmap[pos.y][pos.x] = false;
                    Point pos1 = new Point(pos);
                    path.push(pos1);
                    pos.translate(0, 1);
                }
    
                else {
                    boolmap[pos.y][pos.x] = false;
                    if (gobak.isEmpty()) {
                        Point pos1 = new Point(pos);
                        path.push(pos1);
                        gobak = goback();
                    }
    
                    else {
                        pos.move(gobak.peek().x, gobak.peek().y);
                        gobak.remove();
                    }
                }
            }
        }
    }
}
