
public class linear
{
	static shower show;
	public static void main(String args[])
	{
		show=new shower();
		linear l=new linear();
	//	double[][] matrix={{2,3,1},{4,7,8}};
	//	double[][] matrix={{1,0,3,2},{0,1,-5,7}};
	//	double[][] matrix={{1,0,-1},{0,1,3},{0,0,0}};
		/*
		l.printout(matrix,-1);
		l.onestep(matrix,0);
		l.printout(matrix,0);
		l.onestep(matrix,1);
		l.printout(matrix,1);
		*/
		matrix=l.repeater(matrix);
	}
	public double[][] repeater(double[][] matrix)
	{
		int step=0;
		printout(matrix,-1);
		step=0;
		while(!done(matrix)&&step<matrix.length)
		{
			show.painter(matrix,step);
			onestep(matrix,step);
			delay();
			printout(matrix,step);
			step++;
		}
		show.painter(matrix,step);
		return matrix;
	}
	public boolean done(double[][]matrix)
	{
		for(int k=0;k<matrix.length;k++)
		{
			int counter=0;
			for(int q=0;q<matrix[k].length-1;q++)
			{
				if(matrix[k][q]!=0)
				counter+=1;
			}
			if(counter>1)
				return false;
		}
		for(int k=0;k<matrix[0].length-1;k++)
		{
			int counter=0;
			for(int q=0;q<matrix.length;q++)
			{
				counter+=matrix[q][k];
			}
			if(counter>1)
				return false;
		}
		return true;
	}
	public double[][] onestep(double matrix[][],int step)
	{
		matrix=reorder(matrix,step);
		matrix=dividefirst(matrix,step);
		matrix=replace(matrix,step);
		return matrix;
	}
	double[][] matrix;
	public double[][] replace(double[][] matrix,int step)
	{
		double[] pivot=matrix[step];
		for(int k=0;k<matrix.length;k++)
		{
			if(k!=step)
				matrix[k]=subtract(pivot,matrix[k],step);
		}
		return matrix;
	}
	public void printout(double[][] matrix,int step)
	{
		System.out.println("Step "+step+" {");
		for(int k=0;k<matrix.length;k++)
		{
			for(int q=0;q<matrix[k].length;q++)
			{
				if(q<matrix[k].length-1)
				System.out.print(matrix[k][q]+",");
				else
					System.out.println(matrix[k][q]);
			}
		}
		System.out.println("}");
	}
	public double[] subtract(double[] one,double[] two,int step)
	{
		double multiplier=two[step];
		for(int k=0;k<one.length;k++)
		{
			two[k]-=multiplier*one[k];
		}
		return two;
	}
	public double[][] dividefirst(double[][] matrix,int step)
	{
		double divisor=matrix[step][step];
		for(int k=0;k<matrix[step].length;k++)
		{
			matrix[step][k]/=divisor;
		}
		return matrix;
	}
	public double[][] reorder(double[][] matrix,int step)
	{
		if(matrix[step][step]==0)
		for(int k=0;k<matrix.length;k++)
		{
			if(matrix[step][step]!=0)
			{
				matrix=swap(matrix,k,step);
				k=matrix.length;
			}
		}
		return matrix;
	}
	public double[][] swap(double[][] matrix, int rs,int step)
	{
		double[] temp=matrix[step];
		matrix[step]=matrix[rs];
		matrix[rs]=temp;
		return matrix;
	}
	public void delay()
	{
		long start=System.currentTimeMillis();
		while(System.currentTimeMillis()-start<1000)
		{
			
		}
	}
}
