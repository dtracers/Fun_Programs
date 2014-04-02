package algorythm;

import graphics.GraphicsRunner;

import java.util.ArrayList;

import javax.swing.JPanel;

import materials.Disk;
import materials.WrongSizeException;

public abstract class algy
{
	private int counter=0;
	private ArrayList<Disk> d;
	private JPanel p;
	private algy other;
	public algy()
	{
		
	}
	public algy(ArrayList<Disk> d,JPanel p)
	{
		this.setPanel(p);
		this.setDisk(d);
	}
	public void paintstep()
	{
		((GraphicsRunner)(getPanel())).setNMoves2(""+getCounter());
		//System.out.println("painting");
		getPanel().repaint();
		long start=System.currentTimeMillis();
		while(System.currentTimeMillis()-100<start)
		{
			
		}
	}
	public abstract void solve(int disks,int pegs,int frompeg,int topeg) throws WrongSizeException;
	public void movePeg(int disk,int frompeg,int topeg) throws WrongSizeException
	{
		if(getOther()!=null)
		{
			other.setCounter(getCounter() + 1+other.getCounter());
		}
		setCounter(getCounter() + 1);
		Disk k=getDisk().get(disk-1);
		k.setPeg(topeg);
		paintstep();
	}
	public abstract int numberOfMoves(int disk,int pegs,int frompeg,int topeg);
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public int getCounter() {
		return counter;
	}
	public void setDisk(ArrayList<Disk> d) {
		this.d = d;
	}
	public ArrayList<Disk> getDisk() {
		return d;
	}
	public void setPanel(JPanel p) {
		this.p = p;
	}
	public JPanel getPanel() {
		return p;
	}
	public void setOther(algy other) {
		this.other = other;
	}
	public algy getOther() {
		return other;
	}
}
