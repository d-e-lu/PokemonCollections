package ca.ubc.cs304.ui;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JList list;
    private JScrollPane scrollPane;

    public ResultPanel() {
        super(new BorderLayout());
        list = new JList();
        scrollPane = new JScrollPane();
        this.add(scrollPane);
    }

    public void updateResultPanel(JFrame frame, String result) {
        this.removeAll();
        DefaultListModel listModel1 = new DefaultListModel();
        listModel1.addElement(result);
        list = new JList(listModel1);
        scrollPane = new JScrollPane(list);
        this.add(scrollPane);
        frame.revalidate();
        frame.repaint();
    }

    public void updateResultPanel(JFrame frame, String[] result) {
        this.removeAll();
        list = new JList(result);
        scrollPane = new JScrollPane(list);
        this.add(scrollPane);
        frame.revalidate();
        frame.repaint();
    }
}