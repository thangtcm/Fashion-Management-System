/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Swing.Table;

import Swing.Scroll.ScrollBar;
import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
/**
 *
 * @author YAN
 */
public class Table extends JTable {
    int borderWidth = 3;
    Border borderLine = BorderFactory.createMatteBorder(0, borderWidth, 0, 0, Color.decode("#4F62CB")); 
    Border borderSpace = BorderFactory.createEmptyBorder(0, borderWidth, 0, 0); 
    Border compoundBorder = BorderFactory.createCompoundBorder(borderLine, borderSpace); 
    Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0); 
    Border centeredBorder = BorderFactory.createCompoundBorder(emptyBorder, compoundBorder); 

    public Table() {
        setRowHeight(50);
        setRowMargin(10);
        
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader h = new TableHeader(o + "");
                return h;
            }
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1); 
                if (o instanceof ModelAction) {
                    ModelAction data = (ModelAction) o;
                    OneAction cell = new OneAction(data, i);
                    if (selected) {
                        cell.setBackground(Color.decode("#4F62CB"));
                        cell.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#4F62CB")), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setBorder(BorderFactory.createEmptyBorder());
                    }
                    return cell;
                }
                if (o instanceof ModelThreeAction) {
                    ModelThreeAction data = (ModelThreeAction) o;
                    ThreeAction cell = new ThreeAction(data, i);
                    if (selected) {
                        cell.setBackground(Color.decode("#4F62CB"));
                        cell.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#4F62CB")), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setBorder(BorderFactory.createEmptyBorder());
                    }
                    return cell;
                }
                else
                {
                    setBorder(noFocusBorder);
                    if (selected) {
                        com.setBackground(Color.decode("#4F62CB"));
                        com.setForeground(Color.WHITE);
                    } else {
                        com.setBackground(Color.WHITE);
                        com.setForeground(Color.decode("#444343"));
                    }
                    if(i1 == 0)  
                        ((JComponent) com).setBorder(centeredBorder);
                    else  
                    {
                        ((JComponent) com).setBorder(BorderFactory.createEmptyBorder());
                    }
                    return com;
                }
            }
        });
    }

    
    @Override
    public TableCellEditor getCellEditor(int row, int col) {
        Object valuObject = getValueAt(row, col);
        if(valuObject instanceof ModelAction)
        {
            if (col == getColumnCount() - 1) {
            return new TableCellAction();
            } else {
                return super.getCellEditor(row, col);
            }
        } 
        else
        {
            if (col == getColumnCount() - 1) {
            return new TableCellTwoAction();
            } else {
                return super.getCellEditor(row, col);
            }
        }
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.setVerticalScrollBar(new ScrollBar());
        scroll.getVerticalScrollBar().setBackground(Color.WHITE);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
}
