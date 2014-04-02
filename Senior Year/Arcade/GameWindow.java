import java.awt.Component;
import java.awt.Container;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;


public class GameWindow extends JFrame
{
	protected void processWindowEvent(WindowEvent paramWindowEvent)
	{
		super.processWindowEvent(paramWindowEvent);
		System.out.println("window event!!!");
		if (paramWindowEvent.getID() == 201)
		{
			System.out.println("window closing!!!");
			Container contentpane=this.getContentPane();
			reset(contentpane, this);
			Component[] inside = this.getComponents();
			
			for (Component comp : inside)
			{
				System.out.println(comp);
				reset(comp, this);
			}
			// this means reset to the game window
		}
	}

	private void reset(Component comp, JFrame main)
	{
		if (comp instanceof Game)
		{
			System.out.println("I am a game inside a game window");
			// we go to the other one maybe fire an event that = main
			((Game) comp).exit(main);
		}
	}
}
