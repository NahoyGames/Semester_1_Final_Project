package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.Goblin;
import Cards.SkeletonArmy;
import Minions.Minion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BotPlayer extends Player
{

    public BotPlayer()
    {
        super(0, 2, new ArrayList<>(Arrays.asList(
                new DarkPrince(),
                new Giant(),
                new Goblin(),
                new SkeletonArmy(),
                new Giant(),
                new Giant(),
                new DarkPrince(),
                new SkeletonArmy(),
                new Goblin()
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

        attemptAttack(getPlayerID() == 1 ? 0 : 1);

        super.endTurn();
    }

    private void attemptAttack(int enemyId)
    {
        //** Attack
        List<Minion> myDeployedMinions = Game.getBattlefield().getRow(getPlayerID()).getMinions();
        List<Minion> potentialTargets = Game.getBattlefield().getRow(enemyId).getPotentialTargets();
        potentialTargets.add(Game.getPlayer(enemyId));

        for (Minion m : myDeployedMinions) // Loops through this bot's minions
        {
            try
            {
                m.attack(potentialTargets.get((int)Math.random() * potentialTargets.size()));
                potentialTargets = Game.getBattlefield().getRow(enemyId).getPotentialTargets();
                potentialTargets.add(Game.getPlayer(enemyId));
                attemptAttack(enemyId);
                break;
            }
            catch (IllegalArgumentException err)
            {
                continue;
            }
        }
    }

}
