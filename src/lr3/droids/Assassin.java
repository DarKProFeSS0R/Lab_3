package lr3.droids;

public class Assassin extends Droid {

    public Assassin(String name) {
        super(name);
        setHealth(generateRandomValue(100, 150));
        setDamage(generateRandomValue(30, 50));
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