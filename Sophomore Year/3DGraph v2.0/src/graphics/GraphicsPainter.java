package graphics;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphicsPainter extends JPanel {

	public GraphicsPainter()
	{
		//setSize(sizew,sizeh);
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,1000,1000);
	}
}
