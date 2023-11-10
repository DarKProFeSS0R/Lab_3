package lr3.main;

import lr3.menu.*;

public class DroidBattleMain {
    public static void main(String[] args) {
        DroidMenu menu = new DroidMenuImpl();
        menu.runMenu();
    }
}