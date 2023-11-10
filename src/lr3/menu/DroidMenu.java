package lr3.menu;

public interface DroidMenu {
    void createDroid(String name);
    void showDroidList();
    void startSoloBattle();
    void startTeamBattle();
    void saveBattleToFile();
    void loadBattleFromFile();
    void runMenu();
    void exitProgram();
}