/**
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Android extends Character{
    private int score;
    protected int numLives = 3;
    
    public Android(){
        super();
        setImage();
        setPrimaryPosition();
    }
    
    private void setImage(){
        try {
            icon = ImageIO.read(new File("Images/rsz_Android.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    private void setPrimaryPosition(){
        this.xpos = 10;
        this.ypos = 10;
    }
    
    public void changeScore(int difference){
        this.score += difference;
    }
    
    public int getScore(){
        return score;
    }
    
}
