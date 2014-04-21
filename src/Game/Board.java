/**
  * Board creation
  * @author Kyle Holcomb & Luis Poza
  * @version 1.2
  */

package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
    
    private BufferedImage ICON;
    private BufferedImage lives;
    private Image ii;
    
    protected static Android android;
    protected static Windows window1;
    protected static Windows window2;
    protected static Eatable apple;
    private int reqdx, reqdy, viewdx, viewdy; // ?
    
    private final Color WALL_COLOR = new Color(164, 199, 57); // Android Green
    private final BasicStroke WALL_THICKNESS = new BasicStroke(5);
    private final int BLOCK_SIZE = 40; //individual tile size
    private final int NUM_BLOCKS = 15; //width
    private final int TOTAL_SCREEN_SIZE = NUM_BLOCKS * BLOCK_SIZE;
    private Graphics2D g2d;
    
     /* Corresponding wall locations and eatable
     * 	     2
     *   1  16   4
     *       8 
     * */
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
        loadCharacters();
        setFocusable(true);
        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
    }

    /**
     * Paints all of the components of the game 
     * into the Swing Panel repeats to "animate"
     * @param g Graphics with which to paint on the Swing panel
     */ 
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	initializeMap(g);
    	playGame(g,window1);
        doDrawings(g);
    }
    
    private void doDrawings(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        initializeMap(g2d);
        drawAndroid(g2d);
        drawWindows(g2d,window1);
        drawWindows(g2d,window2);
        
        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
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
        g2d.drawString(Integer.toString(android.score),670,110); // Player score
        g2d.drawString("Lives: ", 670, 200);
        int position = 630;
        for(int i = 0; i < android.numLives; i++){
            g2d.drawImage(lives, null, position+=30, 220); // num lives
        }
        drawAndroid(g2d); // main character
        drawWindows(g2d, window1); //first window
        drawWindows(g2d, window2); //second window
        g2d.dispose();
    }
    
    /** 
     * We will have to change the way we load our images if we want to put 
     * this game in a .JAR
     */
    public void loadCharacters(){
        android = new Android();
        window1 = new Windows();
        window2 = new Windows("Images/rsz_windows.png", 8 , 5);
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
    
    public void drawWindows(Graphics g, Windows window){
    	g2d.drawImage(window.icon,window.xpos,window.ypos,this);
    }
    
    private void playGame(Graphics g, Windows window) {
    	drawWindows(g,window);
        drawAndroid(g);
    }
   
    class TAdapter extends KeyAdapter {
	@Override
        public void keyPressed(KeyEvent ke) {
            Controller.onKeyPress(ke);
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
} // end class