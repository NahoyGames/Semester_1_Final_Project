package Cards;

import _Game.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Card
{
    private int mana;
    private String name, description;
    private BufferedImage image;

    public Card(int mana, String name, String description, String imagePath)
    {
        this.mana = mana;
        this.name = name;
        this.description = description;

        try
        {
            image = ImageIO.read(new File(imagePath));
        }
        catch (IOException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    public int getMana()
    {
        return mana;
    }

    public String toString()
    {
        return this.name + " - " + description + " | " + mana + "💧";
    }

    public void draw(Graphics g, int x, int y)
    {
        // Card BG
        g.setColor(new Color(0x697488));
        g.fillRoundRect(x, y + 3, 75, 100, 10, 10);
        g.setColor(new Color(0x80939E));
        g.fillRoundRect(x, y, 75, 100, 10, 10);

        // Image
        g.drawImage(image, x + 12, y + 12, 50, 75, Game.getGUI());


        // Text
        g.setColor(Color.WHITE);

        int textOffset = (75 - g.getFontMetrics().stringWidth(name)) / 2; // Name
        g.drawString(name, x + textOffset, y + 10);

        textOffset = (75 - g.getFontMetrics().stringWidth(description)) / 2; // Description
        g.drawString(description, x + textOffset, y + 30);

        textOffset = (75 - g.getFontMetrics().stringWidth(mana + " mana")) / 2; // Health
        g.drawString(mana + " mana", x + textOffset, y + 85);

    }

    public abstract void deployCard(int playerID);

    public abstract Card clone();
}
