package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.SkeletonArmy;

import java.util.ArrayList;
import java.util.Arrays;

public class BotPlayer extends Player
{

    public BotPlayer()
    {
        super(0, 3, new ArrayList<>(Arrays.asList(
                new DarkPrince(),
                new Giant(),
                new SkeletonArmy(),
                new Giant(),
                new Giant(),
                new DarkPrince(),
                new SkeletonArmy()
        )));
    }

    @Override
    public void playTurn()
    {
        super.playTurn();

        //** Summon
        try
        {

            this.useCardInHand((int)Math.random() * this.getHand().size());

        }
        catch (IndexOutOfBoundsException e)
        {
            // Incorrect usage! Format: \"/summon [index of card left-right]\""
        }
        catch (IllegalArgumentException e)
        {
            // Not enough mana
        }

        super.endTurn();
    }

}
