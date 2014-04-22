/**
  * Runnable which begins the game
  * @author Kyle Holcomb & Luis Poza
  * @version 1.3
  */

package Game;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Main extends JFrame {

    public Main() {
        add(new Board());
        setTitle("OS WARS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,700);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);  
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        SplashScreen splash = new SplashScreen();
        Controller c = new Controller();
        splash.setVisible(true);
        
        playIntroSound();
        Thread.sleep(4000); // Keeps the splash on screen for 4 seconds
        splash.dispose();
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                new Main();
            }
        });
    }
    
    /**
     * Plays an introduction sound for the game 
     * .wav is the only acceptable format
     */
    public static void playIntroSound(){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sounds/droid.wav").getAbsoluteFile());
            Clip SOUNDBYTE = AudioSystem.getClip();
            SOUNDBYTE.open(ais);
            SOUNDBYTE.start();
        }catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
            System.out.println("Error playing intro sound.");
        }
    }
    
}
