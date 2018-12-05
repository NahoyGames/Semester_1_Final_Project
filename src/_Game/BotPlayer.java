package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.SkeletonArmy;
import Minions.Minion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BotPlayer extends Player
{

    public BotPlayer()
    {
        super(0, 3, new ArrayList<>(Arrays.asList(
                new DarkPrince(),
                new Giant(),
                new SkeletonArmy(),
                new Giant(),
                new Giant(),
                new DarkPrince(),
                new SkeletonArmy()
        )));
    }

    @Override
    public void playRound()
    {
        System.out.println(this.getName() + " 's turn!\n");
        this.resetMana();
        this.drawFromDeck();

        //** Summon
        try
        {

            this.useCardInHand((int)Math.random() * this.getHand().size());

        }
        catch (IndexOutOfBoundsException e)
        {
            // Incorrect usage! Format: \"/summon [index of card left-right]\""
            return;
        }
        catch (IllegalArgumentException e)
        {
            // Not enough mana
            return;
        }
        /*case "/attack":
            try
            {
                        Minion target = Game.getBattlefield().getRow(0).getMinion(Integer.parseInt(input[2]));

                        if (Game.getBattlefield().getRow(0).getPotentialTargets().contains(target))
                        {
                            Game.getBattlefield().getRow(1).getMinion(Integer.parseInt(input[1])).attack(target);
                            break;
                        }
                        else
                        {
                            System.out.println("You can't attack this troop! Perhaps some with taunt(🛡) are present?");
                            break;
                        }
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Incorrect usage! Format: \"/attack [your minion's position left-right] [enemy minion's position left-right]");
                        break;
                    }
                case "/draw":
                    this.drawFromDeck();
                    System.out.println(this.getHand().toString());
                    break;
                case "/endturn":
                    validInput = true;
                    break;
                case "/quit":
                    System.out.println("Thanks for playing 👋");
                    System.exit(0);
                case "/help":
                    System.out.println("Possible commands: \n\"/summon\",\n\"/quit\",\n\"/draw\"");
                    break;
                default:
                    System.out.println("Unknown command. Use \"/help\" for a list of known commands.");
                    break;
            }

            if (!validInput)
            {
                System.out.println("...\n");
            }
        }*/
    }

}
