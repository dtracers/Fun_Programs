package tetris;

import java.awt.Color;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import tetris.Map.DeadBoard;
import tetris.Map.LiveMap;
import tetris.Map.map;
import tetris.Movement.Direction;
import tetris.Movement.PlaceChecker;
import tetris.Pieces.FakeDeadPiece;
import tetris.Pieces.FutureBlock;
import tetris.Pieces.LivePiece;
import tetris.Pieces.Piece;
import Interface.Updateable;
import files.FileLoader;

public class Engine implements Updateable
{
	public String extension;
	private boolean first=true;
	private int Height=30,Width=20;
	private int maxheight[]=new int[Width];
	private int maxheight2=0;
	public int NumberOfBlocksAtATime=1;
	
	/**
	 * this will contain all the blocks for to be randomly chosen from
	 */
	private ArrayList<Piece> choosefrom=new ArrayList<Piece>();
	private Queue<Piece> next=new LinkedList<Piece>();	
	private ArrayList<Piece> liveones=new ArrayList<Piece>();
	private ArrayList<Piece> fakedeadones=new ArrayList<Piece>();
	private ArrayList<Piece> deadones=new ArrayList<Piece>();
	
	private map dead=new DeadBoard(new int[Height][Width]);
	{
		((DeadBoard)dead).setDeadWithin(deadones);
		((DeadBoard)dead).setContainedZombies(fakedeadones);
	}
	private LiveMap live=new LiveMap(new int[Height][Width],getLiveBlocks());
	PlaceChecker check=new PlaceChecker(getDeadMap(),getLiveBlocks());
	Random r=new Random();
	
