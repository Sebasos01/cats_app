package PersonalUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {

    public static String getFirstLine(String path) {
        String fl = null;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            if (myReader.hasNextLine()) {
                fl = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fl;
    }
}
