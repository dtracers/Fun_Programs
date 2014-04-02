package math.algorthms;


public abstract class Algorithm
{
	boolean garbageCollect=true;
	public abstract void presolve();
	public abstract boolean solve();
	public abstract void postsolve();
	/**
	 * Computes the algorithm
	 * @return
	 */
	public final boolean compute()
	{
		if(garbageCollect)
		{
			System.gc();
		}
		presolve();
		boolean solved=solve();
		postsolve();
		return solved;
	}
	public abstract void init();
	public abstract void copyData(Algorithm s);
	public abstract void addData(Algorithm s);
}
