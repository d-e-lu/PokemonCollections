package ca.ubc.cs304.ui;

import ca.ubc.cs304.model.Model;

import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JList list;
    private JTable table;
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

    public void updateResultPanel(JFrame frame, Model[] result) {
        if(result.length == 0) {
            System.out.println("Table length is 0");
            return;
        }
        this.removeAll();
        String[] columns = result[0].getAttributeNames();
        String[][] s =  new String[result.length][columns.length];

        for (int i = 0; i < result.length; ++i) {
            s[i] = result[i].getAttributes();
        }
        table = new JTable(s,columns);
        scrollPane = new JScrollPane(table);
        this.add(scrollPane);
        frame.revalidate();
        frame.repaint();
    }
}
