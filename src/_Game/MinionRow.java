package _Game;

import Minions.Minion;
import Utilities.Vector2;

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
        minions = new ArrayList<Minion>(); //Arrays.asList(Game.getPlayer(playerID))
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

    public List<Minion> getMinions()
    {
        return minions;
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
            targets.add(Game.getPlayer(playerID));
        }

        return targets;
    }

    public void endTurnsForAllMinions()
    {
        for (Minion m : minions)
        {
            m.endTurn();
        }
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

    public void drawMinions(Graphics g, int width, int height)
    {
        int startX = (width - (minions.size() * 80))/2;
        int startY = (int)(height * 0.1 + (playerID * height * 0.3));

        for (int i = 0; i < minions.size(); i++)
        {
            minions.get(i).draw(g, new Vector2(startX + i * 80, startY));
        }
    }
}
