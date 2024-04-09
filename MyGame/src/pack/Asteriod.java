package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Asteriod extends Objects{

    public int x_shift;
    HandleObjects handler;

    public BufferedImage asteroid_image;
    

    public Asteriod(int x, int y, IDs id,int x_shift,HandleObjects handler) {
        super(x, y, id);

        this.x_shift = x_shift;
        this.handler = handler;

        this.open_Image();
    }


     public void open_Image()
    {
          try {
            asteroid_image = ImageIO.read(new File("MyGame\\src\\pack\\Images\\asteroid-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void tick() {
        x -= x_shift;

        if(x <= 0)
        {
            handler.objects.remove(this);
        }
            
    }




    public void render(Graphics g) {
        
        g.setColor(Color.GRAY);
        //g.fillOval(x,y,40,40);
        g.drawImage(asteroid_image, x, y, 40,40,null);
        
    }


    public Rectangle getBounds() {
        return new Rectangle(x,y,40,40);
    }
    
}