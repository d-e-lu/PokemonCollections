package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.PokemonModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SelectPanel extends DefaultPanel {

    private JTextField field1;
    private JTextField table1Field;
    private JTextField field2;
    private JTextField field3;

    public SelectPanel(JFrame frame, String resultButtonCommandName) {
        super();

        JLabel field1Label = new JLabel("Enter select attribute: ");
        JLabel table1Label = new JLabel("Enter table: ");
        JLabel field2Label = new JLabel("Enter filter attribute: ");
        JLabel field3Label = new JLabel("Enter threshold: ");


        field1 = new JTextField(TEXT_FIELD_WIDTH);
        table1Field = new JTextField(TEXT_FIELD_WIDTH);
        field2 = new JTextField(TEXT_FIELD_WIDTH);
        field3 = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(field1Label, field1, c, gb, y++);
        addTextAndField(table1Label, table1Field, c, gb, y++);
        addTextAndField(field2Label, field2, c, gb, y++);
        addTextAndField(field3Label, field3, c, gb, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }

}
