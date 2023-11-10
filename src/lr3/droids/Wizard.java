package lr3.droids;

public class Wizard extends Droid {
    private int mana;

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Wizard(String name) {
        super(name);
        int minDamage = 10;
        int maxDamage = 20;
        setDamage(generateRandomValue(minDamage, maxDamage));
        int minHealth = 150;
        int maxHealth = 200;
        setHealth(generateRandomValue(minHealth, maxHealth));
        int minMana = 80;
        int maxMana = 120;
        setMana(generateRandomValue(minMana, maxMana));
    }

    public void attack(Droid enemy) {
        if (Math.random() < 1.0 / 11 && getMana() > 75) {
            int bonusDamage = getDamage() * 15;
            enemy.takeDamage(bonusDamage);
            setMana(getMana() - 75);
            String message = (getName() + " casts rare spell for " + bonusDamage + " damage! Now " + enemy.getName() + " has " + enemy.getHealth() + " HP.");
            writeToBattleFile(message);
            System.out.println(message);
        } else if (getMana() >= 15) {
            int bonusDamage = getDamage() * 3;
            enemy.takeDamage(bonusDamage);
            setMana(getMana() - 15);
            String message = (getName() + " casts common spell for " + bonusDamage + " damage! Now " + enemy.getName() + " has " + enemy.getHealth() + " HP.");
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