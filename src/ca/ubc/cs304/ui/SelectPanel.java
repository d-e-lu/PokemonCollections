package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.PokemonModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectPanel extends DefaultPanel {

    private JTextField field1;
    private JTextField table1Field;
    private JTextField field2;
    private JTextField thresholdField;

    public SelectPanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon4.jpg");
        JLabel field1Label = new JLabel("Enter select attribute: ");
        JLabel table1Label = new JLabel("Enter table: ");
        JLabel field2Label = new JLabel("Enter filter attribute: ");
        JLabel thresholdLabel = new JLabel("Enter threshold (>=): ");

        field1 = new JTextField(TEXT_FIELD_WIDTH);
        table1Field = new JTextField(TEXT_FIELD_WIDTH);
        field2 = new JTextField(TEXT_FIELD_WIDTH);
        thresholdField = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(field1Label, field1, c, gb, y++);
        addTextAndField(table1Label, table1Field, c, gb, y++);
        addTextAndField(field2Label, field2, c, gb, y++);
        addTextAndField(thresholdLabel, thresholdField, c, gb, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }
    public String getField1() {
        return field1.getText();
    }

    public String getField2() {
        return field2.getText();
    }

    public String getTable() {
        return table1Field.getText();
    }

    public Integer getThreshold() {
        try {
            return Integer.parseInt(thresholdField.getText());
        } catch (Exception e) {
            return null;
        }
    }

}
