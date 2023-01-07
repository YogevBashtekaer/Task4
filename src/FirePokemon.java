import java.util.Random;
public class FirePokemon extends Pokemon {
final int LOTTERY_UNTIL_THE_NUMBER = 5;
final int TAKES_LIFE = 1;
    final int MAX_LIFE_FOR_SELF_DAMAGE = 11;
    final int MIN_LIFE_FOR_SELF_FAMAGE = 3;
    public FirePokemon(String name,int hp,int attackPoint,boolean abilityToDevelop,int level,Attack[] attacks){
        super(name,hp,attackPoint,abilityToDevelop,level,attacks);
    }
    public void attack(Pokemon other){
        super.attack(other);
        if(checkForSelfDamage()== true){
            int selfDamage = calculateSelfDamage();
            lossSelfLife(selfDamage);
        }
    }
    public void lossSelfLife(int selfDamage){
        setCurrentHP(this.getCurrentHP() - selfDamage);
    }
    public int calculateSelfDamage(){
        Random random = new Random();
        int selfDamage = random.nextInt(MIN_LIFE_FOR_SELF_FAMAGE,MAX_LIFE_FOR_SELF_DAMAGE);
        return selfDamage;
    }

    public boolean checkForSelfDamage(){
        Random rand = new Random();
        boolean takeSelfLife = false;
        int drawnNumber = rand.nextInt(LOTTERY_UNTIL_THE_NUMBER);
         if(TAKES_LIFE == drawnNumber){
             takeSelfLife = true;
         }
         return takeSelfLife;
    }
}