package lr3.droids;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public abstract class Droid {
    private final String name;
    private int health;
    private int damage;

    public Droid(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int generateRandomValue(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }

    public void takeDamage(int damage) {
        int currentHealth = getHealth();
        currentHealth -= damage;
        if (currentHealth < 0) {
            currentHealth = 0;
        }
        setHealth(currentHealth);
    }

    public void attack(Droid enemy) {
        int damageDealt = getDamage();
        enemy.takeDamage(damageDealt);
        System.out.println(getName() + " deals " + enemy.getName() + " " + damageDealt + " damage. Now " + enemy.getName() + " has " + enemy.getHealth() + " HP.");
    }

    public void writeToBattleFile(String message) {
        try (FileWriter writer = new FileWriter("battle_log.txt", true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}