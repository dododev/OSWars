/**
 * Creates characters and maintains 
 * their positions within the game board
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character {
    public int xpos, ypos, dx, dy;
    private  String name;
    BufferedImage icon;
    
    public Character(){
    }
    
    private void setName(String newName){
        this.name = newName;
    }
    
    // changes the characters position
    private void changePosition(int change, int direction){
        if(direction == 1)
            this.xpos -= change;
        if(direction == 2)
            this.xpos += change;
        if(direction == 3)
            this.ypos += change;
        if(direction == 4)
            this.ypos -= change;
    }
    
    /*
     * For best results: icons should 
     * be approx 30px by 30px
     */
    private void setIcon(BufferedImage newIcon){
        this.icon = newIcon;
    }
}
