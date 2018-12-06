package _Game;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame
{
    // ** Game Components **
    private static Battlefield battlefield;
    private static GUI graphics;

    // ** Players **
    private static Player[] players;

    public static void main(String[] args)
    {
        System.out.println("Welcome to Hearthstone!\n");

        // ** Creates the players
        players = new Player[]
                {
                        new BotPlayer(),  // opposing player ... id = 0
                        new InputPlayer() // 'you' player    ... id = 1
                };


        // ** Creates the game
        Game game = new Game();

        // ** Plays the game
        while (true)
        {
            players[1].playRound();

            System.out.println(battlefield.toString());

            players[0].playRound();

            System.out.println(battlefield.toString());

        }
    }

    public Game()
    {
        battlefield = new Battlefield();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(true);

        graphics = new GUI();
        setContentPane(graphics);
        setVisible(true);
    }

    public static Battlefield getBattlefield()
    {
        return battlefield;
    }

    public static GUI getGUI()
    {
        return graphics;
    }

    public static Player getPlayer(int id)
    {
        for (Player p : players)
        {
            if (p.getPlayerID() == id)
            {
                return p;
            }
        }

        return null;
    }
}
