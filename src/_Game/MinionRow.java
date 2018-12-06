package _Game;

import Minions.Minion;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Container for deployed Minions
public class MinionRow
{
    private List<Minion> minions;
    private int playerID; // Represents the owner of this row

    public MinionRow(int playerID)
    {
        minions = new ArrayList<Minion>(Arrays.asList(Game.getPlayer(playerID)));
        this.playerID = playerID;
    }

    public void addMinion(Minion m)
    {
        minions.add(m);
    }

    public void killMinion(Minion m)
    {
        minions.remove(m);
    }

    public void killMinion(int index)
    {
        minions.remove(index);
    }

    public int indexOfMinion(Minion m)  // Useful for troops that boost minions on their side
    {
        return minions.indexOf(m);
    }

    public Minion getMinion(int index)
    {
        return minions.get(index);
    }

    public List<Minion> getPotentialTargets() // Returns a list of potential targets, factoring in minions with taunt
    {
        List<Minion> targets = new ArrayList<Minion>();
        boolean hadTaunt = false;

        for (Minion m : minions)
        {
            if (m.getHasTaunt())
            {
                targets.add(m);
            }
        }

        if (targets.size() == 0)
        {
            for (Minion m : minions)
            {
                targets.add(m);
            }
            targets.add(Game.getPlayer(playerID == 1 ? 0 : 1));
        }

        return targets;
    }

    public String toString()
    {
        String s = " | ";

        for (Minion m : minions)
        {
            if (m.getHasTaunt())
            {
                s += "[🛡" + m.toString() + "🛡] | ";
            }
            else
            {
                s += m.toString() + " | ";
            }
        }

        return s;
    }
}