	@Override
	public void update(long time)
	{
		for(int k=0;k<fakedeadones.size();k++)
		{
			fakedeadones.get(k).update(time);
		}
		if(getLiveBlocks().size()>=NumberOfBlocksAtATime)
		{
			for(int k=0;k<getLiveBlocks().size();k++)
			{
				Piece l=getLiveBlocks().get(k);
				l.update(time);
				if(check.canMove(Direction.Down, l))
				{
					l.setY(l.getY()+1);
				}else if(!l.isPiceUnderneath())
				{
					Piece die=l.die(getDeadMap());
					int indexof=liveones.indexOf(l);
					k-=1;		
					if(die!=null)
					{
						if(die instanceof FakeDeadPiece)
						{
							getFakeDeadBlocks().add(die);
						}else
							getDeadBlocks().add(die);
					}
					clearLine();
					setHeightToMax();
					setMax(dead);
					setMax(die);
					
					liveones.set(indexof,nextPiece());
			//		System.out.println("Dead Board\n"+getDeadMap().printBoard());
				}
			}
			//System.out.println("Live Board\n"+getLiveMap().printBoard());
		}
		else
		{
			liveones.add(nextPiece());
		}
	}
	public Piece peek()
	{
		return next.peek();
	}
	public Piece nextPiece()
	{
		Piece p=makeRandomPiece();
		if(p instanceof FutureBlock)
		{
			((FutureBlock) p).setHeights(maxheight);
			p.setX(p.getX());
		}
		return p;
	}
	public ArrayList<Piece> loadPieces()
	{
		extension = extension.replaceAll("%20", " ");
		FileLoader s = new FileLoader();
		BufferedReader b = s.datFile(extension + "Files/Data/Normal Blocks");
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
			System.out.println("FLAG 122");
			e.printStackTrace();
		}
		System.out.println("Done");
		setHeightToMax();
		return choosefrom;
	}
	private Piece makeRandomPiece()
	{
		if(first)
		{
			choosefrom=loadPieces();
			loadPieces();
			first=false;
			getNext().add(choosefrom.get(r.nextInt(choosefrom.size())).copy());
		}
		Piece p=getNext().poll();
		int rotate=r.nextInt(4);
		for(int k=0;k<rotate;k++)
		{
			p=p.rotate(Direction.ClockWise);
		}
		int counter=0;
		p.setY(0);
		do
		{
			counter+=1;
			p.setX(r.nextInt(getDeadMap().getWidth()-p.getMap().getWidth()));
		}while(!check.canMove(Direction.Stay,p)&&counter<100);
		if(counter>=100)
			Lost();
		getNext().add(choosefrom.get(r.nextInt(choosefrom.size())).copy());
		return p;
	}
	public boolean moveLeft(int index)
	{
		if(liveones.size()>0)
		{
			Piece p=getLiveBlocks().get(index);
			if(check.canMove(Direction.Left,p))
			{
				p.setX(p.getX()-1);
				return true;
			}
		}
		return false;
	}
	public boolean moveRight(int index)
	{
		if(liveones.size()>0)
		{
			Piece p=getLiveBlocks().get(index);
			if(check.canMove(Direction.Right,p))
			{
				p.setX(p.getX()+1);
				return true;
			}
		}
		return false;
	}
	public boolean moveDown(int index)
	{
		if(liveones.size()>0)
		{
			Piece p=getLiveBlocks().get(index);
			if(check.canMove(Direction.Down,p))
			{
				p.setY(p.getY()+1);
				return true;
			}
		}
		return false;
	}
	public boolean Rotate(Direction d,int i)
	{
		if(liveones.size()>0)
		{
			Piece p=getLiveBlocks().get(i);
			if(check.canRotate(d,p))
			{
				((LivePiece)p).Rotate(d);
				return true;
			}
		}
		return false;
	}
	public void setLiveMap(LiveMap live) {
		this.live = live;
	}
	public LiveMap getLiveMap() {
		return live;
	}
	public void setDeadMap(map dead) {
		this.dead = dead;
	}
	public map getDeadMap() {
		return dead;
	}
	public void setLiveBlocks(ArrayList<Piece> liveones) {
		this.liveones = liveones;
	}
	public ArrayList<Piece> getLiveBlocks() {
		return liveones;
	}
	public void setDeadBlocks(ArrayList<Piece> deadones) {
		this.deadones = deadones;
	}
	public ArrayList<Piece> getDeadBlocks() {
		return deadones;
	}
	public void setFakeDeadBlocks(ArrayList<Piece> fakedeadones) {
		this.fakedeadones = fakedeadones;
	}
	public ArrayList<Piece> getFakeDeadBlocks() {
		return fakedeadones;
	}
	public void clearLine()
	{
		//FIXME
	//	System.out.println("I AM CLEARING THE LINE");
	//	System.out.println("THE MAX HEIGHT IS: "+maxheight2);
		int bo[][]=dead.getBoard();
		ArrayList<Integer> s=new ArrayList<Integer>();
		for(int k=bo.length-1;k>=maxheight2;k--)
		{
			int max=bo[k].length;
			int counter=0;
			for(int q=0;q<bo[k].length;q++)
			{
				if(bo[k][q]==1)
					counter++;
			}
			if(counter==max)
				s.add(k);
		}
	//	System.out.println("The lines that need to be cleared are "+s);
		if(s.size()>0)
		{
			dead.setBoard(clearLine(s));
			setHeightToMax();
			for(int k=0;k<deadones.size();k++)	
			{
				setMax(deadones.get(k));
			}
		}
	}
	public int[][] clearLine(ArrayList<Integer> indexs)
	{
		int bo[][]=new int[dead.getHeight()][dead.getWidth()];
		for(int w=0;w<deadones.size();w++)
		{
			Piece p=deadones.get(w);
			int y=p.getY();
			ArrayList<Integer>index2=new ArrayList<Integer>();
			int movecounter=0;
			for(int t=0;t<indexs.size();t++)
			{
				int index=indexs.get(t);
				if(index>=y&&index<y+p.getMap().getHeight())
				{
					index2.add(index-y);
				}
				else if(index>=y+p.getMap().getHeight())
				{
					//this is so that it will move down for every cleared line that is completly below it
					movecounter+=1;
				}
			}
			if(clearLine(index2,p))
			{
				p.setY(p.getY()+movecounter+index2.size());
				int[][]block=p.getMap().getBoard();
				y=p.getY();
				int x=p.getX();
				for(int k=0;k<block.length;k++)
				{
					for(int q=0;q<block[0].length;q++)
					{
						if(block[k][q]==1)
							bo[k+y][q+x]=block[k][q];
					}
				}
			}else
			{
				deadones.remove(p);
				w-=1;
			}
		}
		return bo;
	}
	public boolean clearLine(ArrayList<Integer> index,Piece p)
	{
		int[][]bo=p.getMap().getBoard();
		if(bo.length-index.size()<=0)
		{
			return false;
		}
		int[][]block=new int[bo.length-index.size()][bo[0].length];
		for(int k=0;k<index.size();k++)
		{
			int i=index.get(k);
			for(int q=0;q<bo[i].length;q++)
			{
				bo[i][q]=2;
			}
		}
		int counter=0;
		int counter2=0;
		while(counter<block.length)	
		{
			if(bo[counter2][0]==2)
			{
				counter2++;
			}else
			{
				for(int k=0;k<block[0].length;k++)
				{
					block[counter][k]=bo[counter2][k];
				}
				counter++;
				counter2++;
			}
		}
		p.setMap(new map(block));
		return true;
	}
	private void Lost()
	{
		System.out.println("Awwww I lost");
	}
	private void setMax(Piece die)
	{
		int y=dead.getHeight();
		if(die!=null)
			y=die.getY();
		else
			return;
		if(maxheight2==0)
			Lost();
		else if(y<maxheight2)
		{
			maxheight2=y;
		}
		int x=die.getX();
		int block[][]=die.getMap().getBoard();
		/**
		 * this is cool because it traverses by opposite of normal by going y:x instead of x:y
		 */
		for(int q=0;q<block[0].length;q++)
		{
			boolean found=false;
			int counter=0;
			while(!found&&counter<block.length)
			{
				if(block[counter][q]==1)
				{
					found=true;
					if(y+counter<getMaxheight()[x+q])
					{
						System.out.println("IM a max at "+(x+q)+" X and "+(counter+y)+" Y");
						getMaxheight()[x+q]=y+counter;
					}
				}
				counter+=1;
			}
		}
	}
	protected void setHeightToMax()
	{
		maxheight2=dead.getHeight();
		for(int k=0;k<getMaxheight().length;k++)
		{
			getMaxheight()[k]=maxheight2;
		}
	}
	private void setMax(map dead)
	{
		int[][]dead2=dead.getBoard();
		for(int q=0;q<dead2[0].length;q++)
		{
			int k=0;
			while(k<dead2.length&&dead2[k][q]==0)
			{
				k+=1;
			}
			this.maxheight[q]=k;
		}
	}
	private void reset()
	{
		dead=new map(new int[Height][Width]);
		liveones=new ArrayList<Piece>();
		fakedeadones=new ArrayList<Piece>();
		deadones=new ArrayList<Piece>();
		dead=new map(new int[Height][Width]);
		live=new LiveMap(new int[Height][Width],getLiveBlocks());
		check=new PlaceChecker(getDeadMap(),getLiveBlocks());
		setHeightToMax();
	}
	public boolean metRequirement()
	{
		return liveones.size()>=NumberOfBlocksAtATime;
	}
	public void setHeight(int height)
	{
		Height = height;
	}
	public int getHeight()
	{
		return Height;
	}
	public void setWidth(int width)
	{
		Width = width;
	}
	public int getWidth()
	{
		return Width;
	}
	public void setMaxheight(int maxheight[])
	{
		this.maxheight = maxheight;
	}
	public int[] getMaxheight()
	{
		return maxheight;
	}
	public void setNext(Queue<Piece> next) {
		this.next = next;
	}
	public Queue<Piece> getNext() {
		return next;
	}
}