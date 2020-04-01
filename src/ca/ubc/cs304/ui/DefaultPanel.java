package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DefaultPanel extends JPanel {

    protected static final int TEXT_FIELD_WIDTH = 10;
    protected GridBagLayout gb;
    protected GridBagConstraints c;
    protected JButton result;

    protected void addTextAndField(JLabel label, JTextField field, GridBagConstraints c, GridBagLayout gb, int y) {
        c.gridy = y;
        c.gridx = 0;
        gb.setConstraints(label, c);
        this.add(label);
        c.gridx = 1;
        gb.setConstraints(field, c);
        this.add(field);
    }

    protected void addResultButton(String resultButtonCommandName, int y, ActionListener a) {
        result = new JButton("Result");
        result.setActionCommand(resultButtonCommandName);
        c.gridy = y;
        c.gridx = 1;
        gb.setConstraints(result, c);
        this.add(result);
        result.addActionListener(a);
    }

    public DefaultPanel() {
        gb = new GridBagLayout();
        this.setLayout(gb);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
    }
}
