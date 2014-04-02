import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import sun.audio.*;
//import javax.swing.JPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class ScheduleScreen extends JPanel implements ActionListener,combiner,MouseListener
{
	JTable table;
	public ScheduleScreen(String string, JPane frame) {
		// TODO Auto-generated constructor stub
	}

	public boolean exit() {
		// TODO Auto-generated method stub
		return false;
	}

/**
 * BankScreen.java  1/10/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */

//TODO add person looker and tansaction adder
//celebate yeahhhhhhhhhhhhhhhhh!!!!!!!!!!!!!
public class BankScreen extends JPanel implements ActionListener,combiner,MouseListener
{
	JTable table;
	Bank b;
	JButton add,search,remove,exit,main,trans;
	DecimalFormat money=(DecimalFormat)NumberFormat.getCurrencyInstance();
	{
		money.setNegativePrefix("-$");
		money.setNegativeSuffix("");
	}
	MyTableModel mt;
	long clicktime=0;
	int row=-1;
	PersonScreen theperson;
    public BankScreen(Bank f,JPane f2)
    {
    	super(new GridLayout(1,1));
    	f2.setTitle("Bank Period "+f.name);
    	frame=f2;
    	addMouseListener(this);
    	JPanel tabelPane = new JPanel(new GridLayout(1,1));
    	b=f;
    	table= new JTable(mt=new MyTableModel(f,this));
    	table.addMouseListener(this);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(Double.class,new MoneyRenderer(true,money));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        tabelPane.add(scrollPane);

        JPanel ButtonPane = new JPanel(new GridLayout(0,1));
       // ButtonPane.setSize(100,100);
        //Adds the buttons
        add = new JButton("ADD");
        add.setMnemonic(KeyEvent.VK_A);
        add.setActionCommand("add");
        add.setAlignmentX(Component.LEFT_ALIGNMENT);
        add.addActionListener(this);
        ButtonPane.add(add);

		trans = new JButton("TRANS");
        trans.setMnemonic(KeyEvent.VK_T);
        trans.setActionCommand("trans");
        trans.setAlignmentX(Component.LEFT_ALIGNMENT);
        trans.addActionListener(this);
        ButtonPane.add(trans);

        trans = new JButton("MULTI TRANS");
        trans.setMnemonic(KeyEvent.VK_T);
        trans.setActionCommand("mtrans");
        trans.setAlignmentX(Component.LEFT_ALIGNMENT);
        trans.addActionListener(this);
        ButtonPane.add(trans);

        JButton apply = new JButton("APPLY JOB");
        apply.setMnemonic(KeyEvent.VK_T);
        apply.setActionCommand("apply");
        apply.setAlignmentX(Component.LEFT_ALIGNMENT);
        apply.addActionListener(this);
        ButtonPane.add(apply);
		/*
        search = new JButton("SEARCH");
        search.setMnemonic(KeyEvent.VK_S);
        search.setActionCommand("search");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.addActionListener(this);
        ButtonPane.add(search);

        remove = new JButton("REMOVE");
        remove.setMnemonic(KeyEvent.VK_R);
        remove.setActionCommand("remove");
        remove.setAlignmentX(Component.RIGHT_ALIGNMENT);
        remove.addActionListener(this);
        ButtonPane.add(remove);
		*/

        exit = new JButton("EXIT");
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setActionCommand("exit");
        exit.setAlignmentX(Component.RIGHT_ALIGNMENT);
        exit.addActionListener(this);
        ButtonPane.add(exit);

        main = new JButton("MAIN");
        main.setMnemonic(KeyEvent.VK_M);
        main.setActionCommand("main");
        main.setAlignmentX(Component.RIGHT_ALIGNMENT);
        main.addActionListener(this);
        ButtonPane.add(main);

        JButton printButton = new JButton("PRINT");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.setMnemonic(KeyEvent.VK_P);
        printButton.setActionCommand("print");
        printButton.addActionListener(this);
		ButtonPane.add(printButton);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(tabelPane, BorderLayout.PAGE_START );
        add(ButtonPane, BorderLayout.SOUTH);
    }
    public void actionPerformed(ActionEvent e)
    {
    	String g=e.getActionCommand();
        if ("add".equals(g))
        {
	//		frame.setVisible(false);
    //    	JFrame frame2 = new JFrame("Adding Person");
    //    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Addperson n=new Addperson(frame,this,b);
            n.setOpaque(true); //content panes must be opaque
   //     	frame2.setContentPane(n);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setSize(1020,819);
        	frame.setVisible(true);
        }
        else if ("exit".equals(g)||"main".equals(g))
        {
        	ArrayList<Person>list=b.list;
        	print p=new print();
        	p.setfilewrite(b.name);
       // 	p.println(LoadBank.password);
        	for(int k=0;k<list.size();k++)
        	{
        		p.println(""+list.get(k));
        	}
        	if("exit".equals(g))
        		System.exit(0);
        	else
        	{
        		frame.setVisible(false);
        		MainPanel.main(null);
        	}
        }
        else if("print".equals(g))
        {
        	MessageFormat header = new MessageFormat("Page {0,number,integer}");
	        try
	        {
	        	System.out.println("Printing");
	            System.out.println(table.print(JTable.PrintMode.FIT_WIDTH, header, null));
	            System.out.println("Opened printer");
	        } catch (java.awt.print.PrinterException e1)
	        {
	            System.err.format("Cannot print %s%n", e1.getMessage());
	        }
        }
        else if("trans".equals(g))
        {
            Transfer n=new Transfer(frame,this);
            n.setOpaque(true); //content panes must be opaque
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
       // 	frame.setSize(1020,819);
        	frame.setVisible(true);
        }
        else if("mtrans".equals(g))
        {
            Transfer2 n=new Transfer2(frame,this);
            n.setOpaque(true); //content panes must be opaque
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setSize(1020,819);
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }
        else if("apply".equals(g))
        {
        	JobMaker n=new JobMaker(frame,this,b);
        	n.setOpaque(true); //content panes must be opaque
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
       // 	frame.setSize(1020,819);
        	frame.setVisible(true);
        }
    }
    class MyTableModel extends AbstractTableModel
    {
    	int row=-1,col=-1;
    	Bank jon;
    	BankScreen big;
    	public MyTableModel(Bank f,BankScreen big2)
    	{
    		big=big2;
    		jon=f;
    		statechanged();
    	}
    	public void statechanged()
    	{
    		ArrayList<Person> list=jon.list;
    		data=new Object[list.size()][columnNames.length];
    		for(int k=0;k<list.size();k++)
    		{
    			Person temp=list.get(k);
    			data[k][0]=temp.getFirstName();
    			data[k][1]=temp.getLastName();
    			data[k][2]=temp.getTotal();
    		}
    	}
        private String[] columnNames = {"First Name","Last Name","Total",};
        private Object[][] data;
        public int getColumnCount()
        {
            return columnNames.length;
        }
        public int getRowCount()
        {
            return data.length;
        }
        public String getColumnName(int col)
        {
            return columnNames[col];
        }
        public Object getValueAt(int row, int col)
        {
            return data[row][col];
        }
        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c)
        {
            return getValueAt(0, c).getClass();
        }
        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col)
        {
        	if(this.row==row&&this.col==col)
        	{
        		long newtime=System.currentTimeMillis();
        		long bigtime=newtime-clicktime;
        		if(bigtime<1000)
        		{
        			if(big.table.getSelectedRowCount()==1)
        			big.selectperson(row);
        		}
        	}else
        	{
        		this.row=row;this.col=col;
        		clicktime=System.currentTimeMillis();
        	}
       // 	System.out.println("AM I Editable??");
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            return false;
        }
        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col)
        {
            if (DEBUG)
            {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            // Normally, one should call fireTableCellUpdated() when
            // a value is changed.  However, doing so in this demo
            // causes a problem with TableSorter.  The tableChanged()
            // call on TableSorter that results from calling
            // fireTableCellUpdated() causes the indices to be regenerated
            // when they shouldn't be.  Ideally, TableSorter should be
            // given a more intelligent tableChanged() implementation,
            // and then the following line can be uncommented.
            // fireTableCellUpdated(row, col);

            if (DEBUG)
            {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData()
        {
            int numRows = getRowCount();
            int numCols = getColumnCount();
            for (int i=0; i < numRows; i++)
            {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++)
                {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
    public void statechanged()
    {
    	em();
    	print p=new print();
    	p.setfile(b.name);
    	b=mt.jon=PersonDecoder.getBank(p,b.name);
//    	if(theperson!=null&&theperson.done)
//    	{
//	    	Person f=b.list.get(row);
//	    	f.setTotal(theperson.getTotal());
//	    	System.out.println("The new Total:"+f.getTotal());
//    	}
    	mt.statechanged();
	    table.setModel(mt);
    }
    public void selectperson(int row)
    {
    	this.row=row;
    	Person f=b.list.get(row);
    	String name=f.getName();
    	f=PersonDecoder.getCompletePerson(name,b.name);
    //	b.list.set(row,f);
    	frame.setVisible(false);
    	frame = new JPane("Making Bank");
    	frame.setDefaultCloseOperation(JPane.EXIT_ON_CLOSE);
        theperson=new PersonScreen(f,frame,name,this);
        theperson.setOpaque(true); //content panes must be opaque
    	frame.setContentPane(theperson);
    	frame.pack();
    	frame.setSize(1020,819);
    	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
    	frame.setExit(theperson);
    	frame.setVisible(true);
    }
    public void em()
    {
    	ArrayList<Person>list=b.list;
    	print p=new print();
    	p.setfilewrite(b.name);
   // 	p.println(LoadBank.password);
    	for(int k=0;k<list.size();k++)
    	{
    		Person p2=PersonDecoder.getCompletePerson(list.get(k).toString2(),b.name);
    		p.println(""+p2);
    	}
    }
    public boolean exit()
    {
    	System.out.println("Exiting");
    	em();
    	return true;
    }
    public void mouseClicked(MouseEvent e)
    {
    	if(e.getButton()==e.BUTTON3)
    	{
    		System.out.println("threeeeeee");
    		int[] tables=table.getSelectedRows();
    		Person[] lis=new Person[tables.length];
    		for(int k=0;k<lis.length;k++)
    		{
	    		Person f=b.list.get(tables[k]);
	    		String name=f.getName();
	    		f=PersonDecoder.getCompletePerson(name,b.name);
	    		lis[k]=f;
    		}
    		Mult n=new Mult(frame,this,lis);
            n.setOpaque(true); //content panes must be opaque
   //     	frame2.setContentPane(n);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setVisible(true);
    	}
    }
 	public void mouseEntered(MouseEvent e)
 	{
    }
 	public void mouseExited(MouseEvent e)
 	{
    }
 	public void mousePressed(MouseEvent e)
 	{
    }
 	public void mouseReleased(MouseEvent e)
 	{
    }
	public Date getDate()
	{
		DatePanel s=new DatePanel(frame,this);
		s.setOpaque(true); //content panes must be opaque
    	frame.setContentPane(s);
    	frame.pack();
    	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
   // 	frame.setSize(1020,819);
    	frame.setVisible(true);
		while(s.current==null)
		{}
		return s.current;
	}
}