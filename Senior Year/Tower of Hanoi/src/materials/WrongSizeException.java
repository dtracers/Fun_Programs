package materials;

public class WrongSizeException extends Exception
{
	public WrongSizeException(String g)
	{
		super(g);
	}
	public WrongSizeException()
	{
		super("disk is too big");
	}
}
