package Model;

import javax.swing.*;

public class Cat {
    String id;
    String url;
    ImageIcon image;

    public String  getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
