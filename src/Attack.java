import java.util.Random;

public class Attack {
    private String nameOfAttack;
    private int cost;
    private int maxDamage;
    private int minDamage;
    final int FIX_INDEX = 1;

    public Attack(String name, int cost, int maxDamage, int minDamage) {
        this.nameOfAttack = name;
        this.cost = cost;
        this.maxDamage = maxDamage;
        this.minDamage = minDamage;
    }

    public int calculateDamage(int maxDamage,int minDamage) {
        Random rand = new Random();
        int damage = rand.nextInt(maxDamage + 1 - minDamage);
        damage += minDamage;
        return damage;
    }
    public int getCost(){
        return this.cost;
    }
    public boolean checkIfCanAttack(int attackPoint) {
        boolean canAttack = true;
        if (this.cost > attackPoint) {
            canAttack = false;
            System.out.println("you dont have enough attack point");
        }
        return canAttack;
    }

    public int getMaxDamage() {
        return maxDamage;
    }
    public int getMinDamage(){
        return this.minDamage;
    }

    String getListOfAttack(Attack[] attacks) {
        String listOfAttack = "";
        for (int i = 0; i < attacks.length; i++) {
            listOfAttack += i + FIX_INDEX + ") " + attacks[i].toString() + "\n";
        }
        return listOfAttack;
    }

    public String toString() {
        return "name of attack: " + this.nameOfAttack + "\ncost: " + this.cost + "\ndamage: " + this.maxDamage +
                "-" + minDamage;
    }
}
