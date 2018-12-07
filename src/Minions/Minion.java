package Minions;

import Utilities.Vector2;
import _Game.Game;

import java.awt.*;

public abstract class Minion
{

    private String name;

    private int health, damage;

    private boolean hasTaunt;

    private int playerID;

    public Minion(String name, int health, int damage, boolean hasTaunt, int playerID)
    {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.hasTaunt = hasTaunt;
        this.playerID = playerID;
    }

    public void takeDamage(int amount)
    {
        health -= amount;

        if (health <= 0)
        {
            die();
        }
    }

    public void attack(Minion m, boolean myAttack) // Since this calls the other minion's "attack" method, I use the myAttack to prevent recursion
    {
        if (myAttack == true)
        {
            m.attack(this, false);
        }
        m.takeDamage(damage);
    }

    public  void attack(Minion m)
    {
        attack(m, true);
    }

    public void die()
    {
        Game.getBattlefield().getRow(playerID).killMinion(this);
    }

    public String toString()
    {
        return name + "(" + health + "♡) (" + damage + "⚔︎)";
    }

    public void draw(Graphics g, Vector2 pos)
    {
        // Draw Minion BG
        g.setColor(new Color(0x697488));
        g.fillRoundRect((int)pos.x, (int)pos.y + 3, 75, 75, 10, 10);
        g.setColor(new Color(0x80939E));
        g.fillRoundRect((int)pos.x, (int)pos.y, 75, 75, 10, 10);

        // Text
        g.setColor(Color.WHITE);

        int textOffset = (75 - g.getFontMetrics().stringWidth(name)) / 2; // Name
        g.drawString(name, (int)pos.x + textOffset, (int)pos.y + 10);
    }

    public int getHealth()
    {
        return health;
    }

    public int getDamage()
    {
        return damage;
    }

    public boolean getHasTaunt()
    {
        return hasTaunt;
    }
}
