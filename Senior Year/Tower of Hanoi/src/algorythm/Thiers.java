package algorythm;

import java.util.ArrayList;

import javax.swing.JPanel;

import materials.Disk;
import materials.WrongSizeException;

public class Thiers extends algy
{

	public Thiers(ArrayList<Disk> d, JPanel p)
	{
		super(d, p);
		// TODO Auto-generated constructor stub
	}
	public Thiers() {
		// TODO Auto-generated constructor stub
	}
	public void solve(int U,int W,int X,int N,ArrayList<Integer> Nf,int offx) throws WrongSizeException
	{
		int Wtemp=W;
		W-=Nf.size();
	/*	JOptionPane.showMessageDialog(getPanel(), "the pegs are "+offx+" through "+(U+offx)+"\nmoving from "+X+" To "+N+"\nbut i cant move on "+Nf
				+"\nthe number of pegs i can use is "+W);*/
	//	int countemp=super.getCounter();
		int N0=1;
		while(N0==X||N0==N||Nf.contains(N0))
		{
			N0++;
		}
		if(U<W)
		{
			int T=1;
			while(T<U)
			{
				movePeg(T+offx,X,N0);
				N0++;
				while(N0==X||N0==N||Nf.contains(N0))
				{
					N0++;
				}
				T++;
			}
			movePeg(U+offx,X,N);
			T=U-1;
			N0=W;
			while(N0==X||N0==N||Nf.contains(N0))
			{
				N0--;
			}
			while(T>0)
			{
				movePeg(T+offx,N0,N);
				N0--;
				while(N0==X||N0==N||Nf.contains(N0))
				{
					N0--;
				}
				T--;
			}
			String stop="Stop";
		}else //if(U!=W)
		{
			int k=U/2;
			ArrayList<Integer> temp;
			/*
			if(U%2==0&&Wtemp%2==1&&U-3<=W&&U-1>=W&&W>3)
			{
				System.out.println("U is "+U+" W is "+W);
				ArrayList<Disk> di=new ArrayList<Disk>();
				ArrayList<Disk> dk=getDisk();
				for(int t=0;t<U;t++)
				{
					di.add(dk.get(t+offx));
				}
				Thiers x=new Thiers(di,getPanel());
				x.setOther(this);
				x.solve(U,W,X,N,Nf,0);
				return;
			}*/
			if(U==W)
			{
				System.out.println("Thiers spreadout");
				spreadout(U,W,X,N,Nf,offx);
				return;
			}
			else if(W*2==U&&U>=30)
			{
				System.out.println("double size");
				solve2(U,W,X,N,Nf,offx);
			}
			else if(Wtemp==3)
			{
				algy x=new Mine(getDisk(),getPanel());
				x.setOther(this);
				x.solve(U,W,X,N);
				return;
			}
			else if(W<=3)
			{
				System.out.println("U is "+U+" W is 3 "+W+"unusable spaces are "+Nf);
				solve2(U, W, X, N,Nf,offx);
			}else
			{
				System.out.println("first is "+k+" second is "+(U-k)+" W is "+W);
				temp=new ArrayList<Integer>();
				temp.addAll(Nf);
				solve(k,Wtemp,X,N0,temp,offx);
				temp=new ArrayList<Integer>();
				temp.addAll(Nf);
				temp.add(N0);
				solve(U-k,Wtemp,X,N,temp,k+offx);
				temp=new ArrayList<Integer>();
				temp.addAll(Nf);
				solve(k,Wtemp,N0,N,temp,offx);
				String stop="stop";
			}
		}
		String superStop="SuperStop";
	}
	public void spreadout(int U, int W, int X, int N, ArrayList<Integer> Nf,int offx) throws WrongSizeException
	{
		if(X==N)
			return;
		int N0=1;
		while(N0==X||N0==N||Nf.contains(N0))
		{
			N0++;
		}
		int N01=N0;
		int N02=N0;
		while(N02==X||N02==N||Nf.contains(N02)||N02==N0)
		{
			N02++;
		}
		movePeg(1+offx,X,N0);
		movePeg(2+offx,X,N02);
		movePeg(1+offx,N0,N02);
		Nf.add(N02);
		int T=3;
		while(T<U)
		{
			movePeg(T+offx,X,N0);
			N0++;
			while(N0==X||N0==N||Nf.contains(N0))
			{
				N0++;
			}
			T++;
		}
		movePeg(U+offx,X,N);
		T=U-1;
		N0=W;
		while(N0==X||N0==N||Nf.contains(N0))
		{
			N0--;
		}
		while(T>2)
		{
			movePeg(T+offx,N0,N);
			N0--;
			while(N0==X||N0==N||Nf.contains(N0))
			{
				N0--;
			}
			T--;
		}
		movePeg(1+offx,N02,N01);
		movePeg(2+offx,N02,N);
		movePeg(1+offx,N01,N);
		String stop="stop";
	}
	public void solve2(int U,int W, int X, int N,ArrayList<Integer> Nf,int offx) throws WrongSizeException
	{
		int countemp=super.getCounter();
		int N0=1;
		while(N0==X||N0==N||Nf.contains(N0))
		{
			N0++;
		}
		if(U<W)
		{
			int T=1;
			while(T<U)
			{
				movePeg(T,X,N0);
				N0++;
				while(N0==X||N0==N||Nf.contains(N0))
				{
					N0++;
				}
				T++;
			}
			movePeg(U,X,N);
			T=U-1;
			N0=W;
			while(N0==X||N0==N||Nf.contains(N0))
			{
				N0--;
			}
			while(T>0)
			{
				movePeg(T,N0,N);
				N0--;
				while(N0==X||N0==N||Nf.contains(N0))
				{
					N0--;
				}
				T--;
			}
			String stop2="stop";
		}else if(U!=W)
		{
			int r=2;
			int k=(W-r);
			solve2(U-k,W,X,N0,Nf,offx);
			int countemp2=getCounter();
			int N02=1;
			while(N02==X||N02==N||N02==N0||Nf.contains(N02))
			{
				N02++;
			}
			int T=U-(k-1);
			boolean HuH=T==U;
			while(T<U)
			{
				movePeg(T,X,N02);
				N02++;
				while(N02==X||N02==N||N02==N0||Nf.contains(N02))
				{
					N02++;
				}
				T++;
			}
			movePeg(U,X,N);
			N02=W;
			while(N02==X||N02==N||N02==N0||Nf.contains(N02))
			{
				N02--;
			}
			T-=1;
			while(T>U-k)
			{
				movePeg(T,N02,N);
				N02--;
				while(N02==X||N02==N||N02==N0||Nf.contains(N02))
				{
					N02--;
				}
				T--;
			}
			solve2(U-k,W,N0,N,Nf,offx);
			String stop2="stop";
		}else
		{
			System.out.println("thier spredout 2");
			spreadout(U,W,X,N,Nf,offx);
		}
		String superStop="SuperStop";
	}

	@Override
	public void solve(int U,int W, int X, int N) throws WrongSizeException
	{
		ArrayList<Integer> list=new ArrayList<Integer>();
		solve(U,W,X,N,list,0);
	}

	@Override
	public int numberOfMoves(int U, int W, int X, int N)
	{
		if(U<W)
		{
			return (U-1)*2+1;
		}else
		{
			int k=0;
			if(U>W&&W>3)
			{
				k=U/2;
			}else if(W>3)
			{
				k=W-2;
			}
			if(U==W||W<=3||(W*2==U&&U>=30))
			{
				return numberOfMoves2(U,W,X,N);
			}
			return numberOfMoves(k,W,X,N)*2+numberOfMoves(U-k,W-1,X,N);
		}
	}
	public int numberOfMoves2(int U, int W, int X, int N)
	{
		if(U<W)
		{
			return (U-1)*2+1;
		}else
		{
			int r=2;
			int k=W-r;
			return numberOfMoves2(U-k,W,X,N)*2+
			numberOfMoves2(k,W,X,N);
		}
	}

}
