/**
 * Controller class
 * @author Kyle Holcomb & Luis Poza
 * @version 1.1
 */

package Game;

import java.awt.event.KeyEvent;

public class Controller {

    /**
     *
     * @param ke
     */
    public static void onKeyPress(KeyEvent ke) {
        // Arrow keys move the paddle
        int keyCode = ke.getKeyCode();
        switch(keyCode){
            case KeyEvent.VK_UP:
                Board.android.moveUp();
                Board.android.checkPosition();
                break;
            case KeyEvent.VK_DOWN:
                Board.android.moveDown();
                Board.android.checkPosition();
                break;
            case KeyEvent.VK_LEFT:
                Board.android.moveLeft();
                Board.android.checkPosition();
                break;
            case KeyEvent.VK_RIGHT:
                Board.android.moveRight();
                Board.android.checkPosition();
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
