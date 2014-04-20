/**
  * Board creation
  * @author Kyle Holcomb & Luis Poza
  * @version 1.1
  */

package Game;

import java.awt.*;
import java.util.Random;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    BufferedImage windows, apple, android;
    
    private final Color WALL_COLOR = new Color(255, 128, 5);
    private final BasicStroke WALL_THICKNESS = new BasicStroke(5);
    private final int BLOCK_SIZE = 40; //tile size
    private final int NUM_BLOCKS = 15; //width
    private final int TOTAL_SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private final String soundByte = "droid.wav";
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
     * into the Swing Panel
     */ 
    @Override
    public void paintComponent(Graphics g) {
        try{
            playIntroSound();
            super.paintComponent(g);
            initializeMap(g,1);
        }catch(Exception ex){
            ex.getStackTrace();
            ex.getMessage();
        }
    }
    
    public void playIntroSound(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/droid.wav").getAbsoluteFile());
            Clip soundByte = AudioSystem.getClip();
            soundByte.open(ais);
            soundByte.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
            System.out.println("Error playing intro sound.");
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
                if ((level2[y/BLOCK_SIZE][x/BLOCK_SIZE] & 16) != 0) { 
                    Eatable apple = new Eatable(x+10,y+10);
                    g2d.drawImage(apple.icon,apple.xpos,apple.ypos,this);
                }
            }
        }
        g2d.dispose();
    }
    
    public void checkCharacterLocations(){
        // verify all character locations are valid
    }
    
    public void loadCharacters(){
        Android android = new Android();
        
    }
    
    public void loadEatables(){
        
    }
} // end class