package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.SkeletonArmy;
import Minions.Minion;

public class Game
{
    private static Battlefield battlefield;

    public static void main(String[] args)
    {
        System.out.println("Welcome to Hearthstone!\n");

        Game game = new Game();

        new SkeletonArmy().deployMinion(0);
        new DarkPrince().deployMinion(0);
        new Giant().deployMinion(1);

        System.out.println(battlefield.toString());

        battlefield.getRow(1).getMinion(0).attack(battlefield.getRow(0).getPotentialTargets().get(0));

        System.out.println(battlefield.toString());

        while (true)
        {
            game.playRound();

            System.out.println(battlefield.toString());
        }
    }

    public Game()
    {
        battlefield = new Battlefield();
    }

    public static Battlefield getBattlefield()
    {
        return battlefield;
    }

    public void playRound()
    {

        System.out.println("Your move\n");

        // Command Keyword
        boolean validInput = false;
        while (!validInput)
        {
            String[] input = (TextIO.getlnString()).split(" ");

            switch (input[0].toLowerCase())
            {
                case "/summon":
                    switch (input[1].toLowerCase())
                    {
                        case "skeletonarmy":
                            new SkeletonArmy().deployMinion(1);
                            validInput = true;
                            break;
                        case "giant":
                            new Giant().deployMinion(1);
                            validInput = true;
                            break;
                        case "darkprince":
                            new DarkPrince().deployMinion(1);
                            validInput = true;
                            break;
                        default:
                            System.out.println("That ID was not recognized. Valid ID's -\n\"SkeletonArmy\"\n\"Giant\"");
                    }
                    break;
                case "/attack":
                    try
                    {
                        Minion target = battlefield.getRow(0).getMinion(Integer.parseInt(input[2]));

                        if (battlefield.getRow(0).getPotentialTargets().contains(target))
                        {
                            battlefield.getRow(1).getMinion(Integer.parseInt(input[1])).attack(target);
                            validInput = true;
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
                case "/quit":
                    System.out.println("Thanks for playing 👋");
                    System.exit(0);
                case "/help":
                    System.out.println("Possible commands: \n\"/summon\",\n\"/quit\"");
                    break;
                default:
                    System.out.println("Unknown command. Use \"/help\" for a list of known commands.");
                    break;
            }

            if (!validInput)
            {
                System.out.println("Try again.\n");
            }
        }
    }
}
