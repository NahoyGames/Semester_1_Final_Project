package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.SkeletonArmy;
import Minions.Minion;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Game
{
    private static Battlefield battlefield;

    public static void main(String[] args)
    {
        System.out.println("Welcome to Hearthstone!\n");

        // ** Creates the game
        Game game = new Game();

        // ** Creates the 'you' player
        Player human = new InputPlayer();

        // ** Creates the opposing player
        Player enemy = new BotPlayer();

        // ** Plays the game
        while (true)
        {
            human.playRound();

            System.out.println(battlefield.toString());

            enemy.playRound();

            System.out.println(battlefield.toString());

        }
    }

    public Game()
    {
        battlefield = new Battlefield();
    }

    public static Battlefield getBattlefield()
    {
        return battlefield;
    }
}
