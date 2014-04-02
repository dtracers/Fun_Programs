package image;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ImageDrawer extends JFrame
{
  BufferedImage image;

  public ImageDrawer(BufferedImage image, String title)
  {
    super(title);
    this.image = image;
  }

  public void paint(Graphics g)
  {
    try {
      g.drawImage(this.image, 20, 20, this);
    }
    catch (Exception e) {
      System.err.println("paint err inside " + getTitle());
      if (this.image != null)
        repaint();
    }
  }
}