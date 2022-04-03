import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.Vector;

/**
 * <p>A slide. This class has drawing functionality.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Slide
{
    public final static int WIDTH = 1200;
    public final static int HEIGHT = 800;
    protected String title; //The title is kept separately
    protected Vector<Item> items; //The SlideItems are kept in a vector

    public Slide()
    {
        items = new Vector<Item>();
    }

    //Add a SlideItem
    public void append(Item anItem)
    {
        items.addElement(anItem);
    }

    //Return the title of a slide
    public String getTitle()
    {
        return title;
    }

    //Change the title of a slide
    public void setTitle(String newTitle)
    {
        title = newTitle;
    }

    //Create a TextItem out of a String and add the TextItem
    public void append(int level, String message)
    {
        append(new TextItem(level, message));
    }

    //Returns the SlideItem
    public Item getSlideItem(int number)
    {
        return (Item) items.elementAt(number);
    }

    //Return all the SlideItems in a vector
    public Vector<Item> getSlideItems()
    {
        return items;
    }

    //Returns the size of a slide
    public int getSize()
    {
        return items.size();
    }

    //Draws the slide
    public void draw(Graphics g, Rectangle area, ImageObserver view)
    {
        float scale = getScale(area);
        int y = area.y;
        //The title is treated separately
        Item item = new TextItem(0, getTitle());
        Style style = Style.getStyle(item.getLevel());
        item.draw(area.x, y, scale, g, style, view);
        y += item.getBoundingBox(g, view, scale, style).height;
        for (int number = 0; number < getSize(); number++)
        {
            item = (Item) getSlideItems().elementAt(number);
            style = Style.getStyle(item.getLevel());
            item.draw(area.x, y, scale, g, style, view);
            y += item.getBoundingBox(g, view, scale, style).height;
        }
    }

    //Returns the scale to draw a slide
    private float getScale(Rectangle area)
    {
        return Math.min(((float) area.width) / ((float) WIDTH), ((float) area.height) / ((float) HEIGHT));
    }
}
