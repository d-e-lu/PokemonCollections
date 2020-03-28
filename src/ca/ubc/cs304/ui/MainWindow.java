package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.MainWindowDelegate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame implements ActionListener {

    private enum Actions {
        INSERT,
        DELETE,
        UPDATE,
        SELECT,
        EXIT
    }

    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;

    // delegate
    private MainWindowDelegate delegate;

    private void placeButton(GridBagConstraints c, GridBagLayout gb, JPanel contentPane, Actions action) {
        JButton button = new JButton(action.name());
        button.setActionCommand(action.name());
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.insets = new Insets(5, 10, 10, 10);
        c.anchor = GridBagConstraints.CENTER;
        gb.setConstraints(button, c);
        contentPane.add(button);
        button.addActionListener(this);
    }

    public void showFrame(MainWindowDelegate delegate) {

        this.delegate = delegate;

        JPanel contentPane = new JPanel();
        this.setContentPane(contentPane);

        // layout components using the GridBag layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        contentPane.setLayout(gb);
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // place the insert button
        placeButton(c, gb, contentPane, Actions.INSERT);
        // place the delete button
        placeButton(c, gb, contentPane, Actions.DELETE);
        // place the update button
        placeButton(c, gb, contentPane, Actions.UPDATE);
        // place the select button
        placeButton(c, gb, contentPane, Actions.SELECT);
        // place the exit button
        placeButton(c, gb, contentPane, Actions.EXIT);

        // anonymous inner class for closing the window
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // size the window to obtain a best fit for the components
        this.pack();

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == Actions.INSERT.name()) {
            System.out.println("Insert");
        } else if (e.getActionCommand() == Actions.DELETE.name()) {
            System.out.println("Delete");
        } else if (e.getActionCommand() == Actions.UPDATE.name()) {
            System.out.println("Update");
        } else if (e.getActionCommand() == Actions.SELECT.name()) {
            System.out.println("Select");
        } else if(e.getActionCommand() == Actions.EXIT.name()) {
            System.out.println("Exiting");
            delegate.mainWindowFinished();
        }
    }
}