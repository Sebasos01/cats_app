package PersonalUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageUtils {
    public static ImageIcon resizeImage(String iUrl){
        ImageIcon imageIcon;
         try {
             URL url = new URL(iUrl);
             HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
             httpcon.addRequestProperty("User-Agent", "");
             BufferedImage bufferedImage = ImageIO.read(httpcon.getInputStream());
             imageIcon= new ImageIcon(bufferedImage);
             if (imageIcon.getIconWidth() > 800){
                 // Resize
                 Image image1 = imageIcon.getImage();
                 Image modified = image1.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                 imageIcon = new ImageIcon(modified);
             }
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
        return imageIcon;
    }
}
