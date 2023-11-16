package lr3.droids;

public class Healer extends Droid {
    private int mana;

    public Healer(String name) {
        super(name);
        setDamage(generateRandomValue(10, 20));
        setHealth(generateRandomValue(75, 125));
        setMana(generateRandomValue(80, 120));
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void heal(Droid teammate) {
        if (getMana() >= 10) {
            int currentHealth = teammate.getHealth();
            int minHealing = 10;
            int maxHealing = 20;
            int healingAmount = generateRandomValue(minHealing, maxHealing);
            teammate.setHealth(currentHealth+healingAmount);
            setMana(getMana() - 10);
            String message = (getName() + " heals " + teammate.getName() + " for " + healingAmount + " HP. Now " + teammate.getName() + " has " + teammate.getHealth() + " HP.");
            writeToBattleFile(message);
            System.out.println(message);
        }
    }

    public void attack(Droid enemy) {
        if (getMana() >= 15) {
            int bonusDamage = getDamage() * 3;
            enemy.takeDamage(bonusDamage);
            setMana(getMana() - 15);
            String message = (getName() + " casts harmful spell for " + bonusDamage + " damage! Now " + enemy.getName() + " has " + enemy.getHealth() + " HP.");
            writeToBattleFile(message);
            System.out.println(message);
        } else {
            super.attack(enemy);
            String message = (getName() + " has no mana and deals normal " + getDamage() + " damage! Now " + enemy.getName() + " has " + enemy.getHealth() + " HP.");
            writeToBattleFile(message);
            System.out.println(message);
        }
    }

}