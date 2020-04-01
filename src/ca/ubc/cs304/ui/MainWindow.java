package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.MainWindowDelegate;
import ca.ubc.cs304.model.PokemonModel;
import com.sun.org.apache.xpath.internal.operations.Div;

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
    private DeletePanel deletePanel;
    private InsertPanel updatePanel;
    private SelectPanel selectPanel;
    private ProjectPanel projectPanel;
    private JoinPanel joinPanel;
    private AggregationPanel aggregationPanel;
    private NestedAggregationPanel nestedAggregationPanel;
    private DivisionPanel divisionPanel;

    public void showFrame(MainWindowDelegate delegate) {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
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

        deletePanel = new DeletePanel(this, Actions.DELETE.name());
        tabbedPane.addTab(Actions.DELETE.name(), deletePanel);

        updatePanel = new InsertPanel(this, Actions.UPDATE.name());
        tabbedPane.addTab(Actions.UPDATE.name(), updatePanel);

        selectPanel = new SelectPanel(this, Actions.SELECT.name());
        tabbedPane.addTab(Actions.SELECT.name(), selectPanel);

        projectPanel = new ProjectPanel(this, Actions.PROJECT.name());
        tabbedPane.addTab(Actions.PROJECT.name(), projectPanel);

        joinPanel = new JoinPanel(this, Actions.JOIN.name());
        tabbedPane.addTab(Actions.JOIN.name(), joinPanel);

        aggregationPanel = new AggregationPanel(this, Actions.AGGREGATION.name());
        tabbedPane.addTab(Actions.AGGREGATION.name(), aggregationPanel);

        nestedAggregationPanel = new NestedAggregationPanel(this, Actions.NESTED_AGGREGATION.name());
        tabbedPane.addTab(Actions.NESTED_AGGREGATION.name(), nestedAggregationPanel);

        divisionPanel = new DivisionPanel(this, Actions.DIVISION.name());
        tabbedPane.addTab(Actions.DIVISION.name(), divisionPanel);

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
        if (e.getActionCommand().equals(Actions.INSERT.name())) {
            PokemonModel p = insertPanel.createPokemonModel();
            if (p != null) {
                delegate.insert(p);
            } else {
                // TODO: Handle if null or if database couldn't insert pokemon
                System.out.println("Can't insert pokemon.");
            }
        } else if (e.getActionCommand().equals(Actions.DELETE.name())) {
            Integer pId = deletePanel.deletePokemon();
            if (pId != null) {
                delegate.delete(pId);
            } else {
                System.out.println("Can't delete pokemon.");
            }
        } else if (e.getActionCommand().equals(Actions.UPDATE.name())) {
            PokemonModel p = insertPanel.createPokemonModel();
            if (p != null) {
                delegate.update(p);
            } else {
                System.out.println("Can't update pokemon.");
            }
        } else if (e.getActionCommand().equals(Actions.SELECT.name())) {
            System.out.println("Select");
        } else if (e.getActionCommand().equals(Actions.PROJECT.name())) {
            System.out.println("Project");
        } else if (e.getActionCommand().equals(Actions.JOIN.name())) {
            System.out.println("Join");
        } else if (e.getActionCommand().equals(Actions.AGGREGATION.name())) {
            System.out.println("Aggregate");
        } else if (e.getActionCommand().equals(Actions.NESTED_AGGREGATION.name())) {
            System.out.println("Nested Aggregation");
        } else if (e.getActionCommand().equals(Actions.DIVISION.name())) {
            System.out.println("Division");
        }
    }
}