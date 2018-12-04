package Cards;

import Minions.Skeleton;
import _Game.Game;

public class SkeletonArmy extends Card
{

    public SkeletonArmy()
    {
        super(3, "Skeleton Army", "Spawns 4 skeletons");
    }

    @Override
    public void deployMinion()
    {
        for (int i = 0; i < 4; i++)
        {
            Game.getBattlefield().getRow(0).addMinion(new Skeleton()); // TODO summon skeletons
        }
    }

}
