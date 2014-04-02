import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;


public class MyJButton extends JButton
{
	private ArrayList<Object> information = new ArrayList<Object>();
	public MyJButton()
	{
	}

	public MyJButton(Icon arg0)
	{
		super(arg0);
	}

	public MyJButton(String arg0)
	{
		super(arg0);
	}

	public MyJButton(Action arg0)
	{
		super(arg0);
	}

	public MyJButton(String arg0, Icon arg1)
	{
		super(arg0, arg1);
	}

	public void addInfo(Object arg)
	{
		information.add(arg);
	}

	public Object getInfo(int index)
	{
		return information.get(index);
	}

	public int InfoSize()
	{
		return information.size();
	}
}
