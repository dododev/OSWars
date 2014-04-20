/**
  * @author Kyle Holcomb & Luis Poza
  * @version 1.0
  */

package Game;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {

	public Main() {
        add(new Board());
        setTitle("OS WARS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 625);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);  
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        });
    }
}
