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
import java.util.function.Supplier;

public class CatController {
    private final static String BASE_URL = "https://api.thecatapi.com/v1/";
    private final static String SEARCH_ENDPOINT = BASE_URL + "images/search";
    private final static String FAVORITE_ENDPOINT = BASE_URL + "favourites";
    private final static String FAVORITE_ENDPOINT2 = BASE_URL + "favourites?";
    public final static String KEY_PATH = "37_17_120_-54_-40_63_-114_108_66_-13_65_16_-97_-110_-103_111_68_-112_-4_-117_-110_123_0_-56_125_58_116_42_83_118_-63_74_-47_89_17_-11_-12_-69_105_119_-109_-111_32_26_-15_100_-53_1";
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
            Response response = APIConnection.getInstance().sendPostRequest(FAVORITE_ENDPOINT, rBody, getKey.get());
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
            Response response = APIConnection.getInstance().sendGetRequest2(FAVORITE_ENDPOINT2, getKey.get());
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

    public static void removeFavoriteCat(String catId){
        try {
            Response response = APIConnection.getInstance().sendDeleteRequest(FAVORITE_ENDPOINT + "/" + catId, getKey.get());
            System.out.println(response.toString());
            String json = response.body().string();
            System.out.println("Json: " + json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final Supplier<String> getKey = () ->
            FileUtils.getFirstLine(StrongAES.decrypt(StringUtils.stringToBytes.apply(KEY_PATH, "_")));
}
