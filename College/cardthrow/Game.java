import static java.lang.System.*;
import static java.awt.Color.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent.*;
import java.awt.Event.*;
import java.awt.image.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.*;
import java.util.*;
import sun.audio.*;

public abstract class Game extends JPanel implements KeyListener,EventListener,
						MouseListener,MouseMotionListener,MouseWheelListener,
						ActionListener
{
	public ArrayList<Timer> time=new ArrayList<Timer>();
    public int sizew;
    public int sizeh;
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
        return menuBar;
	}
	public void init()
	{
		runGame();
	}
	public void addTimer(int time,String actionCommand,Game g)
	{
		Timer t=new Timer(time,g);
		t.setActionCommand(actionCommand);
		this.time.add(t);
	}
	public void startAllTimers()
	{
		for(int k=0;k<time.size();k++)
		{
			startTimer(k);
		}
	}
	public void startTimer(int which)
	{
		time.get(which).start();
	}
	public void stopAllTimers()
	{
		for(int k=0;k<time.size();k++)
		{
			stopTimer(k);
		}
	}
	public void stopTimer(int which)
	{
		time.get(which).stop();
	}
	public abstract void runGame();
	public void paintComponent(Graphics g)
    {

    }
	public abstract boolean action(ActionEvent e);
	public void actionPerformed(ActionEvent e)
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
			myQuit();
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
		Arcade.main(null);
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
}