package drawing;

import ooo.MyShape;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends MyShape
{
    public Line() {
        super();
    }

    public Line(int x1, int y1, int x2, int y2, Color myColor) {
        super(x1,y1,x2,y2,myColor);
    }

    public void draw(Graphics g) {
        g.setColor(super.getColor());
        g.drawLine(super.getX1(), super.getY1(), super.getX2(), super.getY2());
    }
}
