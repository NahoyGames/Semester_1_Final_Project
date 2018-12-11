package _Game;


import Cards.Card;
import Minions.Minion;
import Utilities.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Player extends Minion
{
    // ** Player Names **
    private static String playerNames[] = new String[]
            {
                    "Jaina Proudmoore🧙",
                    "Garrosh Hellscream🗡️",
                    "Valeera Sanguinar⚔️",
                    "Uther Lightbringer🔨"
            };


    // ** Stuff that actually matters **
    private int playerID; // VERY IMPORTANT! Differentiates between the two players and can affect the ENTIRE game!!!

    private String name;

    private List<Card> deck; // Player's deck - cycles but doesn't fluctuate in length
    private List<Card> hand; // Copies cards from "deck" as it cycles
    private int mana, maxMana;


    public Player(int playerID, int name, ArrayList<Card> deck)
    {
        super(playerNames[name], 30, 1, false, "src/Graphics/defaultMinion.png", playerID);

        this.playerID = playerID;
        this.name = playerNames[name];
        this.deck = deck;
        this.hand = new ArrayList<Card>();

        // ** Init start-of-game stuff
        for (int i = 0; i < 3; i++)
        {
            getCardFromDeck();
        }
    }


    public String getName()
    {
        return name;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    // ** Deck Utility **
    public void playTurn()
    {
        System.out.println(this.getName() + " 's turn!\n");

        resetMana();
        getCardFromDeck();
    }

    public void endTurn()
    {
        Game.nextTurn();
    }

    public List<Card> getHand()
    {
        return hand;
    }

    public void drawHand(Graphics g, int width, int height)
    {
        int startPos = (width - (hand.size() * 80))/2;

        for (int i = 0; i < hand.size(); i++)
        {
            hand.get(i).draw(g, startPos + i * 80, height - 110);
        }
    }

    public void drawHero(Graphics g, int width, int height)
    {
        int startY = (int)(height * 0.1 + (playerID * height * 0.3));

        super.draw(g, new Vector2(10, startY));
    }

    public void getCardFromDeck()
    {
        Card lastCard = deck.get(deck.size() - 1);

        hand.add(lastCard.clone());

        // Cycles Deck
        for (int i = deck.size() - 2; i >= 0; i--)
        {
            deck.set(i + 1, deck.get(i));
        }
        //deck.set(deck.size() - 1, deck.get(0));
        deck.set(0, lastCard);
    }

    public void useCardInHand(int index)
    {
        Card c = hand.get(index);

        if (c.getMana() > mana)
        {
            throw new IllegalArgumentException("Not enough mana!");
        }

        // Summon card
        c.deployCard(playerID);

        // Withdraw mana
        useMana(c.getMana());

        // Discard card
        hand.remove(index);
    }

    public void useCardInHand(Card c)
    {
        useCardInHand(hand.indexOf(c));
    }

    // ** Mana Stuff **
    public int getMana()
    {
        return mana;
    }

    public void useMana(int amount)
    {
        mana -= amount;
    }

    public void resetMana()
    {
        mana = maxMana += 1;
        mana = mana > 10 ? 10 : mana;
    }

    // ** Extends minion stuff **
    @Override
    public void attack(Minion m, boolean myAttack)
    {
        m.takeDamage(getDamage());
    }
}
