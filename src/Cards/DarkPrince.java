package Cards;

import Minions.DarkPrinceMinion;
import Minions.GiantMinion;
import _Game.Game;

public class DarkPrince extends Card
{

    public DarkPrince()
    {
        super(5, "Dark Prince", "Odd fella with a shield. Taunt.");
    }

    @Override
    public void deployMinion(int playerID)
    {
        Game.getBattlefield().getRow(playerID).addMinion(new DarkPrinceMinion(playerID));
    }

}
