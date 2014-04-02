package image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class Grapher2 extends JFrame
{
  private static Grapher2 graphing;
  Graphics g;
  public int lastCounter;
  public int number = 0;
  Color c = Color.black;

  public static void createInstance()
  {
    if (graphing == null)
    {
      graphing = new Grapher2();
      graphing.setTitle("GRAPHING 2");
      graphing.setVisible(false);
      graphing.setSize(1024, 1280);
      graphing.setLocation(0, 0);

      graphing.setVisible(true);
      graphing.setResizable(true);
      graphing.setBackground(Color.black);

      graphing.g = graphing.getGraphics();
    }
  }

  public void paint(Graphics g)
  {
  }

  protected void draw()
  {
    this.g.setColor(this.c);
    this.g.drawLine(this.number + 20, this.lastCounter + 30, this.number + 20, this.lastCounter + 30);
  }

  public static void Graph(Point p, Color c) {
    graphing.number = (int)p.getX();
    graphing.Graph((int)p.getY(), c, true);
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

  public static void reset()
  {
    graphing.getGraphics().clearRect(0, 0, graphing.getWidth(), graphing.getHeight());
    graphing.lastCounter = 0;
    graphing.number = 0;
    graphing.c = Color.black;
  }
}