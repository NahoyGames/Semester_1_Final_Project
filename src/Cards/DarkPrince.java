package Cards;

import Minions.DarkPrinceMinion;
import Minions.GiantMinion;
import _Game.Game;

public class DarkPrince extends Card
{

    public DarkPrince()
    {
        super(5, "Dark Prince", "Taunt", "src/Graphics/defaultMinion.png");
    }

    @Override
    public void deployCard(int playerID)
    {
        Game.getBattlefield().getRow(playerID).addMinion(new DarkPrinceMinion(playerID));
    }

    @Override
    public Card clone()
    {
        return new DarkPrince();
    }

}
