package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ProjectPanel extends DefaultPanel{
    private JTextField field;
    private JTextField table;

    public ProjectPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images/Pokemon5.jpg");

        JLabel fieldLabel = new JLabel("Enter select attribute: ");
        JLabel tableLabel = new JLabel("Enter table: ");

        field = new JTextField(TEXT_FIELD_WIDTH);
        table = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(fieldLabel, field, y++);
        addTextAndField(tableLabel, table, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
    public String getField() {
        return field.getText();
    }

    public String getTable() {
        return table.getText();
    }
}