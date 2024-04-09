package pack;

import java.awt.Color;
import java.awt.Graphics;

public class Health {

    public static int TOTAL_HEALTH =  100;

    private static int greenValue = 255;

    private int x,y;

    public Health()
    {
        this.x = 275;
        this.y = 15;
    }
  
    public void tick()
    {

        TOTAL_HEALTH = Game.haveBounds(TOTAL_HEALTH, 0,100);
        greenValue = Game.haveBounds(greenValue,0,255);

        greenValue = TOTAL_HEALTH*2;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.drawString("Health:", x-50, y+20);
        g.drawString("Score:" + SpaceShip.TOTAL_SCORE, 500, 20);
        g.setColor(Color.gray);
        g.fillRect(x,y,200,32);
        g.setColor(new Color(75,greenValue,0));
        g.fillRect(x,y,TOTAL_HEALTH*2,32);
        g.setColor(Color.white);
        g.drawRect(x,y,200,32);
    }

}
