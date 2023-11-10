package lr3.battle;

import lr3.droids.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SoloBattle implements Serializable {
    public static void startBattle(Droid droid1, Droid droid2) {
        clearLogFile();

        String startMessage = "Battle between " + droid1.getName() + " and " + droid2.getName() + " started!";
        writeToConsoleAndFile(startMessage);
        System.out.println(startMessage);

        while (droid1.getHealth() > 0 && droid2.getHealth() > 0) {
            performRound(droid1, droid2);

            Droid temp = droid1;
            droid1 = droid2;
            droid2 = temp;
        }

        determineWinner(droid1, droid2);
    }

    private static void performRound(Droid attacker, Droid victim) {
        attacker.attack(victim);
    }

    private static void determineWinner(Droid droid1, Droid droid2) {
        if (droid1.getHealth() > 0 && droid2.getHealth() == 0) {
            String message = droid1.getName() + " wins battle!";
            writeToConsoleAndFile(message);
            System.out.println(message);
        } else if (droid2.getHealth() > 0 && droid1.getHealth() == 0) {
            String message = droid2.getName() + " wins battle!";
            writeToConsoleAndFile(message);
            System.out.println(message);
        } else {
            String message = "Battle ended in tie.";
            writeToConsoleAndFile(message);
            System.out.println(message);
        }
    }

    private static void writeToConsoleAndFile(String message) {
        try (FileWriter writer = new FileWriter("battle_log.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void clearLogFile() {
        try {
            Files.write(Paths.get("battle_log.txt"), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}