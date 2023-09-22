package drawing;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MouseHandler extends MouseAdapter
{
    private DrawPanel panel;
    public MouseHandler(DrawPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mousePressed(MouseEvent event)
    {
        if (event.getButton() == MouseEvent.BUTTON1){
            panel.setNewShapeStart(true);
            int x = event.getX();
            int y = event.getY();
            switch(panel.getShapeType())
            {
                case 0:
                    panel.setCurrentShape(new Pencil(x, y, x, y, panel.getCurrentColor()));
                    break;
                case 1:
                    panel.setCurrentShape(new Line(x, y, x, y, panel.getCurrentColor()));
                    break;
                case 2:
                    panel.setCurrentShape(new ShapeCircle(x, y, x, y, panel.getCurrentColor(), panel.isFilledShape()));
                    break;
                case 3:
                    panel.setCurrentShape(new ShapeRectangle(x, y, x, y, panel.getCurrentColor(), panel.isFilledShape()));
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent event)
    {
        if (event.getButton() == MouseEvent.BUTTON1)
        {
            if (event.getX() != panel.getCurrentShape().getX1() || event.getY() != panel.getCurrentShape().getY1())
            {
                panel.getCurrentShape().setX2(event.getX());
                panel.getCurrentShape().setY2(event.getY());
                panel.getShapes().add(panel.getCurrentShape());
            }
            panel.setNewShapeStart(false);
            panel.repaint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent event)
    {
        if (panel.isNewShapeStart())
        {
            panel.getCurrentShape().setX2(event.getX());
            panel.getCurrentShape().setY2(event.getY());
            panel.repaint();
        }
    }

}
