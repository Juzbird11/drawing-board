package drawing;

import java.awt.*;
import java.util.LinkedList;

public class Pencil extends Shape {

    private LinkedList<PencilPoint> points = new LinkedList<>();

    public Pencil(int x1, int y1, int x2, int y2, Color myColor) {
        super(x1,y1,x2,y2,myColor);
    }

    public void draw(Graphics g) {
        points.add(new PencilPoint(this.getX2(), this.getY2()));
        g.setColor(this.getColor());

        for(PencilPoint point: points) {
            g.fillRect(point.getX(), point.getY(), 2, 2);
        }
    }
}
