import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileCow extends Cow {
    public FileCow(String name, String filename) {
        super(name);

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            String image = "";

            for(String line : lines)
                image += line + '\n';

            // strip the final newline character
            // to match the expected output
            image = image.substring(0, image.length()-1);

            super.setImage(image);
        } catch(IOException e) {
            throw new RuntimeException("MOOOOO!!!!!!");
        }
        
    }

    public void setImage() {
        throw new RuntimeException("Cannot reset FileCow Image");
    }
}