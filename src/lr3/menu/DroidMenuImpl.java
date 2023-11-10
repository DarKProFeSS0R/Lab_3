package lr3.menu;

import lr3.battle.*;
import lr3.droids.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DroidMenuImpl implements DroidMenu {
    private final List<Droid> droidList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void createDroid(String name) {
        String droidType = selectDroidType();

        Droid newDroid = switch (droidType) {
            case "Assassin" -> new Assassin(name);
            case "Paladin" -> new Paladin(name);
            case "Wizard" -> new Wizard(name);
            case "Healer" -> new Healer(name);
            default -> null;
        };

        droidList.add(newDroid);
        System.out.println("Droid " + newDroid.getName() + " created.");
    }

    public void showDroidList() {
        if (droidList.isEmpty()) {
            System.out.println("List of droids is empty.");
        } else {
            System.out.println("Droids:");
            for (Droid droid : droidList) {
                System.out.println("Name: " + droid.getName() + ", Type: " + droid.getClass().getSimpleName() + ", HP:" + droid.getHealth());
            }
        }
    }

    public void startSoloBattle() {
        if (droidList.size() < 2) {
            System.out.println("You need to create at least 2 droids to start battle.");
            return;
        }

        System.out.println("Choose first droid:");
        showDroidList();
        int firstDroidIndex = scanner.nextInt()-1;

        if (firstDroidIndex < 0 || firstDroidIndex >= droidList.size()) {
            System.out.println("Wrong option.");
            return;
        }

        System.out.println("Choose second droid:");
        showDroidList();
        int secondDroidIndex = scanner.nextInt()-1;

        if (secondDroidIndex < 0 || secondDroidIndex >= droidList.size()) {
            System.out.println("Wrong option.");
            return;
        }

        Droid droid1 = droidList.get(firstDroidIndex);
        Droid droid2 = droidList.get(secondDroidIndex);

        SoloBattle.startBattle(droid1, droid2);
    }

    public void startTeamBattle() {
        if (droidList.size() < 4) {
            System.out.println("You need to create at least 4 droids (2 teams) to start a team battle.");
            return;
        }

        System.out.println("Create Team 1:");
        List<Droid> team1 = createTeam();

        System.out.println("Create Team 2:");
        List<Droid> team2 = createTeam();

        if (team1.isEmpty() || team2.isEmpty()) {
            System.out.println("Both teams must have at least 1 droid to start the battle.");
            return;
        }

        TeamBattle.startTeamBattle(team1, team2);
    }

    public void saveBattleToFile() {
        System.out.println("Enter the name of the file to save the battle log:");
        String fileName = scanner.next();

        try {
            Path source = Path.of("battle_log.txt");
            Path destination = Path.of(fileName);

            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Battle log saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving battle log to file.");
        }
    }

    public void loadBattleFromFile() {
        System.out.println("Enter the name of the file to load the battle log from:");
        String fileName = scanner.next();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Battle log loaded from " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading battle log from file.");
        }
    }

    public void exitProgram() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    public void runMenu() {
        while (true) {
            System.out.println("\nList of options:");
            System.out.println("1. Create droid");
            System.out.println("2. Show list of droids");
            System.out.println("3. Start 1 vs 1 battle");
            System.out.println("4. Start Team vs Team battle");
            System.out.println("5. Wrote battle into file");
            System.out.println("6. Replay battle from file");
            System.out.println("7. Close game");
            System.out.println("Enter your option:");

            int choice = getUserInput();

            switch (choice) {
                case 1:
                    enterDroidName();
                    break;
                case 2:
                    showDroidList();
                    break;
                case 3:
                    startSoloBattle();
                    break;
                case 4:
                    startTeamBattle();
                    break;
                case 5:
                    saveBattleToFile();
                    break;
                case 6:
                    loadBattleFromFile();
                    break;
                case 7:
                    exitProgram();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
    }

    public String selectDroidType() {
        System.out.println("Choose droid type:");
        System.out.println("1. Assassin");
        System.out.println("2. Paladin");
        System.out.println("3. Wizard");
        System.out.println("4. Healer");

        int choice = getUserInput();

        return switch (choice) {
            case 1 -> "Assassin";
            case 2 -> "Paladin";
            case 3 -> "Wizard";
            case 4 -> "Healer";
            default -> {
                System.out.println("Wrong option.");
                yield selectDroidType();
            }
        };
    }

    public void enterDroidName(){
        System.out.println("Enter droid name:");
        String name = scanner.next();
        createDroid(name);
    }

    private int getUserInput() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.next();
            }
        }
    }

    private List<Droid> createTeam() {
        List<Droid> team = new ArrayList<>();

        showDroidList();

        System.out.println("Add droids to the team (enter droid numbers, one at a time; 0 to finish):");
        int droidNumber;

        while (true) {
            droidNumber = getUserInput();
            if (droidNumber == 0) {
                break;
            } else if (droidNumber > 0 && droidNumber <= droidList.size()) {
                team.add(droidList.get(droidNumber - 1));
            } else {
                System.out.println("Invalid droid number. Try again.");
            }
        }

        return team;
    }

}