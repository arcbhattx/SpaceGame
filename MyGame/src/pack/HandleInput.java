package pack;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HandleInput extends KeyAdapter {

    public HandleObjects handler;

    public HandleInput(HandleObjects handler)
    {
        this.handler = handler;
    }


    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();


        for(int x = 0; x < handler.objects.size(); x++)
        {

            Objects temp = handler.objects.get(x);
            if(temp.getID() == IDs.SpaceShip)
            {
                if(key == KeyEvent.VK_W)
                {
                    temp.setVelY(-5);
                }
                
                if(key == KeyEvent.VK_S)
                {
                    temp.setVelY(5);
                }
                
                if(key == KeyEvent.VK_A)
                {
                    temp.setVelX(-5);
                }
                
                if(key == KeyEvent.VK_D)
                {
                    temp.setVelX(5);
                }

                if(key == KeyEvent.VK_F)
                {
                    temp.setAddOb(true);
                }
            }


        }

    }
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for(int x = 0; x < handler.objects.size(); x++)
        {

            Objects temp = handler.objects.get(x);
            if(temp.getID() == IDs.SpaceShip)
            {
                if(key == KeyEvent.VK_W)
                {
                    temp.setVelY(0);
                }
                
                if(key == KeyEvent.VK_S)
                {
                    temp.setVelY(0);
                }
                
                if(key == KeyEvent.VK_A)
                {
                    temp.setVelX(0);
                }
                
                if(key == KeyEvent.VK_D)
                {
                    temp.setVelX(0);
                }

                if(key == KeyEvent.VK_F)
                {
                    temp.setAddOb(false);
                }

        
            }
        }

    }
}
