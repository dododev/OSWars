/**
 * Creates characters and maintains 
 * their positions within the game board
 * @author Kyle Holcomb & Luis Poza
 * @version 1.1
 */

package Game;

import java.awt.image.BufferedImage;

public class Character {
    public int xpos,ypos,dx,dy;
    private  String name;
    BufferedImage icon;
    
    public Character(){
    }
    
    private void setName(String newName){
        this.name = newName;
    }
    
    /** 
     * Changes the characters position
     * @param change The amount of change in 2D space with 
     * which to move the character
     * @param direction The direction in which to 
     * change the players location
     */
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
    
    // Checks to verify that character is within the board
    public void checkPosition(){
        if(this.xpos > 600 || this.ypos > 600  || this.xpos < 0 || this.ypos < 0){
            if (this.xpos > 600){
                this.xpos -=10;
            }
            if (this.ypos > 600){
                this.ypos -=10;
            }
            if (this.xpos < 0){
                this.xpos +=10;
            }
            if (this.ypos < 0){
                this.ypos +=10;
            }
        }
    
    }
}
