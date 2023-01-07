import java.util.Scanner;
import java.util.Random;

public abstract class Pokemon {
    private String name;
    private int currentHP;
    private int maxHP;
    private int currentAttackPoint;
    private int maxAttackPoint;
    private boolean abilityToDevelop;
    private int level;
    private Attack[] attacks;

    //finals
    final int MAX_ADD_LIFE_BONUS = 31;
    final int MIN_ADD_LIFE_BONUS = 5;
    final int MAX_ADD_ATTACK_POINT_BONUS = 41;
    final double SEVENTY_FIVE_PERCENT = 0.75;
    final int MIN_ATTACK_POINT = 0;
    final int TO_CORRECT_NUMBER_OF_ATTACK = 1;
    public Pokemon(String name, int hp, int attackPoint, boolean abilityToDevelop, int level, Attack[] attacks) {
        this.name = name;
        this.maxHP = hp;
        this.currentHP = hp;
        this.maxAttackPoint = attackPoint;
        this.currentAttackPoint = calculationOfAttackPointAtTheBeginning(attackPoint);
        this.abilityToDevelop = abilityToDevelop;
        this.level = level;
        this.attacks = attacks;
    }
    public int calculationOfAttackPointAtTheBeginning(int attackPoint){
        int firstAttackPoint = (int)(attackPoint * 0.75);
        return firstAttackPoint;
    }
    public void attack(Pokemon other) {
        printAttack();
        int numberOfAttack = getAttackFromUser() - TO_CORRECT_NUMBER_OF_ATTACK;
        if (this.attacks[numberOfAttack].checkIfCanAttack(this.currentAttackPoint)) {
            int damage = this.attacks[numberOfAttack].calculateDamage();
            lossLife(other, damage);
            lossAttackPoint(this.attacks[numberOfAttack].getCost());
        } else {
            System.out.println("you dont have enough attack point");
        }
    }

    public int getAttackFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the number of the attack");
        int userChoose;
        do {
            userChoose = scanner.nextInt();
        } while (!(userChoose >= 1 && userChoose <= this.attacks.length + 1));// +1 becouse number attacks start from 1 and not from 0
        return userChoose;
    }

    public void lossLife(Pokemon pokemon, int damage) {
        pokemon.currentHP -= damage;
    }
    public void lossAttackPoint(int attackPointToLoss){
        setCurrentAttackPoint(this.currentAttackPoint - attackPointToLoss);
    }
    protected int getCurrentAttackPoint(){
        return this.currentAttackPoint;
    }
    protected void setCurrentAttackPoint(int currentAttackPoint){
        if(currentAttackPoint > this.maxAttackPoint){
            this.currentAttackPoint = this.maxAttackPoint;
        }else{
            this.currentAttackPoint =currentAttackPoint;
        }
    }
    protected int getCurrentHP() {
        return this.currentHP;
    }
    protected void setCurrentHP(int currentHP) {
        if (currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        }else if(currentHP < MIN_ATTACK_POINT){
            this.currentHP = MIN_ATTACK_POINT;
        } else {
            this.currentHP = currentHP;
        }
    }

    protected void SetAttacks(Attack[] attacks) {
        this.attacks = attacks;
    }
    protected Attack[] getAttacks() {
        return this.attacks;
    }
    public String getName(){
        return this.name;
    }
    public void hpBonus() {
        Random random = new Random();
        int lifeBonus = random.nextInt(MIN_ADD_LIFE_BONUS, MAX_ADD_LIFE_BONUS);
        setCurrentHP(getCurrentHP() + lifeBonus);
        System.out.println("you get " + lifeBonus + " points life bonus");
    }

    public void attackPointBonus() {
        Random random = new Random();
        int attackPointBonus = random.nextInt(MAX_ADD_ATTACK_POINT_BONUS);
        setCurrentAttackPoint(getCurrentAttackPoint() + attackPointBonus);
        System.out.println("you get " + attackPointBonus + " points attack bonus");
    }

    public void printAttack() {
        String attacks = this.attacks[0].getListOfAttack(this.attacks);
        System.out.println(attacks);
    }

    public String toString() {
        return "name: " + this.name + "\thp: " + this.currentHP + "\tattackPoint: " + this.currentAttackPoint + "\tlevel: "
                + this.level;
    }
    //+ "\n" + attacks[0].getListOfAttack(this.attacks);  //van add to toString
}
