/**
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Windows extends Character {
	private boolean IS_ALIVE;
	private boolean IS_KILLABLE;
	
	/**
	 * 
	 */
	public Windows() {
		this("Images/rsz_windows.png", 7 , 5);
	}
	
	public Windows(String imageLocation, int xCoordinate, int yCoordinate){
		super();
        setImage(imageLocation);
        setInitialPosition(xCoordinate , yCoordinate);
        IS_ALIVE = true;
        IS_KILLABLE = false;
	}
	
    private void setImage(String imageLocation){
        try {
            icon = ImageIO.read(new File(imageLocation));
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    private void setInitialPosition(int xCoordinate, int yCoordinate){
        this.xpos = xCoordinate*40;
        this.ypos = yCoordinate*40;
    }

    public void moveUp(){
        this.ypos += 10;
    }
    
    public void moveDown(){
        this.ypos -= 10;
    }
    
    public void moveLeft(){
        this.xpos -= 10;
    }
    
    public void moveRight(){
        this.xpos += 10;
    }
    
    /** 
     * A.I. that moves randomly across the board
     * 
     */
    public void moveRandomly(){
    	Random rand = new Random(); // Will generate a random int between 1 and 4
    	int randint = rand.nextInt(4) + 1;
    	/*
    	 * Corresponding Directions
    	 *      2
    	 *   1     3
    	 *      4
    	 * */
    	switch(randint){
    		case 1:
    			this.moveLeft();
    			break;
    		case 2:
    			this.moveUp();
    			break;
    		case 3:
    			this.moveRight();
    			break;
    		case 4:
    			this.moveDown();
    			break;
    	}
    }
    
    /*
    public void moveRecursively(Android android){
    	if((android.xpos - this.xpos == 0) && (android.ypos - this.ypos == 0))
    		
    	if(android.xpos - this.xpos <0){
    		moveRecursively(android
    	}
    	
    }
    */
}
