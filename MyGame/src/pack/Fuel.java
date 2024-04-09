package pack;

import java.awt.Graphics;
import java.awt.Color;

public class Fuel {

    public static int TOTAL_FUEL =  50;

    private static int fuel_value = 255;

    private int x,y;
    public Fuel()
    {
        this.x = 50;
        this.y = 500;
    }
  
    public void tick()
    {

        TOTAL_FUEL = Game.haveBounds(TOTAL_FUEL, 0,100);
        fuel_value = Game.haveBounds(fuel_value,0,255);

        fuel_value = TOTAL_FUEL*2;
    }

    public void render(Graphics g)
    {   
        g.setColor(Color.WHITE);
        g.drawString("Fuel:", x, y-16);
        g.setColor(Color.gray);
        g.fillRect(x,y,100,32);
        g.setColor(new Color(fuel_value,75,0));
        g.fillRect(x,y,TOTAL_FUEL*2,32);
        g.setColor(Color.white);
        g.drawRect(x,y,100,32);
    }

}
