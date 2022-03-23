
package edu.uptc.so.views;

import edu.uptc.so.fms.entities.DFT;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DFTTable extends JPanel{
    
    private static final long serialVersionUID = 1L;
    private JTable tableProces;
    private DefaultTableModel modelo;
    private JLabel lblTitle;
    private JButton jButton;
    
    public DFTTable(ActionListener listener) {
        buildtable(listener);
    }
    
    public void buildtable(ActionListener listener){
        setLayout(new BorderLayout());
        lblTitle = new JLabel("DFT info");
        jButton = new JButton("Arbol");
		jButton.addActionListener(listener);
		add(jButton, BorderLayout.SOUTH);
        add(lblTitle, BorderLayout.NORTH);
        modelo = new DefaultTableModel();
        //"id", "type", "name", "head", "visibility","createdAt","updatedAt", "accessedAt", "size","children"
        String[] headers = {"name", "head", "visibility", "Sons"};
        modelo.setColumnIdentifiers(headers);
        tableProces = new JTable(modelo);
        add(new JScrollPane(tableProces),BorderLayout.CENTER);
    }
    
    public void setInfoTable(DFT root){
        modelo.setRowCount(0);
        if(root != null){
            String[] info = {root.getName(), root.getHead() +"", 
            root.getVisibility() +"", root.getChildrenDfts().length +""};
            modelo.addRow(info);
            fillWithTree(root.getChildrenDfts());
        }
    }
    
    public void fillWithTree(DFT[] root){
        for (int i = 0; i < root.length; i++) {
            if (root[i]!=null) {
                String[] info = {root[i].getName(), root[i].getHead() +"",
                    root[i].getVisibility() +"",root[i].getChildrenDfts().length +"" };
                modelo.addRow(info);
                fillWithTree(root[i].getChildrenDfts());
            }
        }
    }
}
