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

    public abstract void deployMinion(int playerID);
}
