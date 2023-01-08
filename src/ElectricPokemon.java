public class ElectricPokemon extends Pokemon {
    private int electricPower;
    final int START_ELECTRIC_POWER = 0;
    final int DIVIDE_BY_HUNDRED = 100;
    final int MAX_ELECTRIC_POWER = 100;
    final int MIN_ELECTRIC_POWER = 0;
    final int CHARGE_AT_FIVE_PERCENT = 5;
    public ElectricPokemon(String name,int hp,int attackPoint,boolean abilityToDevelop,int level,Attack[] attacks){
        super(name,hp,attackPoint,abilityToDevelop,level,attacks);
        this.electricPower = START_ELECTRIC_POWER;
    }


    public void attack(Pokemon other) {
        super.attack(other);
        int numberOfAttack = getAttackFromUser() - TO_CORRECT_NUMBER_OF_ATTACK;
        if (this.getAttacks()[numberOfAttack].checkIfCanAttack(this.getCurrentAttackPoint())){
          int maxDamage = calculateMaxDamage(this.getAttacks()[numberOfAttack].getMaxDamage());
          int minDamage = this.getAttacks()[numberOfAttack].getMaxDamage();
            int damage = this.getAttacks()[numberOfAttack].calculateDamage(maxDamage,minDamage);
            if(this.getTripleAttackPower()){
                damage = calculateTripleAttackPower(damage);
            }
            lossLife(other, damage);
            lossAttackPoint(this.getAttacks()[numberOfAttack].getCost());
        }
    }
    public void chargeElectricPower(){
        setElectricPower(this.electricPower + CHARGE_AT_FIVE_PERCENT);
    }

    public void setElectricPower(int electricPower){
        if(electricPower <= MIN_ELECTRIC_POWER){
            this.electricPower = MIN_ELECTRIC_POWER;
        }else if(electricPower >= MAX_ELECTRIC_POWER){
            this.electricPower = MAX_ELECTRIC_POWER;
        }else{
            this.electricPower = electricPower;
        }
    }
    public int calculateMaxDamage(int maxDamage){
        int addToDamage = maxDamage * this.electricPower/DIVIDE_BY_HUNDRED;
        return maxDamage + addToDamage;
    }
    public String toString(){
        return super.toString() + "\telectric power: " + this.electricPower;
    }
}
