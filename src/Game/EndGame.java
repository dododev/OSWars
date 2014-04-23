/**
 * End Game Screen
 * @author Kyle Holcomb & Luis Poza
 */
package Game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.*;

public class EndGame extends JFrame{
    
    private JLabel imglabel;
    private ImageIcon img;
    
    /**
     *
     */
    public EndGame() {
        super("YOU LOSE!");
        setSize(600, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        img = new ImageIcon("Images"+File.separator+"loser.png");
        imglabel = new JLabel(img);
        add(imglabel);
        setLayout(null);
        imglabel.setBounds(0, 0, 600, 450);
    }
}
