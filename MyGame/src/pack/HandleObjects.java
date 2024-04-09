package pack;

import java.awt.Graphics;
import java.util.LinkedList;

public class HandleObjects {
    LinkedList<Objects> objects = new LinkedList<Objects>();

    public void tick()
    {
        for(int x = 0; x < objects.size(); x++)
        {
            Objects temp = objects.get(x);
            temp.tick();
        }
    }

    public void render(Graphics g)
    {
        for(int x = 0; x<objects.size(); x++)
        {
            Objects temp = objects.get(x);
            temp.render(g);
        }
    }

    public void addObject(Objects ob)
    {
        this.objects.add(ob);
    }

    public void removeObject(Objects ob)
    {
        this.objects.remove(ob);
    }
}
