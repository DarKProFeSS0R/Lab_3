package lr3.battle;

import lr3.droids.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;

public class TeamBattle implements Serializable {
    public static void startTeamBattle(List<Droid> team1, List<Droid> team2) {
        int round = 1;

        clearLogFile();

        while (!team1.isEmpty() && !team2.isEmpty()) {
            String roundMessage = "Round " + round;
            writeToConsoleAndFile(roundMessage);
            System.out.println(roundMessage);

            for (Droid droid1 : team1) {
                Droid target = getRandomDroid(team2);
                if (target != null) {
                    if (droid1 instanceof Healer healer) {
                        if (healer.getMana() >= 10) {
                            Droid teammateToHeal = findWeakestTeammate(team1);
                            if (teammateToHeal != null) {
                                healer.heal(teammateToHeal);
                            }
                        } else {
                            droid1.attack(target);
                        }
                    } else {
                        droid1.attack(target);
                    }
                    if (target.getHealth() == 0) {
                        team1.remove(target);
                    }
                }
            }

            for (Droid droid2 : team2) {
                Droid target = getRandomDroid(team1);
                if (target != null) {
                    if (droid2 instanceof Healer healer) {
                        if (healer.getMana() >= 10) {
                            Droid teammateToHeal = findWeakestTeammate(team2);
                            if (teammateToHeal != null) {
                                healer.heal(teammateToHeal);
                            }
                        } else {
                            droid2.attack(target);
                        }
                    } else {
                        droid2.attack(target);
                    }
                    if (target.getHealth() == 0) {
                        team1.remove(target);
                    }
                }
            }

            round++;
        }

        determineWinner(team1, team2);
    }

    private static void determineWinner(List<Droid> team1, List<Droid> team2) {
        if (team1.isEmpty()) {
            String message = "Team 2 wins!";
            writeToConsoleAndFile(message);
            System.out.println(message);
        } else if (team2.isEmpty()){
            String message = "Team 1 wins!";
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

    private static Droid getRandomDroid(List<Droid> team) {
        Random random = new Random();
        if (team.isEmpty()) {
            return null;
        }
        int randomIndex = random.nextInt(team.size());
        return team.get(randomIndex);
    }

    private static Droid findWeakestTeammate(List<Droid> team) {
        Droid weakestTeammate = null;
        int minHealth = Integer.MAX_VALUE;

        for (Droid droid : team) {
            if (droid.getHealth() > 0 && droid.getHealth() < minHealth) {
                weakestTeammate = droid;
                minHealth = droid.getHealth();
            }
        }

        return weakestTeammate;
    }

    private static void clearLogFile() {
        try {
            Files.write(Paths.get("battle_log.txt"), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}