import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import javax.swing.JFrame;


public class shower extends JFrame
{
	public BufferedImage img;
	public Graphics g;
	public shower()
	{
		setVisible(false);
		setSize(1000,1000);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		img=new BufferedImage(1000,1000,1);
		g=img.getGraphics();
	}
	public void paint(Graphics g)
	{
		g.drawImage(img,3,27,1000,1000,null);
	}
	public void painter(double[][]matrix,int step)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, 1000, 1000);
		g.setColor(Color.black);
		DecimalFormat d=new DecimalFormat("0.0");
		g.drawString(""+step,20,20);
		for(int k=0;k<matrix.length;k++)
		{
			for(int q=0;q<matrix[k].length;q++)
			{
				g.drawString(""+d.format(matrix[k][q]),50+q*40,50+k*20);
			}
		}
		repaint();
	}
}
