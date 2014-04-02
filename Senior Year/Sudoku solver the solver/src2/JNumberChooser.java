package main.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;

import javax.accessibility.Accessible;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import main.tables.NumberChooserTableModel;

import Variables.StaticV;

import Items.Box;

import sun.swing.SwingUtilities2;

public class JNumberChooser extends JPanel implements Accessible
{
	Box theBox;
	JTable table;
	public JNumberChooser()
	{
        this(new Box(0,0));
    }
	public JNumberChooser(Box box)
	{
		theBox=box;
		table=new JTable(new NumberChooserTableModel());
		TableModel s=table.getModel();
		int counterup=1;
		for(int k=0;k<StaticV.squareSize;k++)
		{
			for(int q=0;q<StaticV.squareSize;q++)
			{
				if(theBox.getNotes().contains(""+counterup))
				{
					s.setValueAt(""+counterup, k, q);
				}else
				{
					s.setValueAt(" ", k, q);
				}
				counterup++;
			}
		}
		table.setGridColor(Color.black);
		table.setCellSelectionEnabled(true);
		add(table);
	}
	public static JDialog createDialog(Component c, String title, boolean mode,JNumberChooser chooserPane, ActionListener okListener,ActionListener cancelListener) throws HeadlessException
	{
		//	 Window window = JOptionPane.getWindowForComponent(c);
		NumberChooserDialog dialog;
		if (c instanceof Frame)
		{
			Frame window=JOptionPane.getFrameForComponent(c);
			dialog = new NumberChooserDialog((Frame)window, title, mode, c, chooserPane,okListener, cancelListener);
			return dialog;
		}
		Dialog g=new JOptionPane().createDialog(c, title);
		{
			dialog = new NumberChooserDialog((Dialog)g, title, mode, c, chooserPane,okListener, cancelListener);
		}
		return dialog;
	}
	public static Box showDialog(Component component,String title, Box initialBox) throws HeadlessException
	{
		final JNumberChooser pane = new JNumberChooser(initialBox != null?initialBox : new Box(0,0));
	
	    NumberTracker ok = new NumberTracker(pane);
	    JDialog dialog = createDialog(component, title, true, pane, ok, null);
	
	    dialog.show(); // blocks until user brings dialog down...
	
	    return ok.getNumber();
	}
	public void setIntialNumber(Box b)
	{
		theBox=b;
		TableModel s=table.getModel();
		int counterup=1;
		for(int k=0;k<StaticV.squareSize;k++)
		{
			for(int q=0;q<StaticV.squareSize;q++)
			{
				if(theBox.getNotes().contains(""+counterup))
				{
					s.setValueAt(""+counterup, k, q);
				}else
				{
					s.setValueAt(" ", k, q);
				}
				counterup++;
			}
		}
		table.setGridColor(Color.black);
	}
	public void setIntialNumberStar(Box b)
	{
		theBox=b;
		b.reset();
		b.getContainer().check(theBox);
		TableModel s=table.getModel();
		int counterup=1;
		for(int k=0;k<StaticV.squareSize;k++)
		{
			for(int q=0;q<StaticV.squareSize;q++)
			{
				if(theBox.getNotes().contains(""+counterup))
				{
					s.setValueAt(""+counterup, k, q);
				}else
				{
					s.setValueAt(" ", k, q);
				}
				counterup++;
			}
		}
		table.setGridColor(Color.black);
	}
	public Box getNumber()
	{
		int c=table.getSelectedColumn();
		int r=table.getSelectedRow();
		TableModel s=table.getModel();
		String g=""+s.getValueAt(r,c);
		try
		{
			int y=Integer.parseInt(g);
			theBox.setPen(""+y);
		}catch(Exception e)
		{
			
		}
		return theBox;
	}
	 
}
class NumberChooserDialog extends JDialog implements KeyListener,TableModelListener
{
	private JNumberChooser chooserPane;
	private JButton cancelButton;
	ActionListener okListener;
	public NumberChooserDialog(Dialog owner, String title, boolean modal,Component c, JNumberChooser chooserPane,
	ActionListener okListener, ActionListener cancelListener)throws HeadlessException 
	{
	    super(owner, title, modal);
	    initNumberChooserDialog(c, chooserPane, okListener, cancelListener);
	}
	public NumberChooserDialog(Frame owner, String title, boolean modal,Component c, JNumberChooser chooserPane,
	ActionListener okListener, ActionListener cancelListener)throws HeadlessException
	{
	    super(owner, title, modal);
	    initNumberChooserDialog(c, chooserPane, okListener, cancelListener);
	}
	protected void initNumberChooserDialog(Component c, JNumberChooser chooserPane,ActionListener okListener, ActionListener cancelListener)
	{
		this.okListener=okListener;
		addKeyListener(this);
		chooserPane.addKeyListener(this);
		chooserPane.table.addKeyListener(this);
		this.chooserPane = chooserPane;
		String okString = UIManager.getString("ColorChooser.okText");
		String cancelString = UIManager.getString("ColorChooser.cancelText");
		String resetString = UIManager.getString("ColorChooser.resetText");
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(chooserPane, BorderLayout.CENTER);
	
		/*
		 * Create Lower button panel
		 */
	    JPanel buttonPane = new JPanel();
	    buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JButton okButton = new JButton(okString);
	    okButton.setMnemonic(KeyEvent.VK_ENTER);
	    getRootPane().setDefaultButton(okButton);
	    okButton.setActionCommand("OK");
	    okButton.addActionListener(new ActionListener()
	    {
		    @SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
		    {
		        hide();
		    }
		});
		if (okListener != null)
		{
		    okButton.addActionListener(okListener);
		}
		buttonPane.add(okButton);
	
		cancelButton = new JButton(cancelString);
	
		// The following few lines are used to register esc to close the dialog
		Action cancelKeyAction = new AbstractAction()
		{
	        public void actionPerformed(ActionEvent e)
	        {
	        	AbstractButton j=((AbstractButton)e.getSource());
	            j.doClick();
	        }
	    }; 
	    KeyStroke cancelKeyStroke = KeyStroke.getKeyStroke((char)KeyEvent.VK_ESCAPE, false);
	    InputMap inputMap = cancelButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = cancelButton.getActionMap();
	    if (inputMap != null && actionMap != null)
	    {
	    	inputMap.put(cancelKeyStroke, "cancel");
	    	actionMap.put("cancel", cancelKeyAction);
	    }
	    // end esc handling
	
	    cancelButton.setActionCommand("cancel");
	    cancelButton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		hide();
	    	}
	    });
	    if (cancelListener != null)
	    {
	    	cancelButton.addActionListener(cancelListener);
	    }
	    buttonPane.add(cancelButton);
	
	    JButton resetButton = new JButton(resetString);
	    resetButton.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    //		reset();
	    	}
	    });
	    int mnemonic = SwingUtilities2.getUIDefaultsInt("ColorChooser.resetMnemonic", -1);
	    if (mnemonic != -1)
	    {
	        resetButton.setMnemonic(mnemonic);
	    }
	    contentPane.add(buttonPane, BorderLayout.SOUTH);
	
	    if (JDialog.isDefaultLookAndFeelDecorated())
	    {
	        boolean supportsWindowDecorations = 
	        UIManager.getLookAndFeel().getSupportsWindowDecorations();
	        if (supportsWindowDecorations)
	        {
	            getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
	        }
	    }
	    applyComponentOrientation(((c == null) ? getRootPane() : c).getComponentOrientation());
	
	    pack();
	    setLocationRelativeTo(c);
	
	    this.addWindowListener(new Closer());
	    this.addComponentListener(new DisposeOnClose());
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(KeyEvent e)
	{
		System.out.println("impresseed");
		int number=0;
		int id=e.getKeyCode();
	//	System.out.println("id "+id);
		switch(id)
		{
			case KeyEvent.VK_1:number=1;break;
			case KeyEvent.VK_2:number=2;break;
			case KeyEvent.VK_3:number=3;break;
			case KeyEvent.VK_4:number=4;break;
			case KeyEvent.VK_5:number=5;break;
			case KeyEvent.VK_6:number=6;break;
			case KeyEvent.VK_7:number=7;break;
			case KeyEvent.VK_8:number=8;break;
			case KeyEvent.VK_9:number=9;break;
			case KeyEvent.VK_ENTER:hide();okListener.actionPerformed(new ActionEvent(new Object(), 0, "DUH DUH DUH"));break;
			default:System.out.println("default");return;
		}
		if(number!=0)
		{
			int row=(number-1)/StaticV.squareSize;
			int column=(number-1)%StaticV.squareSize;
		//	System.out.println(row+" row "+column+" column");
			chooserPane.table.changeSelection(row,column, false, false);
		}
	}
	public void keyReleased(KeyEvent e)
	{
	}
	
    class Closer extends WindowAdapter implements Serializable
    {
        public void windowClosing(WindowEvent e)
        {
            cancelButton.doClick(0);
            Window w = e.getWindow();
            w.hide();
        }
    }

    static class DisposeOnClose extends ComponentAdapter implements Serializable
    {
        public void componentHidden(ComponentEvent e)
        {
            Window w = (Window)e.getComponent();
            w.dispose();
        }
    }

	@Override
	public void tableChanged(TableModelEvent e)
	{
		//hide();
	}

}

class NumberTracker implements ActionListener, Serializable
{
    JNumberChooser chooser;
    Box number;

    public NumberTracker(JNumberChooser c)
    {
        chooser = c;
    }

    public void actionPerformed(ActionEvent e)
    {
        number = chooser.getNumber();
    }

    public Box getNumber()
    {
        return number;
    }
}