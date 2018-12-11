package Minions;

import Utilities.Vector2;
import _Game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Minion
{

    private String name;

    private int health, damage;

    private boolean hasTaunt;

    private int turnsToAttack;

    private int playerID;

    private BufferedImage image;
    private BufferedImage tauntIcon;

    public Minion(String name, int health, int damage, boolean hasTaunt, String imagePath, int playerID)
    {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.hasTaunt = hasTaunt;
        this.playerID = playerID;

        this.turnsToAttack = 1;

        // ** Get Graphics **
        try
        {
            image = ImageIO.read(new File(imagePath));
            tauntIcon = ImageIO.read(new File("src/Graphics/shieldIcon.png"));
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
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
            turnsToAttack = 1;
        }
        m.takeDamage(damage);
    }

    public  void attack(Minion m)
    {
        if (Game.getBattlefield().getRow(playerID == 0 ? 1: 0).getPotentialTargets().contains(m))
        {
            if (turnsToAttack <= 0)
            {
                attack(m, true);
            }
            else
            {
                throw new IllegalArgumentException("This troop cannot attack now, wait at least " + turnsToAttack + " turn(s)");
            }
        }
        else
        {
            throw new IllegalArgumentException("Can't attack this troop! Perhaps some with taunt(🛡) are present?");
        }
    }

    public void endTurn() // Used for end-turn mechanics
    {
        turnsToAttack -= turnsToAttack == 0 ? 0 : 1;
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
        g.setColor(turnsToAttack > 0 ? new Color(0x9D524A) : new Color(0x5E8858));
        g.fillRoundRect((int)pos.x, (int)pos.y + 3, 75, 75, 10, 10);
        g.setColor(new Color(0x80939E));
        g.fillRoundRect((int)pos.x, (int)pos.y, 75, 75, 10, 10);

        // Image
        g.drawImage(image, (int)pos.x + 12, (int)pos.y + 12, 50, 50, Game.getGUI());

        // Taunt
        if (hasTaunt)
        {
            g.drawImage(tauntIcon, (int)pos.x + 65, (int)pos.y - 10, 20, 20, Game.getGUI());
        }

        // Text
        g.setColor(Color.WHITE);

        int textOffset = (75 - g.getFontMetrics().stringWidth(name)) / 2; // Name
        g.drawString(name, (int)pos.x + textOffset, (int)pos.y + 10);

        textOffset = (75 - g.getFontMetrics().stringWidth(String.valueOf(health) + "♡")) / 2; // Health
        g.drawString(String.valueOf(health) + "♡", (int)pos.x + textOffset - 20, (int)pos.y + 70);

        textOffset = (75 - g.getFontMetrics().stringWidth(String.valueOf(damage) + " dmg️")) / 2; // Health
        g.drawString(String.valueOf(damage) + " dmg", (int)pos.x + textOffset + 20, (int)pos.y + 70);
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
