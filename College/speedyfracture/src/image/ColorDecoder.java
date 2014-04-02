package image;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import javax.swing.JFrame;

public class ColorDecoder
{
  ArrayList<JFrame> frames;
  BufferedImage image;
  BufferedImage image2;
  BufferedImage copy;
  Map<Color, BufferedImage> colorsets;
  ArrayList<Color> differentColors;
  Map<Color, ArrayList<Point>> colorMap;
  int intense = 5;
  int alg = 0;
  String fileName;
  Point[][] pointBuffer;

  public ColorDecoder(BufferedImage x, String f)
  {
    this.fileName = f;
    this.image = x;

    Grapher2.createInstance();
  }

  public void initColors()
  {
    this.colorMap = new HashMap();
    this.colorsets = new HashMap();
  }

  private void addColor(int one, int two, int three)
  {
    if ((one < 256) && (two < 256) && (three < 256))
    {
      Color c = new Color(one, two, three);
      if (!this.differentColors.contains(c))
      {
        this.differentColors.add(c);
        addColor(one + 127, two, three);
        addColor(one, two + 127, three);
        addColor(one, two, three + 127);
      }
    }
    else;
  }

  public double preFracture(double intensity, int type)
  {
    this.copy = null;
    this.alg = type;
    this.intense = (int)intensity;
    if ((this.differentColors == null) || (this.colorMap == null) || (this.colorsets == null))
    {
      initColors();
      revertColors();
    } else {
      revertColors();
    }double compare = 64.0D / intensity;
    return compare;
  }

  public void postFracture() {
    if (this.image2 == null)
    {
      this.image2 = createCleanCopy(this.image);
    }
    resetImage(this.image2);
    for (Color c : this.differentColors)
    {
      MakeImage(c, this.image2);
    }
  }

  public void FractureColors(double intensity, int type)
  {
    Grapher2.reset();

    double compare = preFracture(intensity, type);
    if (type <= 3)
    {
      System.out.println(compare);
      int height = this.image.getHeight();
      int width = this.image.getWidth();
      ArrayList otherPoints = new ArrayList();
      for (int y = 0; y < height; y++)
      {
        for (int x = 0; x < width; x++)
        {
          Color c2 = new Color(this.image.getRGB(x, y));

          Grapher2.Graph(new Point(x, y), c2);
          switch (type)
          {
          case 0:
          default:
            for (Color c : this.differentColors)
            {
              if (isSameColor(c, c2, compare))
              {
                ((ArrayList)this.colorMap.get(c)).add(new Point(x, y));
              }
            }
            break;
          case 1:
          case 2:
          case 3:
            boolean isMapped = false;
            for (Color c : this.differentColors)
            {
              if (isSameColor(c, c2, compare))
              {
                isMapped = true;
                ((ArrayList)this.colorMap.get(c)).add(new Point(x, y));
              }
            }
            if ((!isMapped) && (type == 1))
            {
              addColor(c2);
              ((ArrayList)this.colorMap.get(c2)).add(new Point(x, y));
            } else if ((!isMapped) && ((type == 2) || (type == 3)))
            {
              otherPoints.add(new Point(x, y));
            }
            break;
          }
        }
      }
      switch (type) {
      default:
        break;
      case 0:
        FractureColors(intensity);
        break;
      case 1:
        FractureColorsImproved(intensity);
        break;
      case 2:
        FractureColorsSplitMethod(intensity, otherPoints);
        break;
      case 3:
        FractureColorsRecursive(intensity, otherPoints);
        break;
      }
    }
    else {
      FractureColorsAlternateMethod(compare, intensity, type);
    }
    postFracture();
  }

  private void FractureColors(double intensity)
  {
  }

  private void FractureColorsImproved(double intensity)
  {
  }

  private void FractureColorsSplitMethod(double intensity, ArrayList<Point> otherPoints)
  {
    this.copy = createCleanCopy(this.image);
    for (Point p : otherPoints)
    {
      this.copy.setRGB((int)p.getX(), (int)p.getY(), this.image.getRGB((int)p.getX(), (int)p.getY()));
    }
  }

  private void FractureColorsRecursive(double intensity, ArrayList<Point> otherPoints)
  {
    this.copy = createCleanCopy(this.image);
    for (Point p : otherPoints)
    {
      this.copy.setRGB((int)p.getX(), (int)p.getY(), this.image.getRGB((int)p.getX(), (int)p.getY()));
    }
  }

