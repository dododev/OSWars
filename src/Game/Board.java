/**
  * Board creation
  * @author Kyle Holcomb & Luis Poza
  * @version 1.2
  */

package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    private BufferedImage ICON;
    private Android android;
    private final Color WALL_COLOR = new Color(164, 199, 57); // Android Green
    private final BasicStroke WALL_THICKNESS = new BasicStroke(5);
    private final int BLOCK_SIZE = 40; //tile size
    private final int NUM_BLOCKS = 15; //width
    private final int TOTAL_SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    
    private final short level1[][] = {
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
    
    private final short level2[][] = {
        {19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 3,   2,  2,  6, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 1,   0,  0,  4, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 1,   0,  0,  4, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 9,   8,  8, 12, 16, 16, 16, 20},
        {17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 3,   2,  2,  6, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 1,   0,  0,  4, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 1,   0,  0,  4, 16, 16, 16, 16, 16, 16, 16, 20},
        {17, 16, 16, 9,   8,  8, 12, 16, 16, 16, 16, 16, 16, 16, 20},
        {25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 28}
    };
    
    //Constructor
    public Board() {
        setBackground(Color.black);
    }

    /*
     * Paints all of the components of the game 
     * into the Swing Panel repeats to "animate"
     */ 
    @Override
    public void paintComponent(Graphics g) {
        try{
            super.paintComponent(g);
            initializeMap(g,1);
        }catch(Exception ex){
            ex.getStackTrace();
        }
    }
    
    /*
     * Draws the level along with the eatables
     */
    private void initializeMap(Graphics g,int level) {
        Graphics2D g2d = (Graphics2D) g;
        for (int y = 0; y < TOTAL_SCREEN_SIZE; y += BLOCK_SIZE){     // draw in y direction
            for (int x = 0; x < TOTAL_SCREEN_SIZE; x += BLOCK_SIZE){ // draw in x direction
            	
                g2d.setColor(WALL_COLOR);
                g2d.setStroke(WALL_THICKNESS);
                
                if ((level1[y/BLOCK_SIZE][x/BLOCK_SIZE] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((level1[y/BLOCK_SIZE][x/BLOCK_SIZE] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((level1[y/BLOCK_SIZE][x/BLOCK_SIZE] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((level1[y/BLOCK_SIZE][x/BLOCK_SIZE] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                if ((level1[y/BLOCK_SIZE][x/BLOCK_SIZE] & 16) != 0) { 
                    try{ICON = ImageIO.read(new File("Images/rsz_Apple.png"));}
                    catch(Exception e){}
                    Eatable apple = new Eatable(ICON,x+10,y+10);
                    g2d.drawImage(apple.icon,apple.xpos,apple.ypos,this);
                }
            }
        }
        g2d.drawImage(android.icon,android.xpos,android.ypos,this);
        g2d.dispose();
    }
    
    public void checkCharacterLocations(){
        // verify all character locations are valid
    }
    
    public void loadCharacters(){
        android = new Android();
    }
    
} // end class