package Utilities;

public class Vector2 // R.I.P. Java has no structs ;_;
{

    public float x;
    public float y;

    public Vector2()
    {
        this(0, 0);
    }

    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    // ** Methods **
    public Vector2 add(Vector2 other)
    {
        this.x += other.x;
        this.y += other.y;

        return this;
    }

    // ** Presets **
    public static Vector2 zero()
    {
        return new Vector2(0, 0);
    }

    public static Vector2 down()
    {
        return new Vector2(0, 1);
    }

    public static Vector2 right()
    {
        return new Vector2(1, 0);
    }

}
