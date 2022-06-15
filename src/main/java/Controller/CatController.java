package Controller;

import Model.Cat;
import Model.FavCat;
import PersonalUtils.FileUtils;
import PersonalUtils.ImageUtils;
import PersonalUtils.StringUtils;
import PersonalUtils.StrongAES;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatController {
    private final static String BASE_URL = "https://api.thecatapi.com/v1/";
    private final static String SEARCH_ENDPOINT = BASE_URL + "images/search";
    private final static String FAVORITE_ENDPOINT = BASE_URL + "favourites";
    private final static String FAVORITE_ENDPOINT2 = BASE_URL + "favourites?";
    public final static byte[] KEY_PATH = {37, 17, 120, -54, -40, 63, -114, 108, 66, -13, 65, 16, -97, -110, -103, 111, 68, -112, -4, -117, -110, 123, 0, -56, 125, 58, 116, 42, 83, 118, -63, 74, -47, 89, 17, -11, -12, -69, 105, 119, -109, -111, 32, 26, -15, 100, -53, 1};
    private final static APIConnection api = APIConnection.getInstance();
    private final static Gson serializer = new Gson();

    public static Cat getRandomCat(){
        Cat cat = null;
        try {
            Response response = api.sendGetRequest(SEARCH_ENDPOINT);
            System.out.println(response.toString());
            String json = response.body().string();
            json = StringUtils.trimFL.apply(json);
            System.out.println("Json: " + json + "\n");
            cat = serializer.fromJson(json, Cat.class);
            ImageIcon catImage = ImageUtils.resizeImage(cat.getUrl());
            cat.setImage(catImage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  cat;
    }

    public static void favoriteCat(String catId){
        String rBody = "{\n    \"image_id\": \"" + catId + "\"\n}";
        try {
            Response response = APIConnection.getInstance().sendPostRequest(FAVORITE_ENDPOINT, rBody, FileUtils.getFirstLine(StrongAES.decrypt(KEY_PATH)));
            System.out.println(response.toString());
            String json = response.body().string();
            System.out.println("Json: " + json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<FavCat> getFavorites(){
        ArrayList<FavCat> cats = new ArrayList<>();
        try {
            Response response = APIConnection.getInstance().sendGetRequest2(FAVORITE_ENDPOINT2, FileUtils.getFirstLine(StrongAES.decrypt(KEY_PATH)));
            System.out.println(response.toString());
            String json = response.body().string();
            System.out.println("Json: " + json + "\n");
            Collections.addAll(cats, serializer.fromJson(json, FavCat[].class));
            cats.forEach(favCat -> favCat.setImageIcon(ImageUtils.resizeImage(favCat.getImage().getUrl())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cats;
    }
}
