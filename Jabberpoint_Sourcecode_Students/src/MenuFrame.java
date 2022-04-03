import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * <p>The controller for the menu</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */
public class MenuFrame extends MenuBar
{

    protected static final String PAGE_NUMBER = "Page number?";
    protected static final String TEST_FILE = "testPresentation.xml";
    protected static final String FILE_NAME = "savedPresentation.xml";
    protected static final String IO_EXCEPTION = "IO Exception: ";
    protected static final String LOAD_ERROR = "Load Error";
    protected static final String SAVE_ERROR = "Save Error";
    private Frame parent; //The frame, only used as parent for the Dialogs
    private Presentation presentation; //Commands are given to the presentation

    public MenuFrame(Frame frame, Presentation pres)
    {
        parent = frame;
        presentation = pres;
        MenuItem menuItem;
        Menu fileMenu = new Menu(MenuFileDialog.FILE);
        fileMenu.add(menuItem = mkMenuItem(MenuFileDialog.OPEN));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                presentation.clear();
                XMLAccessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.loadFile(presentation, TEST_FILE);
                    presentation.setSlideNumber(0);
                } catch (IOException exc)
                {
                    JOptionPane.showMessageDialog(parent, IO_EXCEPTION + exc,
                            LOAD_ERROR, JOptionPane.ERROR_MESSAGE);
                }
                parent.repaint();
            }
        });
        fileMenu.add(menuItem = mkMenuItem(MenuFileDialog.NEW));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                presentation.clear();
                parent.repaint();
            }
        });
        fileMenu.add(menuItem = mkMenuItem(MenuFileDialog.SAVE));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                XMLAccessor xmlAccessor = new XMLAccessor();
                try
                {
                    xmlAccessor.saveFile(presentation, FILE_NAME);
                } catch (IOException exc)
                {
                    JOptionPane.showMessageDialog(parent, IO_EXCEPTION + exc,
                            SAVE_ERROR, JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(menuItem = mkMenuItem(MenuFileDialog.EXIT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                presentation.exit(0);
            }
        });
        add(fileMenu);
        Menu viewMenu = new Menu("VIEW");
        viewMenu.add(menuItem = mkMenuItem(MenuViewDialog.NEXT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                presentation.nextSlide();
            }
        });
        viewMenu.add(menuItem = mkMenuItem(MenuViewDialog.PREV));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                presentation.prevSlide();
            }
        });
        viewMenu.add(menuItem = mkMenuItem(MenuViewDialog.GOTO));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                String pageNumberStr = JOptionPane.showInputDialog((Object) PAGE_NUMBER);
                int pageNumber = Integer.parseInt(pageNumberStr);
                presentation.setSlideNumber(pageNumber - 1);
            }
        });
        add(viewMenu);
        Menu helpMenu = new Menu(MenuHelpDialog.HELP);
        helpMenu.add(menuItem = mkMenuItem(MenuHelpDialog.ABOUT));
        menuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent actionEvent)
            {
                AboutBox.show(parent);
            }
        });
        setHelpMenu(helpMenu);        //Needed for portability (Motif, etc.).
    }

    //Creating a menu-item
    public MenuItem mkMenuItem(String name)
    {
        return new MenuItem(name, new MenuShortcut(name.charAt(0)));
    }
}
