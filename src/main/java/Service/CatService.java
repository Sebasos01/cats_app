package Service;

import Controller.CatController;
import Model.Cat;
import Model.FavCat;
import PersonalUtils.NumericalUtils;

import javax.swing.*;
import java.util.ArrayList;

public class CatService {
    public static void getRandomCat(){
        Cat cat = CatController.getRandomCat();
        if (cat != null){
            String menu = "Options:\n" +
                    "1. See another image\n" +
                    "2. Favorite\n" +
                    "3. Go back\n";
            String[] buttons = {"See another image", "Favorite", "Go back"};
            String catId = cat.getId();
            String option = (String) JOptionPane.showInputDialog(
                    null,
                    menu,
                    catId,
                    JOptionPane.INFORMATION_MESSAGE,
                    cat.getImage(),
                    buttons,
                    buttons[0]
            );
            int iOption = -1;
            for (int i = 0; i < buttons.length; i++) if (option.equals(buttons[i])) iOption = i;
            switch (iOption){
                case 0:
                    getRandomCat();
                    break;
                case 1:
                    favoriteCat(cat);
                    getRandomCat();
                    break;
                default:
                    break;
            }
        }
        else {
            System.out.println("CatController.getRandomCat() returned null in CatService.getRandomCat()");
        }
    }

    private static void favoriteCat(Cat cat){
        CatController.favoriteCat(cat.getId());
    }

    public static void seeRandomFavoriteCat(){
        ArrayList<FavCat> cats = (ArrayList<FavCat>) CatController.getFavorites();
        boolean flag = false;
        int iOption = -1;
        while ((cats.size() > 0) && (iOption < 2)){
            if (flag) {cats = (ArrayList<FavCat>) CatController.getFavorites(); flag =  false;}
            FavCat favCat = cats.get(NumericalUtils.randInt.apply(0, cats.size() - 1));
            String menu = "Options:\n" +
                    "1. See another favorite cat\n" +
                    "2. Remove favorite\n" +
                    "3. Go back\n";
            String[] buttons = {"See another favorite cat", "Remove favorite", "Go back"};
            String catId = favCat.getId();
            String option = (String) JOptionPane.showInputDialog(
                    null,
                    menu,
                    catId,
                    JOptionPane.INFORMATION_MESSAGE,
                    favCat.getImageIcon(),
                    buttons,
                    buttons[0]
            );
            for (int i = 0; i < buttons.length; i++) if (option.equals(buttons[i])) iOption = i;
            if (iOption == 1) {removeFavoriteCat(favCat); flag = true;}
        }
    }

    private static void removeFavoriteCat(FavCat favCat){ CatController.removeFavoriteCat(favCat.getId()); }
}
