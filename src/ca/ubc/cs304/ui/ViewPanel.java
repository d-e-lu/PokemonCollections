package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ViewPanel extends DefaultPanel {
    private JTextField table;

    public ViewPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon10.jpg");
        JLabel tableLabel = new JLabel("Enter table: ");

        table = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(tableLabel, table, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
    public String getTable() {
        return table.getText();
    }
}
