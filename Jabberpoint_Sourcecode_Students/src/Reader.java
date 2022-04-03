import java.io.IOException;

public interface Reader
{

    void loadFile(Presentation presentation, String filename) throws IOException;
}
