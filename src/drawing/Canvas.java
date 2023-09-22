package drawing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Canvas {
    JFrame frame = new JFrame("Drawing Board");
    JPanel toolBar, shapeBar;
    DrawPanel canvasPanel;
    JButton btn, pencil, line, rectangle, circle, undo, clean;
    JLabel lblCurrentColor, lblColor;
    ButtonHandler handler;

    Canvas() {
       canvasPanel = new DrawPanel();
       handler = new ButtonHandler();
    }

    public void CanvasView() {

        //-----------CANVAS-------------
        canvasPanel.setBounds(320, 30, 850, 600);
        canvasPanel.setBackground(Color.white);
        canvasPanel.setLayout(null);
        frame.add(canvasPanel);
        //-----------------------------

        //-----------ShapeBar-----------------
        shapeBar = new JPanel();
        shapeBar.setBounds(1210, 30, 250, 600);
        shapeBar.setBackground(Color.white);
        shapeBar.setLayout(null);
        frame.add(shapeBar);

        pencil = new JButton("Pencil");
        pencil.setBounds(30, 30, 190, 30);
        shapeBar.add(pencil);

        line = new JButton("Line");
        line.setBounds(30, 90, 190, 30);
        shapeBar.add(line);

        rectangle = new JButton("Rectangle");
        rectangle.setBounds(30, 150, 190, 30);
        shapeBar.add(rectangle);

        circle = new JButton("Circle");
        circle.setBounds(30, 210, 190, 30);
        shapeBar.add(circle);

        undo = new JButton("UNDO");
        undo.setBounds(30, 350, 80, 30);
        shapeBar.add(undo);

        clean = new JButton("CLEAN");
        clean.setBounds(140, 350, 80, 30);
        shapeBar.add(clean);


        line.addActionListener(handler);
        rectangle.addActionListener(handler);
        circle.addActionListener(handler);
        pencil.addActionListener(handler);
        undo.addActionListener(handler);
        clean.addActionListener(handler);

        JCheckBox fillShapeColor = new JCheckBox("Filled");
        fillShapeColor.setBounds(30, 260, 100, 50);
        fillShapeColor.setOpaque(false);
        shapeBar.add(fillShapeColor);

        fillShapeColor.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                canvasPanel.setFilledShape(fillShapeColor.isSelected());
            }
        });


        //-----------TOOLBAR-------------

        toolBar = new JPanel();
        toolBar.setBounds(30, 30, 250, 600);
        toolBar.setBackground(Color.white);
        toolBar.setLayout(null);
        frame.add(toolBar);
        addButton(32, 30, "#000000");
        addButton(105, 30, "#FFFFFF");
        addButton(177, 30, "#808080");
        addButton(32, 90, "#FF0000");
        addButton(105, 90, "#00FF00");
        addButton(177, 90, "#0000FF");
        addButton(32, 150, "#FFFF00");
        addButton(105, 150, "#FFA500");
        addButton(177, 150, "#A020F0");
        addButton(32, 210, "#FFC0CB");
        addButton(105, 210, "#964B00");
        addButton(177, 210, "#C32148");


        lblColor = new JLabel("Current Colors : ");
        lblColor.setBounds(30, 260, 100, 50);
        toolBar.add(lblColor);
        lblCurrentColor = new JLabel();
        lblCurrentColor.setBounds(140, 260, 100, 50);
        lblCurrentColor.setText("#000000");
        toolBar.add(lblCurrentColor);

        //------------------------------

        //-----------BACKGROUNDCOLOUR-------------

        JButton bc = new JButton("SET BACKGROUND");
        bc.setBounds(30, 350, 190, 30);
        toolBar.add(bc);
        bc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvasPanel.setBackground(Color.decode(lblCurrentColor.getText()));
            }
        });


        //-----------SAVE---------------
        JButton save = new JButton("SAVE");
        save.setBounds(30, 410, 190, 30);
        toolBar.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tm = java.time.LocalTime.now() + "";
                tm = tm.substring(0, 2) + tm.substring(3, 5) + tm.substring(6, 8) + tm.substring(9, 12) + ".png";
                try {
                    BufferedImage image = getImg(canvasPanel);
                    ImageIO.write(image, "png", new File(tm));
                } catch (Exception ex) {

                }
            }
        });

        //-------------------------------

        //------FRAME------------------
        frame.setSize(1490, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.decode("#001122"));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //---------------------------------
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource().equals(rectangle)) {
                canvasPanel.setShapeType(3);
            } else if (event.getSource().equals(circle)) {
                canvasPanel.setShapeType(2);
            } else if (event.getSource().equals(line)) {
                canvasPanel.setShapeType(1);
            } else if (event.getSource().equals(pencil)) {
                canvasPanel.setShapeType(0);
            } else if (event.getSource().equals(undo)) {
                canvasPanel.undo();
            } else if (event.getSource().equals(clean)) {
                canvasPanel.clear();
            }
        }
    }

    public void addButton(int x, int y, String clr) {
        this.btn = new JButton();
        btn.setBounds(x, y, 40, 40);
        btn.setBackground(Color.decode(clr));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblCurrentColor.setText(clr);
                canvasPanel.setCurrentColor(Color.decode(clr));
            }
        });
        toolBar.add(btn);
    }

    public BufferedImage getImg(Component comp) throws AWTException {
        Point p = comp.getLocationOnScreen();
        Dimension dim = comp.getSize();
        BufferedImage capture = new Robot().createScreenCapture(new Rectangle(p, dim));
        return capture;
    }

}