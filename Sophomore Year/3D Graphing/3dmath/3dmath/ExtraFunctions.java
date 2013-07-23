public class ExtraFunctions
{
    public ExtraFunctions()
    {
    }
    private double[][] intersection(Tree one,Tree two)
    {
    	return null;
    }
    public double[][] intersection(Tree... t)
    {
    	if(t.length==2)
    	{
    		return intersection(t[0],t[1]);
    	}
    	return null;
    }
}