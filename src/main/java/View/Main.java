package View;

import Service.CatService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int menuOption = -1;
        String[] buttons = {"1. See a random kitty", "2. See favorites","3. Exit"};
        do {
            String option = (String) JOptionPane.showInputDialog(null, "Java kittens",
                    "View.Main menu",
                    JOptionPane.INFORMATION_MESSAGE,
                    null, buttons, buttons[0]
            );
            for (int i = 0; i < buttons.length; i++) if (option.equals(buttons[i])) menuOption = i;
            switch (menuOption){
                case 0:
                    CatService.getRandomCat();
                    break;
                case 1:
                    CatService.seeRandomFavoriteCat();
                    break;
                case 2:
                    System.out.println("Exiting app...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        while (menuOption != 2);
    }
}
