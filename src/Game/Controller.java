/**
 * Controller class
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.event.KeyEvent;

public class Controller {
    
    public void onKeyPress(KeyEvent ke) {
        // Arrow keys and WASD keys move the paddle
        int keyCode = ke.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                
                //handle up
                break;
            case KeyEvent.VK_DOWN:
                //handle down
                break;
            case KeyEvent.VK_LEFT:
                //handle left
                break;
            case KeyEvent.VK_RIGHT:
                //handle right
                break;
        }
    }
}
