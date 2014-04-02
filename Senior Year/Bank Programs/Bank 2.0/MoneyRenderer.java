import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import java.text.*;
public class MoneyRenderer extends JFormattedTextField
                           implements TableCellRenderer {
    Border unselectedBorder = null;
    Border selectedBorder = null;
    boolean isBordered = true;
    public MoneyRenderer(boolean isBordered,Format f)
    {
    	super(f);
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }
    public MoneyRenderer(boolean isBordered)
    {
        this.isBordered = isBordered;
        setOpaque(true); //MUST do this for background to show up.
    }
    public Component getTableCellRendererComponent(JTable table,Object color,
    boolean isSelected,boolean hasFocus,
    int row, int column)
    {
        double num = (Double)color;
        if(num<0)
        {
        	setForeground(Color.red);
        }
        if(num>=0)
        {
        	setForeground(Color.black);
        }
        setValue(num);
        if (isBordered) {
            if (isSelected) {
                if (selectedBorder == null) {
                    selectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getSelectionBackground());
                }
                setBorder(selectedBorder);
            } else {
                if (unselectedBorder == null) {
                    unselectedBorder = BorderFactory.createMatteBorder(2,5,2,5,
                                              table.getBackground());
                }
                setBorder(unselectedBorder);
            }
        }
        return this;
    }
}

