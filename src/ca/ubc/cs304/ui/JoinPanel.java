package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class JoinPanel extends DefaultPanel{
    private JTextField regionField;

    public JoinPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon6.jpg");
        JLabel fieldLabel = new JLabel("Enter region: ");

        regionField = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(fieldLabel, regionField, c, gb, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
    public String getField() {
        return regionField.getText();
    }
}