package ca.ubc.cs304.ui;

import ca.ubc.cs304.delegates.MainWindowDelegate;
import ca.ubc.cs304.model.Model;
import ca.ubc.cs304.model.PokemonModel;
//import com.sun.org.apache.xpath.internal.operations.Div;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainWindow extends JFrame implements ActionListener {

    private static final int FRAME_HEIGHT = 600;
    private static final int FRAME_WIDTH = 1600;
    private static final int INVALID_INPUT = Integer.MIN_VALUE;
    private BufferedReader bufferedReader = null;
    private static final int EMPTY_INPUT = 0;
    private static final String WARNING_TAG = "[WARNING]";
    private static final String EXCEPTION_TAG = "[EXCEPTION]";

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
        VIEW
    }

    // delegate
    private MainWindowDelegate delegate;
    private Font font;

    private JTabbedPane tabbedPane;
    private ResultPanel resultPanel;
    private JScrollPane scrollPane;

    private InsertPanel insertPanel;
    private DeletePanel deletePanel;
    private InsertPanel updatePanel;
    private SelectPanel selectPanel;
    private ProjectPanel projectPanel;
    private JoinPanel joinPanel;
    private AggregationPanel aggregationPanel;
    private NestedAggregationPanel nestedAggregationPanel;
    private DivisionPanel divisionPanel;
    private ViewPanel viewPanel;

    public static void changeFont ( Component component, Font font )
    {
        component.setFont ( font );
        if ( component instanceof Container )
        {
            for ( Component child : ( ( Container ) component ).getComponents () )
            {
                changeFont ( child, font );
            }
        }
    }
    private void createFont() {
        try {
            //create the font to use. Specify the size!
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\Pokemon_Text.ttf")).deriveFont(10f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }

    }
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

        createFont();

        JPanel contentPane = new JPanel(new GridLayout(1,2));
        this.setContentPane(contentPane);

        resultPanel = new ResultPanel();
        scrollPane = new JScrollPane();
        resultPanel.add(scrollPane);

        tabbedPane = new JTabbedPane();

        insertPanel = new InsertPanel(this, Actions.INSERT.name(), "images//Pokemon1.jpg");
        tabbedPane.addTab(Actions.INSERT.name(), insertPanel);

        deletePanel = new DeletePanel(this, Actions.DELETE.name());
        tabbedPane.addTab(Actions.DELETE.name(), deletePanel);

        updatePanel = new InsertPanel(this, Actions.UPDATE.name(),"images//Pokemon3.jpg");
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

        viewPanel = new ViewPanel(this, Actions.VIEW.name());
        tabbedPane.addTab(Actions.VIEW.name(), viewPanel);

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
                PokemonModel[] result = delegate.insert(p);
                resultPanel.updateResultPanel(this, result);
            } else {
                resultPanel.updateResultPanel(this, "Can't select pokemon (can't parse input fields).");
            }
        } else if (e.getActionCommand().equals(Actions.DELETE.name())) {
            Integer pId = deletePanel.deletePokemon();
            if (pId != null) {
                PokemonModel[] result = delegate.delete(pId);
                resultPanel.updateResultPanel(this, result);
            } else {
                System.out.println("Can't delete pokemon.");
            }
        } else if (e.getActionCommand().equals(Actions.UPDATE.name())) {
            PokemonModel p = updatePanel.createPokemonModel();
            if (p != null) {
                PokemonModel[] result = delegate.update(p);
                resultPanel.updateResultPanel(this, result);
            } else {
                System.out.println("Can't update pokemon.");
            }
        } else if (e.getActionCommand().equals(Actions.SELECT.name())) {
            System.out.println("Select");
            String f1 = selectPanel.getField1();
            String f2 = selectPanel.getField2();
            String t1 = selectPanel.getTable();
            Integer threshold = selectPanel.getThreshold();
            if (threshold != null) {
                String[] results = delegate.select(f1, t1, f2, threshold);
                resultPanel.updateResultPanel(this, results);
            } else {
                resultPanel.updateResultPanel(this, "Can't select pokemon (can't parse input fields).");
            }
        } else if (e.getActionCommand().equals(Actions.PROJECT.name())) {
            System.out.println("Project");
            String f = projectPanel.getField();
            String t = projectPanel.getTable();
            String[] results = delegate.project(f, t);
            resultPanel.updateResultPanel(this, results);
        } else if (e.getActionCommand().equals(Actions.JOIN.name())) {
            System.out.println("Join");
            String f = joinPanel.getField();
            String[] results = delegate.join(f);
            resultPanel.updateResultPanel(this, results);
        } else if (e.getActionCommand().equals(Actions.AGGREGATION.name())) {
            System.out.println("Aggregate");
            Integer t = aggregationPanel.getThreshold();
            if (t != null) {
                int results = delegate.countPokemonOnWeight(t);
                resultPanel.updateResultPanel(this, Integer.toString(results));
            } else {
                System.out.println("Can't aggregate.");
            }
        } else if (e.getActionCommand().equals(Actions.NESTED_AGGREGATION.name())) {
            System.out.println("Nested Aggregate");
            double avg = delegate.avgPokemonPerRegion();
            resultPanel.updateResultPanel(this, "Average number of pokemon per region is: " + Double.toString(avg));
        } else if (e.getActionCommand().equals(Actions.DIVISION.name())) {
            System.out.println("Division");
            String[] results = delegate.division();
            String description = "All pokemon that are in all regions:";
            String[] display = new String[results.length+1];
            display[0] = description;
            System.arraycopy(results,0, display,1,results.length);
            resultPanel.updateResultPanel(this, display);
        } else if(e.getActionCommand().equals(Actions.VIEW.name())) {
            System.out.println("View");
            String table = viewPanel.getTable();
            Model[] results = delegate.view(table);
            resultPanel.updateResultPanel(this, results);
        }
        changeFont(resultPanel,font);
    }
}