import java.util.Random;

public class Battle {
    private Pokemon firstPokemon;
    private Pokemon secondPokemon;
    private boolean player1Turn;
    private int indexOfFirstPokemon;
    private int indexOfSecondPokemon;


    //final
    final int MAX_OPTION_BONUS = 4;
    final int MIN_OPTION_BONUS = 1;
    final int OPTION_ONE = 1;
    final int OPTION_TWO = 2;
    final int OPTION_THREE = 3;
    final boolean PLAYER_ONE_TURN = true;
    final int PLAYER_LOSE = 0;
    final int MAX_ADD_HP = 5;// need to be 4 but random takes 1 less
    final int MIN_ADD_HP = 0;
    final int MAX_ADD_ATTACK_POINT = 5;// need to be 4 but random takes 1 less
    final int MIN_ADD_ATTACK_POINT = 0;
    final int DEFAULT_INDEX = -1;

    //attacks
    Attack kick = new Attack("Kick",0,2,2);
    Attack scratch = new Attack("Scratch",12,30,25);
    Attack[] charmanderAttacks ={kick,scratch};

    Attack quickAttack = new Attack("Quick Attack",5,10,10);
    Attack[] pichuAttacks ={kick,quickAttack};
    Attack assistingHeater = new Attack("Assisting Heater",30,60,10);
    Attack fireWing = new Attack("Fire Wing",30,30,30);
    Attack[] moltresAttacks = {kick,assistingHeater,fireWing};

    //pokemon level 1
    FirePokemon charmander = new FirePokemon("Charmander",80,40,true,1,charmanderAttacks);
    FirePokemon moltres = new FirePokemon("Moltres",120,60,false,1,moltresAttacks);
    ElectricPokemon pichu = new ElectricPokemon("Pichu",40,30,true,1,pichuAttacks);
    //pokemon level 2
    FirePokemon charmeleon = new FirePokemon("Charmeleon",90,60,true,2,charmanderAttacks);
    Pokemon[] pokemonsLevelOne = {charmander,moltres,pichu};
    Pokemon[] pokemonsLevelTwo = {charmeleon};
    public Battle() {
        this.firstPokemon = null;
        this.secondPokemon = null;
        this.indexOfFirstPokemon = DEFAULT_INDEX;
        this.indexOfSecondPokemon = DEFAULT_INDEX;
        this.player1Turn = true;
    }
    /*
    public Battle(Pokemon firstPokemon, Pokemon secondPokemon) {
        this.firstPokemon = firstPokemon;
        this.secondPokemon = secondPokemon;
        this.player1Turn = true;
    }  /////////save
 */
    public void randomPokemon(){
        Random random = new Random();
        int indexOfFirstPokemon = random.nextInt(pokemonsLevelOne.length);
        this.firstPokemon = pokemonsLevelOne[indexOfFirstPokemon];
        int indexOfSecondPokemon;
        do{
            indexOfSecondPokemon = random.nextInt(pokemonsLevelOne.length);
        }while(indexOfFirstPokemon == indexOfSecondPokemon);
        this.secondPokemon =  pokemonsLevelOne[indexOfSecondPokemon];
    }
    public void changeTurn(){
        if(checkIfPlayer1Turn()){
            this.player1Turn = false;
        }else{
            this.player1Turn = true;
        }
    }

    public boolean checkIfPlayer1Turn() {
        boolean player1Turn = false;
        if (this.player1Turn == PLAYER_ONE_TURN) {
            player1Turn = true;
        }
        return player1Turn;
    }
    public void optionOne(){
        if(checkIfPlayer1Turn()){
            this.firstPokemon.attack(this.secondPokemon);
        }else{
            this.secondPokemon.attack(this.firstPokemon);
        }
    }
    public void optionTwo() {
        Random random = new Random();
        int bonus = random.nextInt(MIN_OPTION_BONUS, MAX_OPTION_BONUS);
        if (bonus == OPTION_ONE) {
            if (checkIfPlayer1Turn()) {
                this.firstPokemon.hpBonus();
            } else {
                this.secondPokemon.hpBonus();
            }
        } else if (bonus == OPTION_TWO) {
            if (checkIfPlayer1Turn()) {
                this.firstPokemon.attackPointBonus();
            } else {
                this.secondPokemon.attackPointBonus();
            }
        } else if (bonus == OPTION_THREE){
            if (checkIfPlayer1Turn()) {
                this.firstPokemon.makeTripleAttack();
            } else {
                this.secondPokemon.makeTripleAttack();
            }
        }
    }
    public void optionThree(){
         if(player1Turn){
             if(this.firstPokemon.checkIfCanDevelop()){
                 this.firstPokemon.developFromOneToTwo(charmeleon);
             }
         }
    }
    public boolean checkWinner(){
        boolean lose = false;
        if(this.firstPokemon.getCurrentHP() <= PLAYER_LOSE && this.secondPokemon.getCurrentHP() <= PLAYER_LOSE){
            System.out.println("draw");
            lose = true;
        }else if(this.firstPokemon.getCurrentHP() <= PLAYER_LOSE){
            System.out.println("the winner is: " +this.secondPokemon.getName());
            lose = true;
        }else if(this.secondPokemon.getCurrentHP() <= PLAYER_LOSE){
            System.out.println("the winner is: " +this.firstPokemon.getName());
            lose = true;
        }
        return lose;
    }

    public void addInTheEndOfTurn(){
       Random random = new Random();
        int addLife = random.nextInt(MIN_ADD_HP,MAX_ADD_HP);
        if(player1Turn){
            this.firstPokemon.addHP(addLife);
        }else{
            this.secondPokemon.addHP(addLife);
        }
        int addAttackPoint = random.nextInt(MIN_ADD_HP,MAX_ADD_HP);
        if(player1Turn){
            this.firstPokemon.addAttackPoint(addAttackPoint);
        }else{
            this.secondPokemon.addAttackPoint(addAttackPoint);
        }
    }
    public String getTurn(){
        String playerTurn = "";
        if(checkIfPlayer1Turn()){
            playerTurn += this.firstPokemon.getName();
        }else{
            playerTurn += this.secondPokemon.getName();
        }
        return playerTurn;
    }
    public String toString(){
        return "player turn: "+getTurn()+ "\n"
                +""+this.firstPokemon.toString()+"\n"+this.secondPokemon.toString();

    }
}
