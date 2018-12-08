package Cards;

import Minions.GiantMinion;
import _Game.Game;

public class Giant extends Card
{

    public Giant()
    {
        super(5, "Giant", "Big n' Bold");
    }

    @Override
    public void deployCard(int playerID)
    {
        Game.getBattlefield().getRow(playerID).addMinion(new GiantMinion(playerID));
    }

    @Override
    public Card clone()
    {
        return new Giant();
    }

}
