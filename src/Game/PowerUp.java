/**
 * Power-ups available to the Android character
 * @author Kyle Holcomb & Luiz Poza
 * @version 1.0
 */

package Game;

public class PowerUp extends Eatable {
    int type; // 1 = speed 2 = invincibility
   
    public PowerUp(){
        super();
    }
    
    private void move(){
        if (type == 1)
            this.xpos +=1; // some powerups move randomly around the map
        if (type == 2){
            this.xpos = 1;
            this.ypos = 1;
        }
    }
    
}
