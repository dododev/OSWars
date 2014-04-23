/**
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Android extends Character{

    public int score;
    public int numLives = 3;
    
    public Android(){
        super();
        setImage();
        setInitialPosition();
    }
    
    private void setImage(){
        try {
            icon = ImageIO.read(new File("Images/rsz_Android.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    private void setInitialPosition(){
        this.xpos = 10;
        this.ypos = 10;
    }
    
    /**Getter for numLives
     * @return numLives
     */
    public int getLives(){
    	return numLives;
    }
    
    /**
     * If the android died, it decrements the number of lives
     */
    public void andriodDied(){
    	if(numLives > 0){
    		numLives--;
    	}
    }
    
    /**
     *
     * @param difference the positive change of points
     */
    public void changeScore(int difference){
        this.score += difference;
    }
    
    /**
     *
     * @return score The players current score
     */
    public int getScore(){
        return score;
    }
    
    /*MoveUp and Move down are inverted because of how we defined the coordinates, so I changed them*/
     public void moveUp(){
        this.ypos -= 40;
    }
    
    public void moveDown(){
        this.ypos += 40;
    }
    
    public void moveLeft(){
        this.xpos -= 40;
    }
    
    public void moveRight(){
        this.xpos += 40;
    }
}
