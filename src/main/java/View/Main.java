package View;

import Service.CatService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int menuOption = -1;
        String[] buttons = {"1. List cats", "2. Exit"};
        do {
            String option = (String) JOptionPane.showInputDialog(null, "Java kitty", "View.Main menu", JOptionPane.INFORMATION_MESSAGE,
                    null, buttons, buttons[0]);
            for (int i = 0; i < buttons.length; i++) if (option.equals(buttons[i])) menuOption = i;
            switch (menuOption){
                case 0:
                    CatService.getRandomCat();
                    break;
                case 1:
                    System.out.println("Exiting app...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
        while (menuOption != 1);
    }
}
