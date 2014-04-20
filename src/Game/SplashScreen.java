/**
 * Generates a splash screen intro for the user
 * @author Kyle Holcomb & Luis Poza
 * @version 1.2
 */
package Game;
 
import java.io.File;
import javax.swing.*;
 
public class SplashScreen extends JFrame {
 
    private JLabel imglabel;
    private ImageIcon img;
    private static JProgressBar pbar;
    Thread t = null;
 
    public SplashScreen() {
        super("OS Wars");
        setSize(284, 177);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        img = new ImageIcon("Images"+File.separator+"splash.jpeg");
        imglabel = new JLabel(img);
        add(imglabel);
        setLayout(null);
        imglabel.setBounds(0, 0, 284, 177);
    }
}
