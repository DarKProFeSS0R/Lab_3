package lr3.droids;

public class Paladin extends Droid {

    public Paladin(String name) {
        super(name);
        int minDamage = 15;
        int maxDamage = 30;
        setDamage(generateRandomValue(minDamage, maxDamage));
        int minHealth = 250;
        int maxHealth = 300;
        setHealth(generateRandomValue(minHealth, maxHealth));
    }

    public void takeDamage(int damage) {
        if (Math.random() < 1.0 / 3) {
            String message = (getName() + " fully blocks the damage with shield!");
            writeToBattleFile(message);
            System.out.println(message);
        } else {
            super.takeDamage(damage);
        }
    }
}