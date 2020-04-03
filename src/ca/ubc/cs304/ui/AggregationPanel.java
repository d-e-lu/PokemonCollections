package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AggregationPanel extends DefaultPanel{
    private JTextField weightThreshold;

    public AggregationPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon7.jpg");
        JLabel fieldLabel = new JLabel("Enter weight threshold: ");

        weightThreshold = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(fieldLabel, weightThreshold, c, gb, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
    public Integer getThreshold() {
        try {
            return Integer.parseInt(weightThreshold.getText());
        } catch (Exception e) {
            return null;
        }
    }
}
