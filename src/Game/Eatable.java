/**
 * Eatable objects
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.image.BufferedImage;

public class Eatable {
    boolean available; // has it been consumed yet?
    int xpos, ypos; 
    BufferedImage icon;
    
    public Eatable(int xpos, int ypos){
        this.xpos = xpos;
        this.ypos = ypos;
    }
    
    public void setPosition(){
        
    }
    
    
    public boolean checkAvailability(){
        return this.available;
    }
    
    public void changeAvailability(){
        if (this.available == true)
            available = false;
    }
    
}
