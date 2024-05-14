package pack;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Laser extends Objects{

    HandleObjects handler;

    public BufferedImage laser_image;

    public Laser(int x, int y, IDs id, HandleObjects handler) {
        super(x, y, id);
        this.handler = handler;

        this.open_Image();
    }




    public void tick() {
        x += 3;

        if(x >= Game.WIDTH)
        {
            handler.objects.remove(this);
        }

        collidedWithAsteroid();

    }

    public void open_Image()
    {
          try {
            laser_image = ImageIO.read(new File("MyGame\\src\\Images\\las-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void collidedWithAsteroid()
    {
        for(int x = 0; x<handler.objects.size(); x++)
        {
            Objects temp = handler.objects.get(x);
            if(temp.getID() == IDs.Asteroids)
            {
                if(this.getBounds().intersects(temp.getBounds()))
                {
                    handler.objects.remove(temp);
                    SpaceShip.TOTAL_SCORE++;
                }
            }
        }
    }

    public void render(Graphics g) {

        //g.setColor(Color.ORANGE);
        //g.fillRect(x, y, 20, 10);

        g.drawImage(laser_image, x, y, 20,10,null);

    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,20,10);
    }
    
}
