import java.awt.*;
import java.awt.image.ImageObserver;

public interface Item
{
     int getLevel();

    //Returns the bounding box of an Item
    Rectangle getBoundingBox(Graphics g, ImageObserver observer,
                             float scale, Style myStyle);

    //Draws the item
    void draw(int x, int y, float scale, Graphics g,
              Style myStyle, ImageObserver o);
}
