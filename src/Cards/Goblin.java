package Cards;

import Minions.GiantMinion;
import Minions.GoblinMinion;
import _Game.Game;

public class Goblin extends Card
{

    public Goblin()
    {
        super(1, "Goblin", "Green", "src/Graphics/defaultMinion.png");
    }

    @Override
    public void deployCard(int playerID)
    {
        Game.getBattlefield().getRow(playerID).addMinion(new GoblinMinion(playerID));
    }

    @Override
    public Card clone()
    {
        return new Goblin();
    }

}
