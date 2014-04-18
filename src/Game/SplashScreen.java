
/**
 * Generates a splash screen intro for the user
 * @author Kyle Holcomb & Luis Poza
 * @version 1.0
 */
package Game;

import java.awt.*;

public class SplashScreen extends Frame{
    
    static void renderSplashFrame(Graphics2D g, int frame){
        final String[] comps ={"almost","done","rendering"};
        g.setComposite(AlphaComposite.Clear);
        g.fillRect(120, 140, 200, 40);
        g.setPaintMode();
        g.setColor(Color.BLACK);
        g.drawString("Loading " +comps[(frame/5)%3]+"...", 120, 150);
    }
    
    public SplashScreen(){
        super("OS Wars");
        setSize(300,200);
        setLayout(new BorderLayout());
        final SplashScreen splash = SplashScreen.getSplashScreen();
        Graphics2D g = splash.createGraphics();
        //final SplashScreen splash = SplashScreen.getSplashScreen();
        for (int i = 0; i<100; i++){
            renderSplashFrame(g,i);
            splash.update();
            try{
                Thread.sleep(90);
            }catch(InterruptedException e){
            }
        }
        splash.close();
        setVisible(true);
        toFront();
    }
    
    public static void main (String args[]){
    SplashScreen test = new SplashScreen();
    }
}
