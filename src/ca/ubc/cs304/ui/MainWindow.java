package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.MainWindowDelegate;
import ca.ubc.cs304.model.PokemonModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame implements ActionListener {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 800;

    private enum Actions {
        INSERT,
        DELETE,
        UPDATE,
        SELECT,
        PROJECT,
        JOIN,
        AGGREGATION,
        NESTED_AGGREGATION,
        DIVISION,
    }

    // delegate
    private MainWindowDelegate delegate;

    private JTabbedPane tabbedPane;
    private JPanel resultPanel;
    private InsertPanel insertPanel;

    private JPanel makeDefaultPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new BorderLayout());
        JButton button = new JButton("Result");
        button.setActionCommand("Result");
        panel.add(button, BorderLayout.PAGE_END);
        button.addActionListener(this);
        return panel;
    }

    private JComponent makeDeletePanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeUpdatePanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeSelectPanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeProjectPanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeJoinPanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeAggregatePanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeNestedAggregatePanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }

    private JComponent makeDivisionPanel() {
        JPanel panel = makeDefaultPanel();
        return panel;
    }


    public void showFrame(MainWindowDelegate delegate) {
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                delegate.mainWindowFinished();
                System.exit(0);
            }
        });

        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        this.delegate = delegate;

        JPanel contentPane = new JPanel(new GridLayout(1,2));
        this.setContentPane(contentPane);

        resultPanel = new JPanel(new BorderLayout());

        tabbedPane = new JTabbedPane();

        insertPanel = new InsertPanel(this, Actions.INSERT.name());
        tabbedPane.addTab(Actions.INSERT.name(), insertPanel);

        JComponent panel2 = makeDeletePanel();
        tabbedPane.addTab(Actions.DELETE.name(), panel2);

        JComponent panel3 = makeUpdatePanel();
        tabbedPane.addTab(Actions.UPDATE.name(), panel3);

        JComponent panel4 = makeSelectPanel();
        tabbedPane.addTab(Actions.SELECT.name(), panel4);

        JComponent panel5 = makeProjectPanel();
        tabbedPane.addTab(Actions.PROJECT.name(), panel5);

        JComponent panel6 = makeJoinPanel();
        tabbedPane.addTab(Actions.JOIN.name(), panel6);

        JComponent panel7 = makeAggregatePanel();
        tabbedPane.addTab(Actions.AGGREGATION.name(), panel7);

        JComponent panel8 = makeNestedAggregatePanel();
        tabbedPane.addTab(Actions.NESTED_AGGREGATION.name(), panel8);

        JComponent panel9 = makeDivisionPanel();
        tabbedPane.addTab(Actions.DIVISION.name(), panel9);

        contentPane.add(tabbedPane);
        contentPane.add(resultPanel);

        // center the frame
        Dimension d = this.getToolkit().getScreenSize();
        Rectangle r = this.getBounds();
        this.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

        // make the window visible
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(Actions.INSERT.name())) {
            PokemonModel p = insertPanel.createPokemonModel();
            if (p != null) {
                delegate.insert(p);
            } else {
                // TODO: Handle if null or if database couldn't insert pokemon
                System.out.println("Can't insert pokemon.");
            }

        }
    }
}