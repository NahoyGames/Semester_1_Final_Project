package Minions;

public abstract class Minion
{

    private String name;

    private int health, damage;

    private boolean hasTaunt;

    public Minion(String name, int health, int damage, boolean hasTaunt)
    {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.hasTaunt = hasTaunt;
    }

    public void takeDamage(int amount)
    {
        health -= amount;
    }

    public void attack(Minion m)
    {
        m.takeDamage(damage);
    }


    public String toString()
    {
        return name + "(" + health + "♡) (" + damage + "⚔︎)";
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
