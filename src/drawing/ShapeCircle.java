package drawing;

import ooo.MyBoundedShape;

import java.awt.Color;
import java.awt.Graphics;

public class ShapeCircle extends GeometricShape
{
    public ShapeCircle(int x1, int y1, int x2, int y2,
                  Color myColor, boolean filled)
    {
        super(x1,y1,x2,y2,myColor,filled);

    }

    public void draw(Graphics g)
    {
        g.setColor(this.getColor());

        if (isFilled()) {
            g.fillOval(getUpperLeftX(), getUpperLeftY(),
                    getWidth(), getHeight());
        }

        else {
            g.drawOval(getUpperLeftX(), getUpperLeftY(),
                    getWidth(), getHeight());
        }

    }
}

