package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class DeletePanel extends DefaultPanel {

    private JTextField pokemonIdField;

    public DeletePanel(JFrame frame, String resultButtonCommandName) {
        super();
        loadBg("images//Pokemon2.jpg");
        JLabel pokemonId = new JLabel("Enter pokemon id: ");
        pokemonIdField = new JTextField(TEXT_FIELD_WIDTH);
        int y = 0;
        addTextAndField(pokemonId, pokemonIdField, c, gb, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }

    Integer deletePokemon() {
        try {
            int pId = Integer.parseInt(pokemonIdField.getText());
            return pId;
        } catch (Exception e) {
            return null;
        }
    }
}
