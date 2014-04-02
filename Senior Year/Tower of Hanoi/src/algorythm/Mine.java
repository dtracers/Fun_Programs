package algorythm;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import materials.Disk;
import materials.WrongSizeException;

public class Mine extends algy
{
	public Mine(ArrayList<Disk> d,JPanel p)
	{
		super(d, p);
	}

	public Mine()
	{
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
		}else
		{
			int r=2;
		//	if(U%2==1&&W%2==0&&)
			int k=(W-r);
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
		}
	}

	@Override
	public int numberOfMoves(int U, int W, int X, int N)
	{
		if(U<W)
		{
			return (U-1)*2+1;
		}else
		{
			int r=2;
			int k=W-r;
			return numberOfMoves(U-k,W,X,N)*2+numberOfMoves(k,W,X,N);
		}
	}

}
