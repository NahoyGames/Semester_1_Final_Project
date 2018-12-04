package _Game;

import Cards.SkeletonArmy;

public class Game
{
    private static Battlefield battlefield;

    public static void main(String[] args)
    {
        battlefield = new Battlefield();

        new SkeletonArmy().deployMinion();

        System.out.println(battlefield.toString());
    }

    public static Battlefield getBattlefield()
    {
        return battlefield;
    }

}
