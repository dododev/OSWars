/**
 * Controller class
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */

package Game;

import java.awt.event.KeyEvent;

/**
 *
 * @author Kyle
 */
public class Controller {

    /**
     *
     * @param ke
     */
    public static void onKeyPress(KeyEvent ke) {
        // Arrow keys and WASD keys move the paddle
        int keyCode = ke.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                Board.android.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                Board.android.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                Board.android.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                Board.android.moveRight();
                break;
            case KeyEvent.VK_P:
                //pause
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }
}
