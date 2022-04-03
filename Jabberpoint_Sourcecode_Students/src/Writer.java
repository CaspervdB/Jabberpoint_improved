import org.w3c.dom.Element;

import java.io.IOException;

public interface Writer
{
    void saveFile(Presentation presentation, String filename) throws IOException;

    String getTitle(Element element, String tagName);

    void loadSlideItem(Slide slide, Element item);
}
