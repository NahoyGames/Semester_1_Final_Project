package Cards;

import Minions.Skeleton;
import _Game.Game;

public class SkeletonArmy extends Card
{

    public SkeletonArmy()
    {
        super(3, "Skeleton Army", "4 skeletons");
    }

    @Override
    public void deployMinion(int playerID)
    {
        for (int i = 0; i < 4; i++)
        {
            Game.getBattlefield().getRow(playerID).addMinion(new Skeleton(playerID));
        }
    }

    @Override
    public Card clone()
    {
        return new SkeletonArmy();
    }

}
