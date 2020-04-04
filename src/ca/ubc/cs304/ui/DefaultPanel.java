package ca.ubc.cs304.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class DefaultPanel extends JPanel{

    private static final String NO_IMAGE_ERROR = "Can't load image.";
    private static final float FONT_SIZE = 20f;
    private GridBagLayout gb;
    private GridBagConstraints c;
    private JButton result;
    private BufferedImage bg;

    protected static final int TEXT_FIELD_WIDTH = 10;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }

    protected void addTextAndField(JLabel label, JTextField field, int y) {
        c.gridy = y;
        c.gridx = 0;
        gb.setConstraints(label, c);
        label.setFont(label.getFont().deriveFont(FONT_SIZE));
        this.add(label);
        c.gridx = 1;
        gb.setConstraints(field, c);
        field.setOpaque(false);
        field.setFont(field.getFont().deriveFont(FONT_SIZE));
        this.add(field);
    }

    protected void addResultButton(String resultButtonCommandName, int y, ActionListener a) {
        result = new JButton("Result");
        result.setActionCommand(resultButtonCommandName);
        c.gridy = y;
        c.gridx = 1;
        gb.setConstraints(result, c);
        result.setFont(result.getFont().deriveFont(FONT_SIZE));
        this.add(result);
        result.addActionListener(a);
    }

    public DefaultPanel() {
        gb = new GridBagLayout();
        this.setLayout(gb);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    }

    protected void loadBg(String path) {
        try {
            bg = ImageIO.read(new File(path));
        } catch (Exception e) {
            System.out.println(NO_IMAGE_ERROR);
        }
    }

    protected void clearTextBoxes() {
        for (Component component : this.getComponents()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            }
        }
    }

}
