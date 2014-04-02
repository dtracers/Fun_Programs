package algorythm;

import java.util.ArrayList;

import javax.swing.JPanel;

import materials.Disk;
import materials.WrongSizeException;

public class MineAlt extends Mine {

	public MineAlt(ArrayList<Disk> d, JPanel p) {
		super(d, p);
		// TODO Auto-generated constructor stub
	}

	public MineAlt() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void solve(int U,int W, int X, int N) throws WrongSizeException
	{
		int countemp=super.getCounter();
		int N0=1;
		while(N0==X||N0==N)
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
				while(N0==X||N0==N)
				{
					N0++;
				}
				T++;
			}
			movePeg(U,X,N);
			T=U-1;
			N0=W;
			while(N0==X||N0==N)
			{
				N0--;
			}
			while(T>0)
			{
				movePeg(T,N0,N);
				N0--;
				while(N0==X||N0==N)
				{
					N0--;
				}
				T--;
			}
			String stop="stop";
		}else if(U!=W)
		{
			int r=2;
			int k=0;
			k=W-r;
			solve(U-k,W,X,N0);
			int countemp2=getCounter();
			int N02=1;
			while(N02==X||N02==N||N02==N0)
			{
				N02++;
			}
			int T=U-(k-1);
			boolean HuH=T==U;
			while(T<U)
			{
				movePeg(T,X,N02);
				N02++;
				while(N02==X||N02==N||N02==N0)
				{
					N02++;
				}
				T++;
			}
			movePeg(U,X,N);
			N02=W;
			while(N02==X||N02==N||N02==N0)
			{
				N02--;
			}
			T-=1;
			while(T>U-k)
			{
				movePeg(T,N02,N);
				N02--;
				while(N02==X||N02==N||N02==N0)
				{
					N02--;
				}
				T--;
			}
			solve(U-k,W,N0,N);
			String stop="stop";
		}else
		{
			System.out.println("spreadout");
			spreadout(U,W,X,N,new ArrayList<Integer>(),0);
		}
	}
	public void spreadout(int U, int W, int X, int N, ArrayList<Integer> Nf,int offx) throws WrongSizeException
	{
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

}
