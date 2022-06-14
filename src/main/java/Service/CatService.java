package Service;

import Controller.CatController;
import Model.Cat;

import javax.swing.*;

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
                    break;
                default:
                    break;
            }
        }
        else {
            System.out.println("CatController.getRandomCat() returned null in CatService.getRandomCat()");
        }
    }

    private static void favoriteCat(Cat cat){}
}
