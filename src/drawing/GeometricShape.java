package drawing;

import java.awt.Color;
import java.awt.Graphics;

abstract class GeometricShape extends Shape
{
    private boolean filled;

    public GeometricShape()
    {
        super();
    }
    public GeometricShape(int x1, int y1, int x2, int y2,
                          Color myColor, boolean filled)
    {
        super(x1,y1,x2,y2,myColor);
        this.filled = filled;
    }


    public boolean isFilled()
    {
        return filled;
    }


    public void setFilled(boolean filled)
    {
        this.filled = filled;
    }

    public int getUpperLeftX()
    {
        return Math.min(super.getX1(), super.getX2());
    }


    public int getUpperLeftY()
    {
        return Math.min(super.getY1(), super.getY2());
    }

    public int getWidth()
    {
        return Math.abs(super.getX2() - super.getX1());
    }

    public int getHeight()
    {
        return Math.abs(super.getY2() - super.getY1());
    }

    abstract public void draw(Graphics g);
}
