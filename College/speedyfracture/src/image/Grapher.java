package image;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Grapher extends JFrame
{
  private static Grapher graphing;
  public int lastCounter;
  public int number = 0;
  Color c = Color.black;
  Graphics g;

  public static void createInstance()
  {
    if (graphing == null)
    {
      graphing = new Grapher();
      graphing.setTitle("GRAPHING 1");
      graphing.setVisible(false);
      graphing.setSize(3000, 3000);
      graphing.setLocation(0, 0);

      graphing.setVisible(true);
      graphing.setResizable(true);
      graphing.setBackground(Color.white);

      graphing.g = graphing.getGraphics();
    }
  }

  public void paint(Graphics g)
  {
  }

  public static void Graph(int newCounter, Color c)
  {
    graphing.Graph(newCounter, c, true);
    graphing.repaint();
  }

  public void Graph(int newCounter, Color c, boolean f) {
    this.lastCounter = newCounter;
    this.c = c;
    if (this.number > getWidth() - 20)
    {
      this.number = 0;
    }

    draw();
    graphing.number = (this.number + 1);
  }

  protected void draw()
  {
    this.g.setColor(this.c);
    this.g.drawLine(this.number + 10, getHeight() - this.lastCounter - 10, this.number + 10, getHeight() - this.lastCounter - 10);
  }

  public static void reset() {
    graphing.getGraphics().clearRect(0, 0, graphing.getWidth(), graphing.getHeight());
    graphing.lastCounter = 0;
    graphing.number = 0;
    graphing.c = Color.black;
  }
}