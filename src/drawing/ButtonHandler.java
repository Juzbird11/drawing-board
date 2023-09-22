package drawing;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {
    private Canvas canvas;

    public ButtonHandler(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(canvas.rectangle)) {
            canvas.canvasPanel.setShapeType(3);
        } else if (event.getSource().equals(canvas.circle)) {
            canvas.canvasPanel.setShapeType(2);
        } else if (event.getSource().equals(canvas.line)) {
            canvas.canvasPanel.setShapeType(1);
        } else if (event.getSource().equals(canvas.pencil)) {
            canvas.canvasPanel.setShapeType(0);
        } else if (event.getSource().equals(canvas.undo)) {
            canvas.canvasPanel.undo();
        } else if (event.getSource().equals(canvas.clean)) {
            canvas.canvasPanel.clear();
        }
    }
}
