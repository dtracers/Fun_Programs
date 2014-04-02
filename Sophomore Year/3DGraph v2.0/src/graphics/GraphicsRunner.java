package graphics;

import static java.awt.Color.gray;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import resourcces.FileLoader;
public class GraphicsRunner extends JPanel implements ActionListener
{
	public static void main(String args[])
	{	
		GraphicsRunner r=new GraphicsRunner();
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(sizeh,sizew);	
		frame.setContentPane(r);
		frame.pack();
		frame.setSize(sizeh,sizew);
	//	frame.validate();
		frame.setVisible(true);
	}
	JFrame in=new JFrame();
	FileLoader fl=new FileLoader();
	JPanel paint;
	static JFrame frame;
	static int sizeh=1000;
	static int sizew=1000;
	public GraphicsRunner()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		paint=new GraphicsPainter();
//		add(paint);
		JMenuBar s=createMenuBar();
		s.add(paint);
		//add(s);
	}
	public void PaintComponent(Graphics g)
	{
		paint.repaint();
	}
	public JMenuBar createMenuBar()
    {
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        load.setMnemonic(KeyEvent.VK_L);
        load.setContentAreaFilled(false);
        load.setOpaque(false);
        load.addActionListener(this);

        fileMenu.add(load);
        fileMenu.setContentAreaFilled(false);
        fileMenu.setBorderPainted(false);
        fileMenu.setOpaque(false);
        menuBar.add(fileMenu);
        return menuBar;
    }
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub	
	}
}
