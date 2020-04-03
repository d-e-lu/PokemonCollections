package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DivisionPanel extends DefaultPanel{
    public DivisionPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon9.jpg");
        int y = 0;
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
}