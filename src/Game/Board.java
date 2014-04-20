/**
  * Board creation
  * @author Kyle Holcomb & Luis Poza
  * @version 1.2
  */

package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    private BufferedImage ICON;
    private BufferedImage lives;
    
    protected static Android android;
    protected static Eatable apple;
    private final Color WALL_COLOR = new Color(164, 199, 57); // Android Green
    private final BasicStroke WALL_THICKNESS = new BasicStroke(5);
    private final int BLOCK_SIZE = 40; //individual tile size
    private final int NUM_BLOCKS = 15; //width
    private final int TOTAL_SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private Graphics2D g2d;
    
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
    
    //Constructor
    public Board() {
        setBackground(Color.BLACK);
    }

    /**
     * Paints all of the components of the game 
     * into the Swing Panel repeats to "animate"
     * @param g Graphics with which to paint on the Swing panel
     */ 
    @Override
    public void paintComponent(Graphics g) {
        loadCharacters();
        initializeMap(g);
        drawAndroid(g);
    }
    
    /**
     * Draws the level along with the eatables
     * @param g Graphics to paint
     * @param level Whatever level to initialize for the user
     */
    private void initializeMap(Graphics g) {
        g2d = (Graphics2D) g;
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
                    Eatable apple = new Eatable(ICON,x+10,y+10);
                    if(apple.checkAvailability() == true)
                        g2d.drawImage(apple.icon,apple.xpos,apple.ypos,this);
                }
            }
        }
        
        g2d.drawString("Score: ", 670, 90);
        g2d.drawString(Integer.toString(android.score),670,110); // score
        g2d.drawString("Lives: ", 670, 200);
        int position = 630;
        for(int i = 0; i < android.numLives; i++){
            g2d.drawImage(lives, null, position+=30, 220); // num lives
        }
        g2d.drawImage(android.icon,android.xpos,android.ypos,this); // main character
        g2d.dispose();
    }
    
    public void loadCharacters(){
        android = new Android();
        lives = android.icon;
        try{
            ICON = ImageIO.read(new File("Images/rsz_Apple.png"));
        }catch(IOException e){
            System.err.print("Error initializing the eatables.");
        }
    }
    
    public void drawAndroid(Graphics g){
        g2d.drawImage(android.icon,android.xpos,android.ypos,this);
    }
    
} // end class