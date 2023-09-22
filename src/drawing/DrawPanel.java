package drawing;


import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

public class DrawPanel extends JPanel
{
    private LinkedList<Shape> shapes = new LinkedList<>();
    private Shape currentShape;
    private int shapeType;
    private Color  CurrentColor;
    private boolean filledShape;
//    private JLabel statusText;
    private boolean newShapeStart;

    public DrawPanel()
    {
        setBackground(Color.WHITE);
//        this.statusText=statusLabel;
        setShapeType(0);
        setCurrentColor(Color.BLACK);
        setFilledShape(false);
        DrawPanel.MouseHandler handler =new DrawPanel.MouseHandler(this);
        addMouseMotionListener(handler);
        addMouseListener(handler);
    }

    final public void  setShapeType( int shapeType)
    {
        this.shapeType=shapeType;
    }

    final public void setCurrentColor( Color CurrentColor)
    {
        this.CurrentColor=CurrentColor;
    }

    final public void setFilledShape( boolean filledShape)
    {
        this.filledShape=filledShape;
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

    private class MouseHandler extends MouseAdapter
    {

        private DrawPanel panel;
        public MouseHandler(DrawPanel panel) {
            this.panel = panel;
        }
        
        @Override
        public void mousePressed(MouseEvent event)
        {
            if (event.getButton() == MouseEvent.BUTTON1){
                newShapeStart = true;
                int x = event.getX();
                int y = event.getY();
                switch(shapeType)
                {
                    case 0:
                        currentShape = new Pencil(x, y, x, y, CurrentColor);
                        break;
                    case 1:
                        currentShape = new Line(x, y, x, y, CurrentColor);
                        break;
                    case 2:
                        currentShape = new ShapeCircle(x, y, x, y, CurrentColor, filledShape);
                        break;
                    case 3:
                        currentShape = new ShapeRectangle(x, y, x, y, CurrentColor, filledShape);
                        break;

                }

            }
        }

        @Override
        public void mouseReleased(MouseEvent event)
        {
            if (event.getButton() == MouseEvent.BUTTON1)
            {
                if (event.getX() != currentShape.getX1() || event.getY() != currentShape.getY1())
                {
                    currentShape.setX2(event.getX());
                    currentShape.setY2(event.getY());
                    shapes.add(currentShape);
                }
                newShapeStart=false;

                repaint();

            }
        }

        @Override
        public void mouseDragged(MouseEvent event)
        {
            if (newShapeStart)
            {
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());
                repaint();

            }

//            mouseMoved(event);
        }

        @Override
        public void mouseMoved(MouseEvent event)
        {
//            statusText.setText(String.format("(%d, %d)", event.getX(), event.getY()));
        }
    }
}

