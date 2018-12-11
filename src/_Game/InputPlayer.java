package _Game;

import Cards.DarkPrince;
import Cards.Fireball;
import Cards.Giant;
import Cards.SkeletonArmy;
import Utilities.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class InputPlayer extends Player
{

    private int selectedCard = -1; // -1 means no selected card
    private int selectedMinion = -1; // -1 means no selected minion
    private boolean myTurn = false;

    public InputPlayer()
    {
        super(1, 0, new ArrayList<>(Arrays.asList(
                new DarkPrince(),
                new Giant(),
                new SkeletonArmy(),
                new Giant(),
                new Giant(),
                new DarkPrince(),
                new SkeletonArmy(),
                new Fireball()
        )));

    }

    @Override
    public void drawHand(Graphics g, int width, int height)
    {
        super.drawHand(g, width, height);

        // ** Highlights the selected card
        if (selectedCard != -1)
        {
            int startPos = (width - (getHand().size() * 80)) / 2;

            g.setColor(new Color(0xD4B22A));
            g.drawRoundRect(startPos + (selectedCard * 80) - 1, height - 110, 77, 102, 10, 10);
        }

        // ** Highlights the selected minion
        if (selectedMinion != -1)
        {
            int startX = (width - (Game.getBattlefield().getRow(1).getMinions().size() * 80))/2;
            int startY = (int)(height * 0.1 + (height * 0.3));

            g.setColor(new Color(0xD4B22A));
            g.drawRoundRect(startX + (selectedMinion * 80) - 1, startY, 77, 77, 10, 10);

        }
    }

    @Override
    public void playTurn()
    {
        super.playTurn();

        myTurn = true;

    }

    @Override
    public void endTurn()
    {
        super.endTurn();
        myTurn = false;
    }

    public void detectCollision(Vector2 mousePos, int screenWidth, int screenHeight) // Takes the mouse position and detects if a card is clicked or un-clicked
    {
        // Checks collisions for - Cards in hand
        int startPos = (screenWidth - (getHand().size() * 80))/2;

        for (int i = 0; i < super.getHand().size(); i++)
        {
            Vector2 cardPos = new Vector2(startPos + i * 80, screenHeight - 110);

            if ((cardPos.x <= mousePos.x) && (cardPos.x + 75 >= mousePos.x) && (cardPos.y <= mousePos.y) && (cardPos.y + 100 >= mousePos.y))
            {
                selectedCard = i == selectedCard ? -1 : i;
                selectedMinion = -1;
                return;
            }
        }

        // Checks collisions for - Deployed Minions
        int minionCollision = detectCollisionOnMinion(mousePos, screenWidth, screenHeight, 1);

        if (minionCollision != -1)
        {
            selectedMinion = minionCollision == selectedMinion ? -1 : minionCollision;
            selectedCard = -1;
            return;
        }


        // Check for use selected card
        if (selectedCard != -1)
        {
            if (mousePos.y < screenHeight - 110 && mousePos.y > (int)(screenHeight * 0.1 + 75))
            {
                try
                {
                    this.useCardInHand(selectedCard);
                    selectedCard = -1;
                    selectedMinion = -1;
                    return;
                }
                catch (IllegalArgumentException e)
                {
                    Game.outputMessage(String.valueOf(e));
                    selectedCard = -1;
                    selectedMinion = -1;
                    return;
                }
            }
        }

        // Check for attack w/ selected minion
        if (selectedMinion != -1)
        {
            // Enemy minions
            minionCollision = detectCollisionOnMinion(mousePos, screenWidth, screenHeight, 0);

            if (minionCollision != -1)
            {
                try
                {
                    Game.getBattlefield().getRow(1).getMinion(selectedMinion).attack(Game.getBattlefield().getRow(0).getMinion(minionCollision));
                }
                catch (IllegalArgumentException e)
                {
                    Game.outputMessage(String.valueOf(e));
                }
                selectedMinion = -1;
                selectedCard = -1;
                return;
            }

            // Enemy Hero
            int startY = (int)(screenHeight * 0.1 + (0 * screenHeight * 0.3));
            if ((10 <= mousePos.x) && (85 >= mousePos.x) && (startY <= mousePos.y) && (startY + 75 >= mousePos.y))
            {
                try
                {
                    Game.getBattlefield().getRow(1).getMinion(selectedMinion).attack(Game.getPlayer(0));
                }
                catch (IllegalArgumentException e)
                {
                    Game.outputMessage(String.valueOf(e));
                }
                selectedMinion = -1;
                selectedCard = -1;
                return;
            }

        }

    }

    public int detectCollisionOnMinion(Vector2 mousePos, int screenWidth, int screenHeight, int playerID) // @param playerID = sample whose minions
    {
        int numberOfMinions = Game.getBattlefield().getRow(playerID).getMinions().size();
        int startX = (screenWidth - (numberOfMinions * 80))/2;
        int startY = (int)(screenHeight * 0.1 + (playerID * screenHeight * 0.3));

        for (int i = 0; i < numberOfMinions; i++)
        {
            Vector2 cardPos = new Vector2(startX + i * 80, startY);

            if ((cardPos.x <= mousePos.x) && (cardPos.x + 75 >= mousePos.x) && (cardPos.y <= mousePos.y) && (cardPos.y + 75 >= mousePos.y))
            {
                return i;
            }
        }

        return -1;
    }
}

// TEXT BASED TURNS
/*
        {
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
                                    new SkeletonArmy().deployCard(id);
                                    break;
                                case "giant":
                                    new Giant().deployCard(id);
                                    break;
                                case "darkprince":
                                    new DarkPrince().deployCard(id);
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
                    this.getCardFromDeck();
                    System.out.println(this.getHand().toString());
                    break;
                case "/endturn":
                    Game.getBattlefield().getRow(0).endTurnsForAllMinions();
                    Game.getBattlefield().getRow(1).endTurnsForAllMinions();
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
        }*/
