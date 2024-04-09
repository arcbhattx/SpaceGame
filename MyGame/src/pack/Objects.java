package pack;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Objects {


    protected int x,y;
    protected IDs id;
    protected int velx, vely;
    protected boolean add_ob;

    public Objects(int x, int y, IDs id)
    {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setVelX(int velx)
    {
        this.velx = velx;
    }
    
    public void setVelY(int velY)
    {
        this.vely = velY;
    }

    public void setID(IDs id)
    {
        this.id = id;
    }

    public void setAddOb(boolean add_ob)
    {
        this.add_ob = add_ob;
    }
    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getVelX()
    {
        return this.velx;
    }

    public int getVelY()
    {
        return this.vely;
    }

    public IDs getID()
    {
       return this.id;
    }

    public boolean getAddOb()
    {
        return this.add_ob;
    }
}
