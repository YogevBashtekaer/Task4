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
    private boolean tripleAttackPower;
    //finals
    final boolean cantDevelop = false;
    final int MAX_ADD_LIFE_BONUS = 31;
    final int MIN_ADD_LIFE_BONUS = 5;
    final int MAX_ADD_ATTACK_POINT_BONUS = 41;
    final double SEVENTY_FIVE_PERCENT = 0.75;
    final int MIN_ATTACK_POINT = 0;
    final int TO_CORRECT_NUMBER_OF_ATTACK = 1;
    final int MULTIPLY_ATTACK_POWER = 3;
    final int LOSE_HP_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO = 20;
    final int LOSE_ATTACK_POINT_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO = 25;
    public Pokemon(String name, int hp, int attackPoint, boolean abilityToDevelop, int level, Attack[] attacks) {
        this.name = name;
        this.maxHP = hp;
        this.currentHP = hp;
        this.maxAttackPoint = attackPoint;
        this.currentAttackPoint = calculationOfAttackPointAtTheBeginning(attackPoint);
        this.abilityToDevelop = abilityToDevelop;
        this.level = level;
        this.attacks = attacks;
        this.tripleAttackPower = false;
    }
    public void developFromOneToTwo(Pokemon other){
            this.name = other.name;
            this.currentHP -= LOSE_HP_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO;
            this.currentAttackPoint -= LOSE_ATTACK_POINT_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO;
            this.maxHP = other.maxHP;
            this.maxAttackPoint = other.maxAttackPoint;
            this.abilityToDevelop = other.abilityToDevelop;
            this.level = other.level;
    }
    public void makeTripleAttack(){
        System.out.println("you get triple attack to your next turn");
        changeTripleAttackPowerToTrue();
    }
    public void changeTripleAttackPowerToTrue(){
        this.tripleAttackPower = true;
    }
    /*
    public void changeTripleAttackPower(){
        if(this.tripleAttackPower){
            tripleAttackPower = false;
        }else{
            tripleAttackPower=true;
        }
    }
    */
    public int calculateTripleAttackPower(int damage){
       ///can add change triple attack power
        return damage *= MULTIPLY_ATTACK_POWER;
    }
    public int calculationOfAttackPointAtTheBeginning(int attackPoint){
        int firstAttackPoint = (int)(attackPoint * 0.75);
        return firstAttackPoint;
    }
    public void attack(Pokemon other) {
        printAttack();
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
    public boolean getTripleAttackPower(){
        return this.tripleAttackPower;
    }
    public void hpBonus() {
        Random random = new Random();
        int lifeBonus = random.nextInt(MIN_ADD_LIFE_BONUS, MAX_ADD_LIFE_BONUS);
        setCurrentHP(getCurrentHP() + lifeBonus);
        System.out.println("you get " + lifeBonus + " points life bonus");
    }
    public void addHP(int toAdd){
        setCurrentHP(getCurrentHP()+toAdd);
    }
    public void addAttackPoint(int toAdd){
        setCurrentAttackPoint(getCurrentAttackPoint()+toAdd);
    }
    public void attackPointBonus() {
        Random random = new Random();
        int attackPointBonus = random.nextInt(MAX_ADD_ATTACK_POINT_BONUS);
        setCurrentAttackPoint(getCurrentAttackPoint() + attackPointBonus);
        System.out.println("you get " + attackPointBonus + " points attack bonus");
    }
    public boolean checkIfCanDevelop(){
        if(this.abilityToDevelop == cantDevelop){
            System.out.println("pokemon cant develop");
        }
        return getAbilityToDevelop();
    }
    public boolean getAbilityToDevelop(){
        return  this.abilityToDevelop;
    }
   /* public void developFromOneToTwo(String name){
      /*  setCurrentHP(this.getCurrentHP() - LOSE_HP_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO );
        setCurrentAttackPoint(this.getCurrentAttackPoint() - LOSE_ATTACK_POINT_WHEN_DEVELOP_FROM_LEVEL_ONE_TO_TWO );

    }
    */
    public void printAttack() {
        String attacks = this.attacks[0].getListOfAttack(this.attacks);
        System.out.println(attacks);
    }

    public String toString() {
        return "name: " + this.name + "\thp: " + this.currentHP + "\tattack Point: " + this.currentAttackPoint + "\tlevel: "
                + this.level;
    }
    //+ "\n" + attacks[0].getListOfAttack(this.attacks);  //van add to toString
}
