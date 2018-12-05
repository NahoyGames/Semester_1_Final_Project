package Cards;

import Minions.GiantMinion;
import _Game.Game;

public class Giant extends Card
{

    public Giant()
    {
        super(5, "Giant", "Spawns a big boi");
    }

    @Override
    public void deployMinion(int playerID)
    {
        Game.getBattlefield().getRow(playerID).addMinion(new GiantMinion(playerID));
    }

}
