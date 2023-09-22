package drawing;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.util.LinkedList;

public class DrawPanel extends JPanel
{
    private LinkedList<Shape> shapes = new LinkedList<>();
    private Shape currentShape;
    private int shapeType;
    private Color CurrentColor;
    private boolean filledShape;
    private boolean newShapeStart;

    private MouseHandler handler;

    public DrawPanel()
    {
        setBackground(Color.WHITE);
        setShapeType(0);
        setCurrentColor(Color.BLACK);
        setFilledShape(false);
        handler = new MouseHandler(this);
        addMouseMotionListener(handler);
        addMouseListener(handler);
    }

    public LinkedList<Shape> getShapes() {
        return shapes;
    }

    public boolean isNewShapeStart() {
        return newShapeStart;
    }

    public void setNewShapeStart(boolean newShapeStart) {
        this.newShapeStart = newShapeStart;
    }

    public int getShapeType() {
        return shapeType;
    }

    public void  setShapeType(int shapeType)
    {
        this.shapeType=shapeType;
    }

    public Shape getCurrentShape() {
        return currentShape;
    }

    public void setCurrentShape(Shape currentShape) {
        this.currentShape = currentShape;
    }

    public boolean isFilledShape() {
        return filledShape;
    }

    public void setFilledShape( boolean filledShape)
    {
        this.filledShape=filledShape;
    }

    public Color getCurrentColor() {
        return CurrentColor;
    }

    public void setCurrentColor( Color CurrentColor)
    {
        this.CurrentColor=CurrentColor;
    }

    public void undo()
    {
        if (! shapes.isEmpty()) {
            shapes.removeLast();
        }
        
        repaint();
    }

    public void clear()
    {
        shapes.clear();
        removeAll();
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (Shape shape: shapes)
            shape.draw(g);
        if (newShapeStart) {
            currentShape.draw(g);
        }

    }
}

