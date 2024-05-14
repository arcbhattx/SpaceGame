package pack;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FuelPack extends Objects{

    private HandleObjects handler;

    public BufferedImage fuel_image;

    public FuelPack(int x, int y, IDs id, HandleObjects handler) {
        super(x, y, id);
        this.handler = handler;

        this.open_Image();
    }

    public void tick() {

        x -= 5;

        if(x <= 0)
        {
            handler.objects.remove(this);
        }
    }
     public void open_Image()
    {
          try {
            fuel_image = ImageIO.read(new File("MyGame\\src\\Images\\fuel-removebg-preview.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void render(Graphics g) {

        g.setColor(Color.YELLOW);
        //g.fillRect(x, y, 20, 30);
        g.drawImage(fuel_image, x, y, 20,30,null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,20,30);
    }
    
}
