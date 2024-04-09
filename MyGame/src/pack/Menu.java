package pack;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import pack.Game.PANEL;

public class Menu extends MouseAdapter {

    private BufferedImage play_image; 
    Game game;
    HandleObjects handler;

    private Timer timer;
    private TimerTask task;
    private TimerTask fuel_task;

    
    private Random rand;

    public Menu(Game game, HandleObjects handler)
    {
        open_Image();
        this.game = game;
        this.handler = handler;
        timer = new Timer();
        rand = new Random();
    }


    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX();
        int my = e.getY();

        if(selected(mx, my, 280, 250, 200, 100) && game.currentPanel != PANEL.Game)
        {
            game.currentPanel = PANEL.Game;

            handler.addObject(new SpaceShip(50, 50, IDs.SpaceShip,handler));

            task = new TimerTask() {
        
                    public void run() {
                    for(int x = 0; x < 5; x++)
                        handler.addObject(new Asteriod(rand.nextInt( 600,Game.WIDTH), rand.nextInt(0,Game.HEIGHT), IDs.Asteroids, rand.nextInt(2,8),handler));
                    }
                    
                };

            fuel_task = new TimerTask() {

                public void run()
                {
                    handler.addObject(new FuelPack(rand.nextInt( 600,Game.WIDTH), rand.nextInt(0,Game.HEIGHT), IDs.FuelPack,handler));
                }
            };

            timer.scheduleAtFixedRate(task, 0,1000);

            timer.scheduleAtFixedRate(fuel_task, 0, 10000);
        }
    }
    public void mouseReleased(MouseEvent e)
    {
        
    }

    private boolean selected(int mx, int my, int x, int y, int width, int height)
    {
        if(mx > x && mx < x + width)
            if(my > y && my < y + height){
                return true;
            }else{
                return false;
            }
        else{
            return false;
        }
    }

    public void open_Image()
    {
          try {
            play_image = ImageIO.read(new File("MyGame\\src\\pack\\Images\\play-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tick()
    {

    }

    public void render(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect(200, 135, 400, 100);
        g.setColor(Color.black);
        g.drawRect(200, 135, 400, 100);
        Font font = new Font("Monospaced", 1,50);
        g.setFont(font);
        g.setColor(Color.black);
        g.drawString("SPACE RAIDER", 210, 200);
        g.drawImage(play_image, 280, 250,200,100 ,null);
    }
}
