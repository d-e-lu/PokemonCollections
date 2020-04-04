package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.PokemonModel;

import javax.swing.*;
import java.awt.event.ActionListener;


public class InsertPanel extends DefaultPanel {

    private JTextField pokemonIdField;
    private JTextField nameField;
    private JTextField weightField;
    private JTextField attackField;
    private JTextField specialDefenseField;
    private JTextField speedField;
    private JTextField hpField;
    private JTextField defenseField;
    private JTextField specialAttackField;
    private JTextField abilityNameField;

    public InsertPanel(JFrame frame, String resultButtonCommandName, String bgPath) {
        super();
        loadBg(bgPath);

        JLabel pokemonId = new JLabel("Enter pokemon id: ");
        JLabel name = new JLabel("Enter name: ");
        JLabel weight = new JLabel("Enter weight: ");
        JLabel attack = new JLabel("Enter attack: ");
        JLabel specialDefense = new JLabel("Enter special defense: ");
        JLabel speed = new JLabel("Enter speed: ");
        JLabel hp = new JLabel("Enter hp: ");
        JLabel defense = new JLabel("Enter defense: ");
        JLabel specialAttack = new JLabel("Enter special attack:");
        JLabel abilityName = new JLabel("Enter ability name:");

        pokemonIdField = new JTextField(TEXT_FIELD_WIDTH);
        nameField = new JTextField(TEXT_FIELD_WIDTH);
        weightField = new JTextField(TEXT_FIELD_WIDTH);
        attackField = new JTextField(TEXT_FIELD_WIDTH);
        specialDefenseField = new JTextField(TEXT_FIELD_WIDTH);
        speedField = new JTextField(TEXT_FIELD_WIDTH);
        hpField = new JTextField(TEXT_FIELD_WIDTH);
        defenseField = new JTextField(TEXT_FIELD_WIDTH);
        specialAttackField = new JTextField(TEXT_FIELD_WIDTH);
        abilityNameField = new JTextField(TEXT_FIELD_WIDTH);

        int y = 0;
        addTextAndField(pokemonId, pokemonIdField, y++);
        addTextAndField(name, nameField, y++);
        addTextAndField(weight, weightField, y++);
        addTextAndField(attack, attackField, y++);
        addTextAndField(specialDefense, specialDefenseField, y++);
        addTextAndField(speed, speedField, y++);
        addTextAndField(hp, hpField, y++);
        addTextAndField(defense, defenseField, y++);
        addTextAndField(specialAttack, specialAttackField, y++);
        addTextAndField(abilityName, abilityNameField, y++);
        addResultButton(resultButtonCommandName, y, (ActionListener) frame);
    }

    PokemonModel createPokemonModel() {
        try {
            int pId = Integer.parseInt(pokemonIdField.getText());
            String n = nameField.getText();
            double w = Double.parseDouble(weightField.getText());
            int at = Integer.parseInt(attackField.getText());
            int sd = Integer.parseInt(specialDefenseField.getText());
            int s = Integer.parseInt(speedField.getText());
            int hp = Integer.parseInt(hpField.getText());
            int d = Integer.parseInt(defenseField.getText());
            int sa = Integer.parseInt(specialAttackField.getText());
            String an = abilityNameField.getText();
            return new PokemonModel(pId, n, w, at, sd, s, hp, d, sa, an);
        } catch (Exception e) {
            return null;
        }
    }
}
