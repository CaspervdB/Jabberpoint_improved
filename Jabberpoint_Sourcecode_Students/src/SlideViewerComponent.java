import javax.swing.*;
import java.awt.*;


/**
 * <p>SlideViewerComponent is a graphical component that ca display Slides.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class SlideViewerComponent extends JComponent
{

    private Slide slide; //The current slide

    private Presentation presentation = null; //The presentation
    private JFrame frame = null;
    private Font labelFont = null;


    public SlideViewerComponent(Presentation pres, JFrame frame)
    {
        setBackground(SlideViewerColor.BACKGROUND_COLOR);
        presentation = pres;
        labelFont = new Font(SlideViewerFont.FONTNAME, SlideViewerFont.FONTSTYLE, SlideViewerFont.FONTHEIGHT);
        this.frame = frame;
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(Slide.WIDTH, Slide.HEIGHT);
    }

    public void update(Presentation presentation, Slide data)
    {
        if (data == null)
        {
            repaint();
            return;
        }
        this.presentation = presentation;
        this.slide = data;
        repaint();
        frame.setTitle(presentation.getTitle());
    }

    //Draw the slide
    public void paintComponent(Graphics g)
    {
        g.setColor(SlideViewerColor.BACKGROUND_COLOR);
        g.fillRect(0, 0, getSize().width, getSize().height);
        if (presentation.getSlideNumber() < 0 || slide == null)
        {
            return;
        }
        g.setFont(labelFont);
        g.setColor(SlideViewerColor.COLOR);
        g.drawString("Slide " + (1 + presentation.getSlideNumber()) + " of " +
                presentation.getSize(), SlideViewerPosition.X_AXIS_POSITION, SlideViewerPosition.Y_AXIS_POSITION);
        Rectangle area = new Rectangle(0, SlideViewerPosition.Y_AXIS_POSITION, getWidth(), (getHeight() - SlideViewerPosition.Y_AXIS_POSITION));
        slide.draw(g, area, this);
    }
}
