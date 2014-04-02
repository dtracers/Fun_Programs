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
import tetris.Pieces.other.BombBlock;
import tetris.Pieces.other.PushBlock;

public class CoolEngine extends Engine
{
	public ArrayList<Piece> loadPieces()
	{
		ArrayList<Piece> choosefrom=super.loadPieces();
		try
		{
			LivePiece p=new BombBlock();
			System.out.println("Next block");
			int[][] x ={{1}};
			((FutureBlock)p).setHeights(getMaxheight());
			p.setMap(new map(x));
			choosefrom.add(p);
			
			
			p=new PushBlock();
			System.out.println("Next block");
			x =new int[][]{{1}};
			((FutureBlock)p).setHeights(getMaxheight());
			p.setMap(new map(x));
			choosefrom.add(p);
		}catch(Exception e)
		{
			System.out.println("CoolEngine FLAG 63");
			e.printStackTrace();
		}
		System.out.println("Done");
		setHeightToMax();
		System.out.println("Done");
		return choosefrom;
	}

}
