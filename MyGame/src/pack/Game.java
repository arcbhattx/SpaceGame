package pack;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;



public class Game extends Canvas implements Runnable{

    public static int HEIGHT = 600;
    public static int WIDTH = 800;
    
    private Thread thread;

    private boolean isRunning = false;


    private HandleObjects ho;

    private Random rand;

    private Timer timer;
    private TimerTask task;
    private TimerTask fuel_task;

    private Health h;
    private Fuel fuel;


    private int FPS;

    private Menu main_menu;

    public enum PANEL
    {
        Game,
        GameOver,
        Main_Menu;
    }

    public PANEL currentPanel = PANEL.Main_Menu;


    private BufferedImage back_image;

    public Game()
    {
        open_Image();
        FPS = 0;
        h = new Health();
        fuel = new Fuel();
        timer = new Timer();
        rand = new Random();
        

        ho = new HandleObjects();

        main_menu = new Menu(this,ho);

        this.addKeyListener(new HandleInput(ho));
        this.addMouseListener(main_menu);


      

        new Window(HEIGHT, WIDTH, this); 


        if(currentPanel == PANEL.Game)

        {
            ho.addObject(new SpaceShip(50, 50, IDs.SpaceShip,ho));

            task = new TimerTask() {
        
                    public void run() {
                    for(int x = 0; x < 5; x++)
                        ho.addObject(new Asteriod(rand.nextInt( 600,Game.WIDTH), rand.nextInt(0,Game.HEIGHT), IDs.Asteroids, rand.nextInt(2,8),ho));
                    }
                    
                };

            fuel_task = new TimerTask() {

                public void run()
                {
                    ho.addObject(new FuelPack(rand.nextInt( 600,Game.WIDTH), rand.nextInt(0,Game.HEIGHT), IDs.FuelPack,ho));
                }
            };

            timer.scheduleAtFixedRate(task, 0,1000);

            timer.scheduleAtFixedRate(fuel_task, 0, 10000);
        }
    

        
        
    }

    public void run() {
        this.requestFocus();    
        long lastTime = System.nanoTime();
        double amountOfTicks = 120.0;
        double ns = 1000000000/amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now-lastTime) /ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            if(isRunning)
            {
                render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                FPS = frames;
                frames = 0;
            }
        }

        stop();
    }

    private void tick()
    {
        ho.tick();

        if(currentPanel == PANEL.Game)
        {
            h.tick();
            fuel.tick();
        }
        else if(currentPanel == PANEL.Main_Menu)
        {
            main_menu.tick();
        }
    }

    private void render()
    {   
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
                this.createBufferStrategy(3);
                return;
        }

        Graphics g = bs.getDrawGraphics();
        

        g.setColor(Color.BLACK);
        g.drawImage(back_image, 0, 0, WIDTH, HEIGHT, null);

        
        ho.render(g);


        if(currentPanel == PANEL.Game)
        {
            h.render(g);
            fuel.render(g);

            g.drawString("FPS: " + FPS + "", 0, 10);
            g.setColor(Color.white);
            g.drawLine(200, 600, 200, 0);
        }
 
        else if(currentPanel == PANEL.Main_Menu)
        {
            main_menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    public void open_Image()
    {
          try {
            back_image = ImageIO.read(new File("MyGame\\src\\pack\\Images\\background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void stop()
    {
        try{
            thread.join();
            isRunning = false;

        }catch(Exception e)
        {
            e.printStackTrace();;
        }
    }
    

    public static int haveBounds(int var, int min, int max)
    {
        if (var >= max)
            return var = max;
        else if(var <= min)
            return var = min;
        else 
            return var;
    }
    
    public static void main(String[] args) {
        new Game();
        
    }
    
}
