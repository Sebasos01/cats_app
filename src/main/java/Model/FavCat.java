package Model;

import javax.swing.*;

public class FavCat {
    String id;
    String image_id;
    ImageX image;
    ImageIcon imageIcon;

    public String getId() {
        return id;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public ImageX getImage() {
        return image;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    @Override
    public String toString(){
        return String.format("{id: %s, image_id: %s, image: %s}", id, image_id, image.toString());
    }
}
