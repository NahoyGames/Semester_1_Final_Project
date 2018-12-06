package _Game;

import Cards.DarkPrince;
import Cards.Giant;
import Cards.SkeletonArmy;
import Minions.Minion;
import Utilities.Vector2;

import java.util.ArrayList;
import java.util.Arrays;

public class InputPlayer extends Player
{

    private int selectedCard; // -1 means no selected card

    public InputPlayer()
    {
        super(1, 0, new ArrayList<>(Arrays.asList(
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

        System.out.println("Your cards -");
        System.out.println(this.getHand().toString() + " / / / / You have " + this.getMana() + "💧");

        // Command Keyword
        while (true)
        {
            String[] input = (TextIO.getlnString()).split(" ");

            switch (input[0].toLowerCase())
            {
                case "/sudo":
                    switch (input[1].toLowerCase())
                    {
                        case "summon":
                            int id = this.getPlayerID();
                            if (input.length >= 4)
                            {
                                id = Integer.parseInt(input[3]);
                            }
                            switch (input[2].toLowerCase())
                            {
                                case "skeletonarmy":
                                    new SkeletonArmy().deployMinion(id);
                                    break;
                                case "giant":
                                    new Giant().deployMinion(id);
                                    break;
                                case "darkprince":
                                    new DarkPrince().deployMinion(id);
                                    break;
                                default:
                                    System.out.println("That ID was not recognized. Valid ID's -\n\"SkeletonArmy\"\n\"Giant\"");
                                    break;
                            }
                            break;
                        case "givemana":
                            this.useMana(-1 * Integer.parseInt(input[2]));
                            System.out.println("Your cards -");
                            System.out.println(this.getHand().toString() + " / / / / You have " + this.getMana() + "💧");
                            break;
                        default:
                            System.out.println("Sudo command not recognized!");
                            break;
                    }
                    break;
                case "/summon":
                    try
                    {
                        this.useCardInHand(Integer.parseInt(input[1]));
                        break;
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Incorrect usage! Format: \"/summon [index of card left-right]\"");
                        break;
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Incorrect usage! Format: \"/summon [index of card left-right]\"");
                        break;
                    }
                    catch (IllegalArgumentException e)
                    {
                        System.out.println(e);
                        break;
                    }
                case "/attack":
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
                    return;
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

            System.out.println("...\n");
        }
    }

    public void detectCollision(Vector2 mousePos, int screenWidth, int screenHeight) // Takes the mouse position and detects if a card is clicked or un-clicked
    {
        int startPos = (screenWidth - (getHand().size() * 80))/2;

        for (int i = 0; i < super.getHand().size(); i++)
        {
            Vector2 cardPos = new Vector2(startPos + i * 80, screenHeight - 110);

            if ((cardPos.x <= mousePos.x) && (cardPos.x + 75 >= mousePos.x) && (cardPos.y <= mousePos.y) && (cardPos.y + 100 >= mousePos.y))
            {
                selectedCard = i == selectedCard ? -1 : i;
                System.out.println("Selected card @ index: " + selectedCard);
                break;
            }
        }
    }

}
