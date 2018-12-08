package Cards;

import java.awt.*;

public abstract class Card
{
    private int mana;
    private String name, description;

    public Card(int mana, String name, String description)
    {
        this.mana = mana;
        this.name = name;
        this.description = description;
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

        // Text
        g.setColor(Color.WHITE);

        int textOffset = (75 - g.getFontMetrics().stringWidth(name)) / 2; // Name
        g.drawString(name, x + textOffset, y + 10);

        textOffset = (75 - g.getFontMetrics().stringWidth(description)) / 2; // Description
        g.drawString(description, x + textOffset, y + 30);

    }

    public abstract void deployCard(int playerID);

    public abstract Card clone();
}
