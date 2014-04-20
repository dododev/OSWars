/**
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Android extends Character{
    private int score;
    
    public void Android(){
        //super();
        setImage();
    }
    
    public void setImage(){
        try {
            icon = ImageIO.read(new File("Images/rsz_Android.png"));
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
    
    public void changeScore(){
        this.score += 100;
    }
    
    public int getScore(){
        return score;
    }
    
}
