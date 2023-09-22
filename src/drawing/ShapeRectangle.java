package drawing;

import java.awt.Color;
import java.awt.Graphics;

public class ShapeRectangle extends GeometricShape
{
    public ShapeRectangle()
    {
        super();
    }

    public ShapeRectangle(int x1, int y1, int x2, int y2,
                          Color myColor, boolean filled)
    {
        super(x1,y1,x2,y2,myColor,filled);

    }

    public void draw(Graphics g)
    {
        g.setColor(super.getColor());

        if (isFilled())
            g.fillRect(getUpperLeftX(), getUpperLeftY(),
                    getWidth(), getHeight());
        else
            g.drawRect(getUpperLeftX(), getUpperLeftY(),
                    getWidth(), getHeight());
    }
}
