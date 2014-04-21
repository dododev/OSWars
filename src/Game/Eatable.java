/**
 * Eatable objects
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.image.BufferedImage;

/**
 *
 * @author Kyle
 */
public class Eatable {
    boolean available; // has it been consumed yet?
    int xpos, ypos; 
    BufferedImage icon;
    
    /**
     *
     */
    public Eatable(){
    }
    
    /**
     *
     * @param icon
     * @param xpos
     * @param ypos
     */
    public Eatable(BufferedImage icon, int xpos, int ypos){
        this.icon = icon;
        this.xpos = xpos;
        this.ypos = ypos;
        available = true;
    }
    
    /**
     *
     * @return
     */
    public boolean checkAvailability(){
        return this.available;
    }
    
    /**
     *
     */
    public void changeAvailability(){
        if (this.available == true)
            available = false;
    }
    
}
