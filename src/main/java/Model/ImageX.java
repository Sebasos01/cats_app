package Model;

public class ImageX {
    String id;
    String url;

    public String getUrl() {
        return url;
    }

    @Override
    public String toString(){
        return String.format("{id: %s, url: %s}", id, url);
    }
}
