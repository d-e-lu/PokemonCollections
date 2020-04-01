package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NestedAggregationPanel extends DefaultPanel{
    public NestedAggregationPanel(JFrame frame, String resultButtonCommandName) {
        super();
        int y = 0;
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
}