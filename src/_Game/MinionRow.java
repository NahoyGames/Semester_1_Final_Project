package _Game;

import Minions.Minion;

import java.util.ArrayList;
import java.util.List;

// Container for deployed Minions
public class MinionRow
{
    private List<Minion> minions;

    public MinionRow()
    {
        minions = new ArrayList<Minion>();
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
                if (hadTaunt = false)
                {
                    targets.clear();
                }

                hadTaunt = true;
                targets.add(m);
            }
            else
            {
                if (hadTaunt == false)
                {
                    targets.add(m);
                }
            }

        }

        return targets;
    }

    public String toString()
    {
        String s = " | ";

        for (Minion m : minions)
        {
            s += m.toString() + " | ";
        }

        return s;
    }
}
