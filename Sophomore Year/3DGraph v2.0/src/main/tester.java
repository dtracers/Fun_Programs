package main;

import javax.swing.JOptionPane;

import equations.EqMaker;
import equations.Functions;
import equations.Tree;


public class tester
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		EqMaker s=new EqMaker();
		String g=JOptionPane.showInputDialog("Insert functions or done to stop");
		while(!g.equals("done"))
		{
			Functions.addFunction(g);
			g=JOptionPane.showInputDialog("Insert functions or done to stop");
		}
		System.out.println(Functions.database);
		String g2=JOptionPane.showInputDialog("Insert equation or done to stop");
		while(!g.equals("done"))
		{
			s=new EqMaker();
			s.spaceEq(g);
//			s.spaceEq("(5+7)+86+(7*8)-56");
			System.out.println("made to this point");
			Tree t=s.getTree();
			System.out.println("made it to the tree");
			String g3=JOptionPane.showInputDialog("Insert variable : value or done to stop");
			while(!g3.equals("done"))
			{
				String[]g4=g3.split(":");
				t.setVariable(g4[0],Integer.parseInt(g4[1]));
				g3=JOptionPane.showInputDialog("Insert variable : value or done to stop");
			}
			
		//	t.setVariable("t",7);
			System.out.println("value "+t.getNum());
			System.out.println("the tree" + t);
			System.out.println("the tree value"+t.toString2());
			g2=JOptionPane.showInputDialog("Insert equation or done to stop");
		}/*
//		s.spaceEq("5+(8*(16-15");
		s.spaceEq("f:(h)+f:(t-6)+g:(h,t)");
//		s.spaceEq("(5+7)+86+(7*8)-56");
		Tree t=s.getTree();
		t.setVariable("h",2);
		t.setVariable("t",7);
		System.out.println(t.getNum());
		System.out.println(t);
		System.out.println(t.toString2());*/
		// TODO Auto-generated method stub
	}
}