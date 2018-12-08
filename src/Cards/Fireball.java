package Cards;

import Minions.DarkPrinceMinion;
import Minions.Minion;
import _Game.Game;

public class Fireball extends Card
{

    public Fireball()
    {
        super(5, "Fireball", "2 Damage to ALL");
    }

    @Override
    public void deployCard(int playerID)
    {
        for (Object m : Game.getBattlefield().getRow(0).getMinions().toArray())
        {
            ((Minion)m).takeDamage(2);
        }
        for (Object m : Game.getBattlefield().getRow(1).getMinions().toArray())
        {
            ((Minion)m).takeDamage(2);
        }
    }

    @Override
    public Card clone()
    {
        return new Fireball();
    }

}
