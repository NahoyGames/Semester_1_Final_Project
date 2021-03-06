package _Game;

import Minions.Minion;

public class Battlefield
{
    private MinionRow[] playerRows;

    public Battlefield()
    {
        playerRows = new MinionRow[] {
                new MinionRow(0),
                new MinionRow(1)
        };
    }

    public MinionRow getRow(int playerID)
    {
        return playerRows[playerID];
    }

    public String toString()
    {
        return playerRows[0].toString() + "\n vs \n" + playerRows[1].toString() + "\n\n~~~~~~~~~~~~~~~~~~~\n";
    }

}
