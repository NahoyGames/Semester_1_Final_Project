package Cards;

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

    public abstract void deployMinion(int playerID);

    public abstract Card clone();
}
