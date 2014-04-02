package main.tables;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import main.graphic.JNumberChooser;

import Items.Box;

public class BoxEditor extends AbstractCellEditor implements TableCellEditor,ActionListener
{
	Box item;
	JButton button;
	JDialog dialog;
	JNumberChooser numberChooser;
	protected static final String EDIT = "edit";
	public BoxEditor()
	{
		button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        numberChooser=new JNumberChooser();
        dialog=JNumberChooser.createDialog(button, "Pick a Number", true, numberChooser, this, null);
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		item=(Box)value;
		System.out.println("im here first");
		System.out.println(item);
	/*	if(j.isSuperPen())
		{
			item=j;
		}*/
		return button;
	}

	@Override
	public Object getCellEditorValue()
	{
		// TODO Auto-generated method stub
		return item;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
        if (EDIT.equals(e.getActionCommand()))
        {
            if(!item.isSuperPen())
            {
            	if(item.getPen().equals("0"))
            		numberChooser.setIntialNumber(item);
            	else
            		numberChooser.setIntialNumberStar(item);
            	dialog.setVisible(true);
            }
            fireEditingStopped();

        }else
        {
            item = numberChooser.getNumber();
        }
	}

}


/*
public class ColorEditor extends AbstractCellEditor
                         implements TableCellEditor,
			            ActionListener {
    Color currentColor;
    JButton button;
    JColorChooser colorChooser;
    JDialog dialog;
    protected static final String EDIT = "edit";

    public ColorEditor() {
        //Set up the editor (from the table's point of view),
        //which is a button.
        //This button brings up the color chooser dialog,
        //which is the editor from the user's point of view.
        button = new JButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);

        //Set up the dialog that the button brings up.
        colorChooser = new JColorChooser();
        dialog = JColorChooser.createDialog(button,
                                        "Pick a Color",
                                        true,  //modal
                                        colorChooser,
                                        this,  //OK button handler
                                        null); //no CANCEL button handler
    }

    /**
     * Handles events from the editor button and from
     * the dialog's OK button.
     */
/*
    public void actionPerformed(ActionEvent e) {
        if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setBackground(currentColor);
            colorChooser.setColor(currentColor);
            dialog.setVisible(true);

            //Make the renderer reappear.
            fireEditingStopped();

        } else { //User pressed dialog's "OK" button.
            currentColor = colorChooser.getColor();
        }
    }

    //Implement the one CellEditor method that AbstractCellEditor doesn't.
    public Object getCellEditorValue() {
        return currentColor;
    }

    //Implement the one method defined by TableCellEditor.
    public Component getTableCellEditorComponent(JTable table,
                                                 Object value,
                                                 boolean isSelected,
                                                 int row,
                                                 int column) {
        currentColor = (Color)value;
        return button;
    }
}
*/