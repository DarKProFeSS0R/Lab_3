package lr3.droids;

public class Assassin extends Droid {

    public Assassin(String name) {
        super(name);
        int minHealth = 100;
        int maxHealth = 150;
        setHealth(generateRandomValue(minHealth, maxHealth));
        int minDamage = 30;
        int maxDamage = 50;
        setDamage(generateRandomValue(minDamage, maxDamage));
    }

    public void attack(Droid enemy) {
        if (Math.random() < 0.25) {
            int bonusDamage = getDamage() * 3;
            enemy.takeDamage(bonusDamage);
            String message = (getName() + " deals a critical hit with "+ bonusDamage+" damage! Now "+ enemy.getName()+ " has "+ enemy.getHealth()+" HP.");
            writeToBattleFile(message);
            System.out.println(message);
        } else {
            super.attack(enemy);
        }
    }

}