package lr3.droids;

public class Paladin extends Droid {

    public Paladin(String name) {
        super(name);
        setDamage(generateRandomValue(15, 30));
        setHealth(generateRandomValue(250, 300));
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