  public void FractureColorsAlternateMethod(double intensity, double inens, int type)
  {
    this.colorsets = new HashMap();
    this.differentColors = new ArrayList();
    ArrayList allPoints = new ArrayList();
    int height = this.image.getHeight();
    int width = this.image.getWidth();
    int split = height / 50;
    int quadSplitY = height / split;
    int quadSplitX = width / split;
    System.out.println(height / split + " " + width / split);
    boolean simple = false;
    if ((quadSplitY == height % split) && (quadSplitX == width % split))
      simple = true;
    split += (simple ? 0 : 1);
    System.out.println("is simple?" + simple + " split is" + split);
    ArrayList[][] allSplitPoints = new ArrayList[split][split];

    if (simple)
    {
      for (int k = 0; k < split; k++)
      {
        for (int q = 0; q < split; q++)
        {
          ArrayList tempList = new ArrayList();
          for (int y = quadSplitY * k; y < quadSplitY * (k + 1); y++)
          {
            for (int x = quadSplitX * q; x < quadSplitX * (q + 1); x++)
            {
              Point p = new Point(x, y);
              tempList.add(p);
            }
          }

          allSplitPoints[k][q] = tempList;
        }
      }
    }
    else
    {
      for (int k = 0; k < split; k++)
      {
        for (int q = 0; q < split; q++)
        {
          ArrayList tempList = new ArrayList();
          int maxY = Math.min(quadSplitY * (k + 1), height);
          int maxX = Math.min(quadSplitX * (q + 1), width);
          for (int y = quadSplitY * k; y < maxY; y++)
          {
            for (int x = quadSplitX * q; x < maxX; x++)
            {
              Point p = new Point(x, y);
              tempList.add(p);
            }
          }

          allSplitPoints[k][q] = tempList;
        }
      }
    }
    System.out.println("Starting");

    PrintStream backup = System.out;
    try {
      PrintStream printing = new PrintStream(new FileOutputStream(new File(this.fileName + type + " out" + split / 2 + ".txt")));
      System.setOut(printing);
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    allPoints.add(new Point(0, 0));
    long time1 = System.currentTimeMillis();
    while (allPoints.size() > 0)
    {
      Point p = (Point)allPoints.get(0);
      Color c = new Color(this.image.getRGB((int)p.getX(), (int)p.getY()));
      allPoints = FractureColorsAlternateIterative(allSplitPoints, allPoints, 
        p, width, height, quadSplitX, quadSplitY, intensity, c);
    }
    long time2 = System.currentTimeMillis();
    System.out.println("DONE! Total Time = " + (time2 - time1));
    this.differentColors.addAll(this.colorMap.keySet());
    System.setOut(backup);
    System.out.println("Done!");

    if (type >= 6)
    {
      System.out.println("runs left = " + (type - 5));
      postFracture();
      this.image = this.image2;
      FractureColors(inens - 0.05D, type - 1);
    }
  }

  private ArrayList<Point> FractureColorsAlternateIterative(ArrayList<Point>[][] allSplitPoints, ArrayList<Point> allPoints, Point currentPoint, int width, int height, int quadSplitX, int quadSplitY, double intensity, Color c) {
    int skip = 1;

    ArrayList deadPoints = new ArrayList();
    ArrayList usedPoints = new ArrayList();
    Stack stack = new Stack();
    stack.add(new DirectedPoint(currentPoint, -1));
    long time1 = System.currentTimeMillis();
    while (!stack.isEmpty())
    {
      DirectedPoint direct = (DirectedPoint)stack.pop();
      currentPoint = direct.p;

      int direction = direct.direction;
      direct = null;
      int x = (int)currentPoint.getX();
      int y = (int)currentPoint.getY();
      int quadX = x / quadSplitX;
      int quadY = y / quadSplitY;
      if ((x >= 0) && (x < width) && (y >= 0) && (y < height))
      {
        if (!deadPoints.contains(currentPoint))
        {
          if (allSplitPoints[quadY][quadX].contains(currentPoint))
          {
            Color c2 = new Color(this.image.getRGB(x, y));
            if (isSameColor(c2, c, intensity))
            {
              c2 = null;
              usedPoints.add(currentPoint);
              Grapher2.Graph(currentPoint, c);
              if (direction != 2)
              {
                DirectedPoint p2 = new DirectedPoint(new Point(x, y - skip), 3);
                stack.push(p2);
              }

              if (direction != 3)
              {
                DirectedPoint p2 = new DirectedPoint(new Point(x, y + skip), 2);
                stack.push(p2);
              }

              if (direction != 0)
              {
                DirectedPoint p2 = new DirectedPoint(new Point(x - skip, y), 1);
                stack.push(p2);
              }

              if (direction != 1)
              {
                DirectedPoint p2 = new DirectedPoint(new Point(x + skip, y), 0);
                stack.push(p2);
              }
            }
            else
            {
              c2 = null;

              deadPoints.add(currentPoint);
              continue;
            }

            allSplitPoints[quadY][quadX].remove(currentPoint);
          }
        }
      }
    }
    long time2 = System.currentTimeMillis();

    if (this.colorMap.containsKey(c))
    {
      ArrayList p = (ArrayList)this.colorMap.get(c);
      p.add(new Point(-1, -1));
      p.addAll(usedPoints);
    }
    else {
      this.colorMap.put(c, usedPoints);
    }

    ArrayList newPoints = new ArrayList();
    if ((time2 - time1 > 50L) || (usedPoints.size() > 50) || (allPoints.size() < 2))
    {
      for (int k = 0; k < allSplitPoints.length; k++)
      {
        for (int q = 0; q < allSplitPoints[k].length; q++)
        {
          newPoints.addAll(allSplitPoints[k][q]);
        }
      }
    }
    else {
      allPoints.removeAll(usedPoints);
      newPoints = allPoints;
    }

    return newPoints;
  }

  private ArrayList<Point> FractureColorsAlternateIterativeMemorySaving(ArrayList<Point>[][] allSplitPoints, ArrayList<Point> allPoints, Point currentPoint, int width, int height, int quadSplitX, int quadSplitY, double intensity, Color c) {
    int skip = 1;

    ArrayList deadPoints = new ArrayList();
    ArrayList usedPoints = new ArrayList();
    Stack stack = new Stack();
    stack.add(new DirectedPoint(currentPoint, -1));
    long time1 = System.currentTimeMillis();
    while (!stack.isEmpty())
    {
      DirectedPoint direct = (DirectedPoint)stack.pop();
      currentPoint = direct.p;

      int direction = direct.direction;
      direct = null;
      int x = (int)currentPoint.getX();
      int y = (int)currentPoint.getY();
      int quadX = x / quadSplitX;
      int quadY = y / quadSplitY;

      if (!deadPoints.contains(currentPoint))
      {
        if (allSplitPoints[quadY][quadX].contains(currentPoint))
        {
          Color c2 = new Color(this.image.getRGB(x, y));
          if (isSameColor(c2, c, intensity))
          {
            c2 = null;
            usedPoints.add(currentPoint);
            Grapher2.Graph(currentPoint, c);
            if (direction != 2)
            {
              if (y - skip > 0)
              {
                DirectedPoint p2 = new DirectedPoint(this.pointBuffer[(y - skip)][x], 3);
                stack.push(p2);
              }
            }
            if (direction != 3)
            {
              if (y + skip < height)
              {
                DirectedPoint p2 = new DirectedPoint(this.pointBuffer[(y + skip)][x], 2);
                stack.push(p2);
              }
            }
            if (direction != 0)
            {
              if (x - skip > 0)
              {
                DirectedPoint p2 = new DirectedPoint(this.pointBuffer[y][(x - skip)], 1);
                stack.push(p2);
              }
            }
            if (direction != 1)
            {
              if (x + skip < width)
              {
                DirectedPoint p2 = new DirectedPoint(this.pointBuffer[y][(x + skip)], 0);
                stack.push(p2);
              }
            }
          }
          else {
            c2 = null;

            deadPoints.add(currentPoint);
            continue;
          }

          allSplitPoints[quadY][quadX].remove(currentPoint);
        }
      }
    }
    long time2 = System.currentTimeMillis();

    if (this.colorMap.containsKey(c))
    {
      ArrayList p = (ArrayList)this.colorMap.get(c);
      p.add(new Point(-1, -1));
      p.addAll(usedPoints);
    }
    else {
      this.colorMap.put(c, usedPoints);
    }

    ArrayList newPoints = new ArrayList();
    if ((time2 - time1 > 50L) || (usedPoints.size() > 50))
    {
      for (int k = 0; k < allSplitPoints.length; k++)
      {
        for (int q = 0; q < allSplitPoints[k].length; q++)
        {
          newPoints.addAll(allSplitPoints[k][q]);
        }
      }
    }
    else {
      allPoints.removeAll(usedPoints);
      newPoints = allPoints;
    }

    return newPoints;
  }

  public void MakeImage(Color c)
  {
    BufferedImage setting = (BufferedImage)this.colorsets.get(c);
    if (setting == null)
    {
      setting = createCleanCopy(this.image);
      this.colorsets.put(c, setting);
    }
    MakeImage(c, setting);
  }

  public void MakeImage(Color c, BufferedImage setting) {
    if (setting == null)
    {
      setting = createCleanCopy(this.image);
    }
    ArrayList<Point> points = (ArrayList)this.colorMap.get(c);
    int rbg = c.getRGB();
    for (Point p : points)
    {
      if ((p.getX() >= 0.0D) && (p.getY() >= 0.0D))
        setting.setRGB((int)p.getX(), (int)p.getY(), rbg);
    }
  }

  public BufferedImage createCleanCopy(BufferedImage in)
  {
    BufferedImage out;
    if (in != null)
      out = new BufferedImage(in.getWidth(), in.getHeight(), 1);
    else
      out = new BufferedImage(1000, 1000, 1);
    resetImage(out);
    return out;
  }

  public boolean isSameColor(Color one, Color two, double compare) {
    double red = Math.abs(one.getRed() - two.getRed());
    double green = Math.abs(one.getGreen() - two.getGreen());
    double blue = Math.abs(one.getBlue() - two.getBlue());
    return (red <= compare) && (green <= compare) && (blue <= compare);
  }

  public void createAllFrames() {
    if (this.image != null)
    {
      createFrame(this.image, "Default Image");
    }
    if (this.image2 != null)
    {
      createFrame(this.image2, "Copied Image " + this.intense + " Alg" + this.alg);
    }
    if (this.copy != null)
    {
      createFrame(this.copy, "Extra Copied Image " + this.intense + " Alg" + this.alg);
    }
    for (Color c : this.differentColors)
    {
      BufferedImage d = (BufferedImage)this.colorsets.get(c);
      if (d != null)
        createFrame((BufferedImage)this.colorsets.get(d), "This color filter is " + c.toString());
    }
  }

  private void createFrame(BufferedImage g, String st) {
    if (this.frames == null)
    {
      this.frames = new ArrayList();
    }
    for (int k = 0; k < this.frames.size(); k++)
    {
      if (((JFrame)this.frames.get(k)).getTitle().equals(st))
      {
        return;
      }
    }
    JFrame frame = new ImageDrawer(g, st);
    frame.setVisible(false);
    frame.setSize(g.getWidth() + 30, g.getHeight() + 30);
    frame.prepareImage(g, g.getWidth(), g.getHeight(), null);
    frame.setVisible(true);
    this.frames.add(frame);
  }

  public void killAllFrames() {
    if (this.frames != null)
      for (int k = 0; k < this.frames.size(); k++)
      {
        JFrame f = (JFrame)this.frames.get(k);
        this.frames.remove(k);
        k--;
        f.setVisible(false);
        System.out.println("disposed window " + k);
      }
  }

  public void resetImage(BufferedImage out) {
    int rbg = new Color(255, 255, 255).getRGB();
    for (int y = 0; y < out.getHeight(); y++)
    {
      for (int x = 0; x < out.getWidth(); x++)
      {
        out.setRGB(x, y, rbg);
      }
    }
  }

  public void addColor(Color c) {
    if (!this.differentColors.contains(c))
    {
      this.differentColors.add(c);
      this.colorMap.put(c, new ArrayList());
    }
  }

  public void revertColors() {
    this.differentColors = new ArrayList();
    addColor(0, 0, 0);
    for (Color c : this.differentColors)
    {
      this.colorMap.put(c, new ArrayList());
    }
  }

  class DirectedPoint
  {
    Point p;
    int direction;

    public DirectedPoint(Point p, int direction)
    {
      this.p = p;
      this.direction = direction;
    }
  }

  class SaveState
  {
    ArrayList<Point> allPoints;
    ArrayList<Point> usedPoints;
    ArrayList<Point> deadPoints;
    int direction;
    Point currentPoint;
    int width;
    int height;
    double intensity;
    Color c;
    boolean firstCall;

    public SaveState(ArrayList<Point> allPoints, ArrayList<Point> usedPoints, ArrayList<Point> deadPoints, Point currentPoint, int width, int height, Color arg9,Color c, boolean firstCall)
    {
      this.allPoints = allPoints;
      this.usedPoints = usedPoints;
      this.deadPoints = deadPoints;
      this.currentPoint = currentPoint;
      this.width = width;
      this.height = height;
      this.intensity = intensity;
      this.c = c;
      this.firstCall = firstCall;
      this.direction = direction;
    }
  }
}