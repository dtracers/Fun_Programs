package tetris.types;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;

import files.FileLoader;
import tetris.Engine;
import tetris.Map.map;
import tetris.Pieces.FutureBlock;
import tetris.Pieces.LivePiece;
import tetris.Pieces.Piece;

public class CrazyShapes extends Engine
{
	public ArrayList<Piece> loadPieces()
	{
		ArrayList<Piece> choosefrom=super.loadPieces();
		FileLoader s=new FileLoader();
		BufferedReader b=s.datFile("Data/Crazy Blocks");
		try
		{
			String g=b.readLine();
			LivePiece p=new FutureBlock();
			p.setColor(Color.decode(g));
			while((g=b.readLine())!=null&&!g.equals(""))
			{
				ArrayList<int[]> list=new ArrayList<int[]>();
				int length=0;
				do
				{
					length=g.length();
					int[] temp=new int[length];
					for(int k=0;k<length;k++)
					{
						if(g.charAt(k)=='X')
							temp[k]=1;
					}
					list.add(temp);
					g=b.readLine();
					System.out.println(g);
				}while(g!=null&&!g.matches("[-]\\d*"));
				System.out.println("Next block");
				int[][] x = new int[1][1];
				x=list.toArray(x);
				((FutureBlock)p).setHeights(getMaxheight());
				p.setMap(new map(x));
		//		System.out.println(g);
		//		System.out.println(Arrays.toString(x));
				choosefrom.add(p);
				p=new FutureBlock();
				if(g!=null)
					p.setColor(Color.decode(g));
			}
		//	System.out.println(g);
		}catch(Exception e)
		{
			System.out.println("Crzy Shapes FLAG 63");
			e.printStackTrace();
		}
		System.out.println("Done");
		setHeightToMax();
		return choosefrom;
	}

}
