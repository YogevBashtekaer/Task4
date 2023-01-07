public class Main {
    public static void main(String[] args) {
        Attack fire = new Attack("fire", 13, 15, 12);
        Attack grass = new Attack("grass", 13, 40, 30);
        Attack water = new Attack("water", 13, 15, 12);
        Attack[] attacks = {fire, grass, water};
        FirePokemon first = new FirePokemon("pika", 10, 100, true, 1, attacks);
        FirePokemon other = new FirePokemon("other", 10, 15, true, 1, attacks);
        System.out.println(first);
        //first.hpBonus();
        //first.attackPointBonus();
        first.lossLife(first,5);
        System.out.println(first);
        Battle bbb = new Battle(first,other);
        bbb.optionOne();
        System.out.println(bbb);
    }

}