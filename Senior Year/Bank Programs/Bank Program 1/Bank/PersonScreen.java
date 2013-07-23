/**
 * PersonScreen.java  1/10/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.io.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
//import javax.swing.JPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.event.*;
import java.awt.print.*;
//TODO add edit listener
public class PersonScreen extends JPanel implements ActionListener,combiner
{
	JPane frame;
	private boolean DEBUG = false;
	JTable table;
	Person b;
	JButton deposit,withdraw,search,remove,exit,main;
	JButton back;
	DecimalFormat money=(DecimalFormat)NumberFormat.getCurrencyInstance();
	{
		money.setNegativePrefix("-$");
		money.setNegativeSuffix("");
		//money.setGroupingSize(3);
	}
	MyTableModel mt;	
	long clicktime=0;
	String bankname;
	BankScreen before;
	double getTotal=0;
	boolean done=false;
    public PersonScreen(Person f,JPane f2,String bankname,BankScreen b2)
    {    	
    	super(new GridLayout(1,0));
    	f2.setTitle(f.getName());
    	before=b2;
    	frame=f2;
    	JPanel tabelPane = new JPanel(new GridLayout(0,1));
    	b=f;
    	table= new JTable(mt=new MyTableModel(f,this));
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        table.setDefaultRenderer(Double.class,new MoneyRenderer(true,money));

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        tabelPane.add(scrollPane);

        JPanel ButtonPane = new JPanel(new GridLayout(0,1));
        
        JPanel other = new JPanel(new GridLayout(1,0));
        //Adds the buttons
        deposit = new JButton("DEPOSIT");
        deposit.setMnemonic(KeyEvent.VK_D);
        deposit.setActionCommand("deposit");
        deposit.setAlignmentX(Component.LEFT_ALIGNMENT);
        deposit.addActionListener(this);
        other.add(deposit);
        
        deposit = new JButton("CLEAR JOBS");
        deposit.setMnemonic(KeyEvent.VK_J);
        deposit.setActionCommand("clear");
        deposit.setAlignmentX(Component.LEFT_ALIGNMENT);
        deposit.addActionListener(this);
        other.add(deposit);
        
        withdraw = new JButton("WITHDRAW");
        withdraw.setMnemonic(KeyEvent.VK_W);
        withdraw.setActionCommand("withdraw");
        withdraw.setAlignmentX(Component.LEFT_ALIGNMENT);
        withdraw.addActionListener(this);
        other.add(withdraw);
        
        ButtonPane.add(other);
		
		
        search = new JButton("ADD JOB");
        search.setMnemonic(KeyEvent.VK_A);
        search.setActionCommand("add");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.addActionListener(this);
        ButtonPane.add(search);
        
        search = new JButton("REMOVE JOB");
        search.setMnemonic(KeyEvent.VK_A);
        search.setActionCommand("remove");
        search.setAlignmentX(Component.CENTER_ALIGNMENT);
        search.addActionListener(this);
        ButtonPane.add(search);
		/*
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
        
        back = new JButton("BACK");
        back.setMnemonic(KeyEvent.VK_B);
        back.setActionCommand("back");
        back.setAlignmentX(Component.RIGHT_ALIGNMENT);
        back.addActionListener(this);
        ButtonPane.add(back);
        
        JButton printButton = new JButton("PRINT");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.setMnemonic(KeyEvent.VK_P);
        printButton.setActionCommand("print");
        printButton.addActionListener(this);
        ButtonPane.add(printButton);

        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(tabelPane, BorderLayout.PAGE_START );
        add(ButtonPane, BorderLayout.PAGE_END );
    }
    public void actionPerformed(ActionEvent e)
    {
    	String g=e.getActionCommand();
        if ("deposit".equals(g))
        {
	//		frame.setVisible(false);
    //    	JPane frame2 = new JPane("Adding Person");
    //    	frame.setDefaultCloseOperation(JPane.EXIT_ON_CLOSE);
            Deposit n=new Deposit(frame,this,b);
            n.setOpaque(true); //content panes must be opaque
   //     	frame2.setContentPane(n);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setTitle("Deposit For "+b.getName());
        	frame.setVisible(true);
        }
        else if ("withdraw".equals(g))
        {
	//		frame.setVisible(false);
    //    	JPane frame2 = new JPane("Adding Person");
    //    	frame.setDefaultCloseOperation(JPane.EXIT_ON_CLOSE);
            Withdraw n=new Withdraw(frame,this,b);
            n.setOpaque(true); //content panes must be opaque
   //     	frame2.setContentPane(n);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setTitle("Withdraw For "+b.getName());
        	frame.setVisible(true);
        }
        else if ("exit".equals(g)||"main".equals(g)||"back".equals(g))
        {
        	em(false);
        	if("exit".equals(g)||"main".equals(g))
        	{
        		done=true;
        		before.statechanged();
        		before.em();
        		if("exit".equals(g))
	        		System.exit(0);
	        	else
	        	{
	        		frame.setVisible(false);
	        		MainPanel.main(null);
	        	}
        	}else
        	{
        		before.statechanged();
	            before.setOpaque(true); //content panes must be opaque
	   //     	frame2.setContentPane(n);
	        	frame.setContentPane(before);
	        	frame.pack();
	        	frame.setSize(1020,819);
	        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
	        	frame.setVisible(true);
        	}
        }
        else if("print".equals(g))
        {
        	print();
        }
        else if("add".equals(g))
        {
        	System.out.println("ADDING");
        	AddJob n=new AddJob(frame,this,b);
            n.setOpaque(true); //content panes must be opaque
   //     	frame2.setContentPane(n);
        	frame.setContentPane(n);
        	frame.pack();
        	frame.setLocation(1020/2-frame.getWidth()/2,819/2-frame.getHeight()/2);
        	frame.setVisible(true);
        }else if("remove".equalsIgnoreCase(g))
        {
        	System.out.println("Remove");
        	String g2=JOptionPane.showInputDialog("Enter Name of Job to Be Removed",this);
        	b.removeJob(new Job(g2,0,0));
        	statechanged();
        }
        else if("clear".equalsIgnoreCase(g))
        {
        	b.clearJobs();
        	b.stateChanged(before.b);
        	statechanged();
        }
    }
    class MyTableModel extends AbstractTableModel
    {
    	int row=-1,col=-1;
    	Person jon;
    	PersonScreen big;
    	int click=0;
    	public MyTableModel(Person f,PersonScreen big)
    	{
    		this.big=big;
    		jon=f;
    		statechanged();
    	}
    	public void statechanged()
    	{
    		ArrayList<Tansaction> list=jon.list;
    		Collections.sort(list);
    		data=new Object[list.size()][columnNames.length];
    		for(int k=0;k<list.size();k++)
    		{
    			Tansaction temp=list.get(k);
    			data[k][0]=temp.getDate();
    			data[k][1]=temp.getDescript();
    			data[k][2]=temp.getChange();
    			data[k][3]=jon.getTotal2(k+1);
    		}
    	}
        private String[] columnNames = {"Date","Description","The Amount","The Total"};
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
        	System.out.println("Editing");
        	if(col<3)
        	{
	        	return true;
        	}else
        	{
        		return false;
        	}
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col)
        {
        	System.out.println("IM changed");
            if (DEBUG)
            {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }			
            data[row][col] = value;
            tablechanged(row,col,data);
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
        
        public Object[][] getData()
        {
        	return data;
        }
    }
    public void statechanged()
    {
    	mt=new MyTableModel(b,this);
    	mt.statechanged();
    	table.setModel(mt);
    	em();
    }
    public void tablechanged(int row,int column,Object[][] dat)
    {
    	ArrayList<Tansaction> list=b.list;
    	ArrayList<Tansaction> list2=new ArrayList<Tansaction>();
    	list.set(row,new Tansaction(""+dat[row][1],((Double)dat[row][2]),((Date)dat[row][0]).getTime()));
    	for(int k=0;k<list.size();k++)
    	{
    		Tansaction t=list.get(k);
    		if(t.getChange()!=0)
    			list2.add(t);
    	}
    	b.list=list2;
    	statechanged();
    }
    public double getTotal()
    {
    	return getTotal;
    }
    public void em(boolean t)
    {
    	done=true;
    	getTotal=b.getTotal2();
    	ArrayList<Tansaction>list=b.list;
    //	System.out.println(list);
    	print p=new print();
    	p.setfilewrite(StaticDirectory.Directory+"\\"+b.getName()+before.b.name);
    	p.println(""+b.getInitial());
    	for(int k=0;k<list.size();k++)
    	{
    		System.out.println(list.get(k));
    		p.println(""+list.get(k));
    	}
    	p.println("Job");
    	for(int k=0;k<b.jobs.size();k++)
    	{
    		System.out.println(b.jobs.get(k));
    		p.println(""+b.jobs.get(k));
    	}
    	if(t)
    	{
        	before.statechanged();
    		before.em();
    	}
    }
    public void em()
    {
    	getTotal=b.getTotal2();
    	ArrayList<Tansaction>list=b.list;
    //	System.out.println(list);
    	print p=new print();
    	p.setfilewrite(StaticDirectory.Directory+"\\"+b.getName()+before.b.name);
    	p.println(""+b.getInitial());
    	for(int k=0;k<list.size();k++)
    	{
    		System.out.println(list.get(k));
    		p.println(""+list.get(k));
    	}
    	p.println("Job");
    	for(int k=0;k<b.jobs.size();k++)
    	{
    		System.out.println(b.jobs.get(k));
    		p.println(""+b.jobs.get(k));
    	}
    }
    public static void em(Person b,Bank b2)
    { 
    	b.getTotal2();
    	ArrayList<Tansaction>list=b.list;
    	print p=new print();
    	p.setfilewrite(StaticDirectory.Directory+"\\"+b.getName()+b2.name);
    	p.println(""+b.getInitial());
    	for(int k=0;k<list.size();k++)
    	{
    		p.println(""+list.get(k));
    	}
    	p.println("Job");
    	for(int k=0;k<b.jobs.size();k++)
    	{
    		System.out.println(b.jobs.get(k));
    		p.println(""+b.jobs.get(k));
    	}
    }
   	public boolean exit()
    {
    	em(true);    	
    	return true;
    }
    public void print()
    {
    	MessageFormat header = new MessageFormat("Page {0,number,integer} "+"For "+b.getName());
    	System.out.println(b.getName());
        try
        {
        	 Printable printer=table.getPrintable(JTable.PrintMode.FIT_WIDTH, header, null);
        	 PrinterJob job = PrinterJob.getPrinterJob();
        	 job.setPrintable(printer);
        	 boolean doPrint = job.printDialog();
        	 if(doPrint)
        	 	job.print();
        	 else
        	 	System.out.println("no print");
        } catch (java.awt.print.PrinterException e1)
        {
            System.err.format("Cannot print %s%n", e1.getMessage());
        }
    }
}