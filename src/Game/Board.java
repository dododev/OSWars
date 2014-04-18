
/**
  * @author Kyle Holcomb & Luis Poza
  * @version 1.0
  */

package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    BufferedImage windows, apple, android;
    
    private static final long serialVersionUID = 1L;
    private final Color WALL_COLOR = new Color(255, 128, 5);
    private final BasicStroke WALL_THICKNESS = new BasicStroke(4);
    private final Color APPLE_COLOR = new Color(128, 128, 128);
    private final int BLOCK_SIZE = 40; //tile size
    private final int NUM_BLOCKS = 15; //width
    private final int TOTAL_SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private final short objectPlacement[][] = {
        {19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 3, 2, 2, 6, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 1, 0, 0, 4, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 1, 0, 0, 4, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 9, 8, 8, 12, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28}
    };
    
    //Constructor
    public Board() {
        setBackground(Color.black);
    }

    /*
     * Paints all of the components of the game 
     * into the Swing Panel
     */ 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        getImages();
        initializeMap(g);
    }
    
    /*
     * Draws the level along with the eatables
     */
    private void initializeMap(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (int y = 0; y < TOTAL_SCREEN_SIZE; y += BLOCK_SIZE){     // draw in y direction
            for (int x = 0; x < TOTAL_SCREEN_SIZE; x += BLOCK_SIZE){ // draw in x direction
            	
                g2d.setColor(WALL_COLOR);
                g2d.setStroke(WALL_THICKNESS);
                
                if ((objectPlacement[y/BLOCK_SIZE][x/BLOCK_SIZE] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((objectPlacement[y/BLOCK_SIZE][x/BLOCK_SIZE] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((objectPlacement[y/BLOCK_SIZE][x/BLOCK_SIZE] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((objectPlacement[y/BLOCK_SIZE][x/BLOCK_SIZE] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                if ((objectPlacement[y/BLOCK_SIZE][x/BLOCK_SIZE] & 16) != 0) { 
                    g2d.setColor(APPLE_COLOR); // swap for apple logo
                    g2d.fillRect(x + 11, y + 11, 10, 10);
                }
            }
        }
        g2d.drawImage(windows, 200,  200, this);
        g2d.drawImage(android, 100 , 100, this);
        g2d.drawImage(apple  , 50  ,  50, this);
        g2d.dispose();
    }
    
    /*
     * Loads the images necessary for gameplay
     */
    public void getImages(){
        try {
            windows = ImageIO.read(new File("Images/rsz_Windows.png"));
            apple = ImageIO.read(new File("Images/rsz_Apple.png"));
            android = ImageIO.read(new File("Images/rsz_Android.png"));
            
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
} // end class