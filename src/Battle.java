import java.util.Random;

public class Battle {
    private Pokemon firstPokemon;
    private Pokemon secondPokemon;
    private boolean player1Turn;
    final int MAX_OPTION_BONUS = 4;
    final int MIN_OPTION_BONUS = 1;
    final int OPTION_ONE = 1;
    final int OPTION_TWO = 2;
    final int OPTION_THREE = 3;
    final boolean PLAYER_ONE_TURN = true;


    public Battle(Pokemon firstPokemon, Pokemon secondPokemon) {
        this.firstPokemon = firstPokemon;
        this.secondPokemon = secondPokemon;
        this.player1Turn = true;
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
        int bonus = randomNumber(MIN_OPTION_BONUS, MAX_OPTION_BONUS);
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
          System.out.println("33333333333333");
        }
    }
    public int randomNumber(int min,int max){
        Random random = new Random();
        int randomNum = random.nextInt(min,max);
        return randomNum;
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
