import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int userChoose;
        Battle pokemonBattle = new Battle();
        pokemonBattle.randomPokemon();
        do{
            System.out.println(pokemonBattle);
            userChoose = takeNumberFromUser();
            switch (userChoose){
                case 1:
                    pokemonBattle.optionOne();
                    break;
                case 2:
                    pokemonBattle.optionTwo();
                    break;
                case 3:
                    pokemonBattle.optionThree();
                    break;
            }
            pokemonBattle.addInTheEndOfTurn();
           pokemonBattle.changeTurn();
        }while(!pokemonBattle.checkWinner());







        /*Attack fire = new Attack("fire", 13, 15, 12);
        Attack grass = new Attack("grass", 13, 40, 30);
        Attack water = new Attack("water", 13, 15, 12);
        Attack[] attacks = {fire, grass, water};
        FirePokemon first = new FirePokemon("char", 1, 100, true, 1, attacks);
        FirePokemon other = new FirePokemon("other", 10, 15, true, 1, attacks);
        ElectricPokemon pika = new ElectricPokemon("pikapika",12,23,true,1,attacks);
        Battle bbb = new Battle(first,pika);
        System.out.println(bbb);
        bbb.optionOne();
        System.out.println(bbb);
        bbb.checkWinner();
*/
    }
    public static int takeNumberFromUser(){
        Scanner scan = new Scanner(System.in);
        int numberFromUser;
        System.out.println("choose an option");
        do{
            numberFromUser =scan.nextInt();
            if (numberFromUser < 1 || numberFromUser > 4){
                System.out.println("try again");
            }
        }while(numberFromUser < 1 || numberFromUser > 4);
        return numberFromUser;
    }

}