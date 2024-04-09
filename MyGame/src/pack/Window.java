package pack;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;


public class Window extends Canvas{


    public Window(int HEIGHT, int WIDTH, Game game)
    {
        JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
        frame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        
        frame.setVisible(true);

        game.start();
    }
    
}
