import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.PrintStream;
import java.util.EventListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public abstract class Game extends JPanel implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener,
						ActionListener
{
    public int sizew;
    public int sizeh;
	private String pathExtension = "";
	private Arcade back;
    private FileLoader fl;
	private PrintStream ps;
	private Scanner s;
	public Game()
	{
	}
	public JMenuBar createMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic(KeyEvent.VK_S);
        save.setContentAreaFilled(false);
        save.setOpaque(false);
        save.addActionListener(this);
        JMenuItem load = new JMenuItem("Load");
        load.setMnemonic(KeyEvent.VK_L);
        load.setContentAreaFilled(false);
        load.setOpaque(false);
        load.addActionListener(this);
        JMenuItem quit = new JMenuItem("Quit");
        quit.setMnemonic(KeyEvent.VK_Q);
        quit.setContentAreaFilled(false);
        quit.setOpaque(false);
        quit.addActionListener(this);
        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.addSeparator();
        fileMenu.add(quit);
        fileMenu.setContentAreaFilled(false);
        fileMenu.setBorderPainted(false);
        fileMenu.setOpaque(false);


        JMenu helpMenu = new JMenu("Help");
        JMenuItem controls = new JMenuItem("Controls");
        controls.setMnemonic(KeyEvent.VK_C);
        controls.setContentAreaFilled(false);
        controls.setOpaque(false);
        controls.addActionListener(this);
        JMenuItem about = new JMenuItem("About");
        about.setMnemonic(KeyEvent.VK_A);
        about.setContentAreaFilled(false);
        about.setOpaque(false);
        about.addActionListener(this);
        helpMenu.add(controls);
        helpMenu.addSeparator();
        helpMenu.add(about);
        helpMenu.setContentAreaFilled(false);
        helpMenu.setBorderPainted(false);
        helpMenu.setOpaque(false);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.setBounds(0, 23, getWidth(), 26);
		menuBar.setOpaque(true);
        return menuBar;
	}
	public void init()
	{
		runGame();
	}
	public abstract void runGame();
	public void paintComponent(Graphics g)
    {

    }
	public abstract boolean action(ActionEvent e);

	public final void actionPerformed(ActionEvent e)
	{
		if(action(e))
		{

		}
		else if(e.getActionCommand().equalsIgnoreCase("Save")){
			save();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Load")){
			load();
		}
		else if(e.getActionCommand().equalsIgnoreCase("Quit")){
			exit(null);
		}
		else if(e.getActionCommand().equalsIgnoreCase("Controls")){
			controls();
		}
		else if(e.getActionCommand().equalsIgnoreCase("About")){
			about();
		}
	}
	private void myQuit()
	{
		quit();
	}

	public final void exit(JFrame m)
	{
		myQuit();
		if (back != null)
			back.reset(m);
		else
			Arcade.reseting(m);
	}
	public abstract void save();
	public abstract void load();
	public abstract void quit();
	public abstract void controls();
	public abstract void about();
    public abstract void keyPressed(KeyEvent e);
    public abstract void keyReleased(KeyEvent e);
    public void keyTyped(KeyEvent e){}
    public abstract void mousePressed(MouseEvent e);
    public abstract void mouseReleased(MouseEvent e);
    public abstract void mouseEntered(MouseEvent e);
    public abstract void mouseExited(MouseEvent e);
    public void mouseClicked(MouseEvent e){}
    public abstract void mouseDragged(MouseEvent e);
    public abstract void mouseMoved(MouseEvent e);
    public abstract void mouseWheelMoved(MouseWheelEvent e);
	public void setScanner(Scanner s) {
		this.s = s;
	}
	public Scanner getScanner() {
		return s;
	}
	public void setPrintStream(PrintStream ps) {
		this.ps = ps;
	}
	public PrintStream getPrintStream() {
		return ps;
	}
	public void setFileLoader(FileLoader fl) {
		this.fl = fl;
	}
	public FileLoader getFileLoader() {
		return fl;
	}

	public void setFileExtension(String path)
	{
		pathExtension = path.replaceAll("%20", " ");
	}

	public String getPathExtension()
	{
		return pathExtension;
	}

	public final void setBack(Arcade arc)
	{
		back = arc;
	}
}