package pack;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpaceShip extends Objects {

    HandleObjects handler;

    public BufferedImage ship_image;

    public static int TOTAL_SCORE;


    public SpaceShip(int x, int y, IDs id,HandleObjects handler) {
        super(x, y, id);
        this.handler = handler;
        this.open_Image();
        TOTAL_SCORE = 0;
    }


    public void collided()
    {
        for(int x = 0; x<handler.objects.size(); x++)
        {
            Objects temp = handler.objects.get(x);
            if(temp.getID() == IDs.Asteroids)
            {
                if(this.getBounds().intersects(temp.getBounds()))
                {
                    Health.TOTAL_HEALTH -=5;
                    handler.objects.remove(temp);

                    if(Health.TOTAL_HEALTH <= 0)
                    {
                        System.exit(x);
                    }
                }
            }

            if(temp.getID() == IDs.FuelPack)
            {
                if(this.getBounds().intersects(temp.getBounds()))
                {
                    Fuel.TOTAL_FUEL = 50;
                    handler.objects.remove(temp);
                }
            }
        }

        
    }

    public void tick() {

        x += velx;
        y += vely;

        x = Game.haveBounds(x, 0, 150);
        y = Game.haveBounds(y,0,Game.HEIGHT);

        collided();

        if(add_ob && Fuel.TOTAL_FUEL > 0)
        {
            Fuel.TOTAL_FUEL -= 1;
            handler.addObject(new Laser(x, y, IDs.Laser,handler));
        }

    }

    public void open_Image()
    {
          try {
            ship_image = ImageIO.read(new File("MyGame\\src\\Images\\ship-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        
       // g.setColor(new Color(160,32,240));
        //g.fillOval(x, y, 32, 32);
        g.drawImage(ship_image, x, y,40,40,null);
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,40,40);
    }
    
}
