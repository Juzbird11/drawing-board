package drawing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Canvas {
    JFrame frame = new JFrame("Drawing Board");
    JPanel toolBar, shapeBar;
    DrawPanel canvasPanel;
    JButton btn, pencil, line, rectangle, circle;
    JTextField col, sz;
    String prev, back;
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
        {
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
        }

        line.addActionListener(handler);
        rectangle.addActionListener(handler);
        circle.addActionListener(handler);
        pencil.addActionListener(handler);

        JCheckBox fillShapeColor = new JCheckBox("Filled");
        fillShapeColor.setBounds(30, 260, 100, 50);
        fillShapeColor.setOpaque(false);
        shapeBar.add(fillShapeColor);


        //------------------------------------

        //-----------TOOLBAR-------------
        {
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
        }
        //-----------------------------

        //---------CUSTOM---------------
        {
            JLabel cllabel = new JLabel("Custom Colors : ");
            cllabel.setBounds(30, 260, 100, 50);
            toolBar.add(cllabel);
            col = new JTextField();
            col.setBounds(30, 310, 190, 30);
            col.setText("#000000");
            toolBar.add(col);
        }
        //------------------------------

        //-----------BACKGROUNDCOLOUR-------------
        {
            back = "#FFFFFF";
            JButton bc = new JButton("SET BACKGROUND");
            bc.setBounds(30, 350, 190, 30);
            toolBar.add(bc);
            bc.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    back = col.getText();
                    canvasPanel.setBackground(Color.decode(back));
                }
            });
        }
        //---------------------------------

        //---------SIZE------------------
        {
            JLabel szlabel = new JLabel("Size : ");
            szlabel.setBounds(30, 380, 100, 50);
            toolBar.add(szlabel);
            JButton sub = new JButton("-");
            sub.setBounds(30, 415, 50, 30);
            toolBar.add(sub);
            sz = new JTextField();
            sz.setBounds(100, 415, 50, 30);
            sz.setText("5");
            toolBar.add(sz);
            JButton add = new JButton("+");
            add.setBounds(170, 415, 50, 30);
            toolBar.add(add);
            sub.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sz.setText(Integer.parseInt(sz.getText()) - 1 + "");
                }
            });
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    sz.setText(Integer.parseInt(sz.getText()) + 1 + "");
                }
            });
        }
        //-------------------------------

        //---------PAINT&ERASE-----------------
        {
            JButton brush = new JButton("BRUSH");
            brush.setBounds(30, 465, 80, 30);
            toolBar.add(brush);
            JButton undo = new JButton("UNDO");
            undo.setBounds(140, 465, 80, 30);
            toolBar.add(undo);
            prev = "#000000";
            brush.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    col.setText(prev);
                }
            });

            undo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvasPanel.undo();
                }
            });
        }
        //--------------------------------

        //-----------CLEAN--------------
        {
            JButton clean = new JButton("CLEAN");
            clean.setBounds(30, 515, 190, 30);
            toolBar.add(clean);
            clean.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    canvasPanel.clear();
                }
            });
        }
        //-------------------------------

        //-----------SAVE---------------
        {
            JButton save = new JButton("SAVE");
            save.setBounds(30, 555, 190, 30);
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
        }
        //-------------------------------

        //------FRAME------------------
        {
            frame.setSize(1490, 700);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);
            frame.getContentPane().setBackground(Color.decode("#001122"));
            frame.setVisible(true);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
        //---------------------------------
    }

    private class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == rectangle) {
                canvasPanel.setShapeType(3);
            } else if (event.getSource() == circle) {
                canvasPanel.setShapeType(2);
            } else if (event.getSource() == line) {
                canvasPanel.setShapeType(1);
            } else if (event.getSource() == pencil) {
                canvasPanel.setShapeType(0);
            }
        }
    }

    //-------------------------------------------------

    //---------FUNCTIONS TO CREATE SIMILAR BUTTONS---------
    public void addButton(int x, int y, String clr) {
        this.btn = new JButton();
        btn.setBounds(x, y, 40, 40);
        btn.setBackground(Color.decode(clr));
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                col.setText(clr);
            }
        });
        toolBar.add(btn);
    }
    //-------------------------------------------------------

    //----------FUNCTION TO CREATE IMAGE TO SAVE-----------------------------
    public BufferedImage getImg(Component comp) throws AWTException {
        Point p = comp.getLocationOnScreen();
        Dimension dim = comp.getSize();
        BufferedImage capture = new Robot().createScreenCapture(new Rectangle(p, dim));
        return capture;
    }
    //--------------------------------------------------------------------------
}