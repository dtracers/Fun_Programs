/*
003400020
645200030
000038094
007506048
009000300
860307200
710960000
090003152
030002700
*/
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Variables.StaticV;

import Items.Grid;

public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		Grid g=new Grid();
		g.createGrid();
		BufferedReader r=new BufferedReader(new FileReader(new File("Runner6.txt")));
		// TODO Auto-generated method stub
		//r.readLine();
		System.out.println("BEFORE FILLING");
		System.out.println(g);
		for(int k=0;k<StaticV.gridSize;k++)
		{
			String s=r.readLine();
			System.out.println(s);
			for(int q=0;q<StaticV.gridSize;q++)
			{
		//		System.out.println(g.getBox(q,k));
				g.getBox(q,k).setPen(""+s.charAt(q));
			}
		}
		for(int t=0;t<10;t++)
		{
		//	System.out.println("BEFORE CHECKING "+t +" times");
		//	System.out.println(g);
			for(int k=0;k<StaticV.gridSize;k++)
			{
				for(int q=0;q<StaticV.gridSize;q++)
				{
					g.check(g.getBox(q,k));
				}
			}
		//	System.out.println("AFTER CHECKING "+t +" times");
		//	System.out.println(g);
		//System.out.println("BEFORE CHECKING 1 time");
		//System.out.println(g);		
		for(int k=0;k<StaticV.gridSize;k++)
		{
			g.check(g.getColumn()[k]);
			g.check(g.getRow()[k]);
		}
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				g.check(g.getSquare()[k][q]);
			}
		}
		//System.out.println("AFTER CHECKING 1 time");
		//System.out.println(g);
		}
		System.out.println("AFTER Finishing");
		System.out.println(g);
	}
	public static void checkOnce(Grid g)
	{
		for(int k=0;k<StaticV.gridSize;k++)
		{
			for(int q=0;q<StaticV.gridSize;q++)
			{
				g.check(g.getBox(q,k));
			}
		}	
		for(int k=0;k<StaticV.gridSize;k++)
		{
			g.check(g.getColumn()[k]);
			g.check(g.getRow()[k]);
		}
		for(int k=0;k<3;k++)
		{
			for(int q=0;q<3;q++)
			{
				g.check(g.getSquare()[k][q]);
			}
		}
	}
}
