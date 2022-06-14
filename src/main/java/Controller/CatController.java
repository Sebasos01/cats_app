package Controller;

import Model.Cat;
import PersonalUtils.ImageUtils;
import PersonalUtils.StringUtils;
import com.google.gson.Gson;
import com.squareup.okhttp.Response;

import javax.swing.*;
import java.io.IOException;

public class CatController {
    private final static String BASE_URL = "https://api.thecatapi.com/v1/";
    private final static String SEARCH_ENDPOINT = BASE_URL+"images/search";
    private final static String FAVORITE_ENDPOINT = BASE_URL+"favourites";
    private final static APIConnection api = APIConnection.getInstance();
    private final static Gson serializer = new Gson();

    public static Cat getRandomCat(){
        Cat cat = null;
        try {
            Response response = api.sendGetRequest(SEARCH_ENDPOINT);
            String json = response.body().string();
            json = StringUtils.trimFL.apply(json);
            System.out.println("Json: " + json);
            cat = serializer.fromJson(json, Cat.class);
            ImageIcon catImage = ImageUtils.resizeImage(cat.getUrl());
            cat.setImage(catImage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return  cat;
    }

    public static void favoriteCat(){

    }
}
