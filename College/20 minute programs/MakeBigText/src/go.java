import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class go
{
	public static void main(String args[])
	{
		String[] g = input();
		if (g == null)
		{
			main(null);
		}
		int size = Integer.parseInt(g[0]);
		JFrame frame = new JFrame();
		frame.setVisible(false);
		frame.setSize(size * g[1].length() + 30, size + 1 + 30);
		frame.setVisible(true);
		BufferedImage m = new BufferedImage(size * g[1].length(), size + 1, 1);
		Graphics graph = m.getGraphics();
		graph.setFont(graph.getFont().deriveFont((float) size));
		graph.setColor(Color.white);
		graph.fillRect(0, 0, m.getWidth(), m.getHeight());
		graph.setColor(Color.black);
		graph.drawString(g[1], 1, (int) size);
		int maxX = -1, maxY = -1;
		int minx = m.getWidth(), miny = m.getHeight();
		for (int y = 0; y < m.getHeight(); y++)
		{
			for (int x = 0; x < m.getWidth(); x++)
			{
				int value = m.getRGB(x, y) == -1 ? 0 : 1;
				if (value == 1)
				{
					if (x < minx)
					{
						minx = x;
					}
					if (y < miny)
					{
						miny = y;
					}
					if (x > maxX)
					{
						maxX = x;
					}
					if (y > maxY)
					{
						maxY = y;
					}
				}
				System.out.print(value);
			}
			System.out.println();
		}
		System.out.println();
		minx = Math.max(minx - 1, 0);
		miny = Math.max(miny - 1, 0);
		Rectangle bounds = new Rectangle(minx, miny, Math.min(maxX + 2, m.getWidth()), Math.min(maxY + 2,
				m.getHeight()));
		String[][] values = new String[(int) ((int) bounds.getHeight() - bounds.getY())][(int) ((int) bounds.getWidth() - bounds
				.getX())];
		for (int y = (int) bounds.getY(); y < bounds.getHeight(); y++)
		{
			for (int x = (int) bounds.getX(); x < bounds.getWidth(); x++)
			{
				int value = m.getRGB(x, y) == -1 ? 0 : 1;
				values[(int) (y - bounds.getY())][(int) (x - bounds.getX())] = "" + value;
				System.out.print(value);
			}
			System.out.println();
		}
		System.out.println();

		for (int y = 0; y < values.length; y++)
		{
			for (int x = 0; x < values[y].length; x++)
			{
				System.out.print(values[y][x]);
			}
			System.out.println();
		}

		values = replace(values, g[1]);

		for (int y = 0; y < values.length; y++)
		{
			for (int x = 0; x < values[y].length; x++)
			{
				System.out.print(values[y][x]);
			}
			System.out.println();
		}
		frame.getGraphics().drawImage(m, 30, 30, null);
	}

	public static String[][] replace(String[][] mat, String input)
	{
		input = input.replaceAll(" ", "");
		int count = 0;
		int x2 = 0;
		for (int x = 0; x < mat[0].length; x++)
		{
			for (int y = 0; y < mat.length; y++)
			{
				if (mat[y][x].equals("1") && (count < input.length() || x2 == x))
				{
					if (x2 == x) count -= 1;
					String letter = "" + input.charAt(count);
					if (letter.equals(" "))
					{
						count++;
					}
					System.out.println("x: " + x + " c: " + count + " l: " + input.charAt(count));
					recursive(x, y, mat, "" + input.charAt(count), x);
					count++;
					x2 = x;
				} else if (mat[y][x].equals("0"))
				{
					mat[y][x] = " ";
				}
			}
		}
		return mat;
	}

	public static void recursive(int x, int y, String[][] mat, String letter, int startx)
	{
		mat[y][x] = letter;
		for (int yoff = -1; yoff < 2; yoff++)
		{
			for (int xoff = -1; xoff < 2; xoff++)
			{
				if (!(yoff == 0 && xoff == 0))
				{
					int x2 = x + xoff;
					int y2 = y + yoff;
					if (x2 >= 0 && x2 < mat[0].length && y2 >= 0 && y2 < mat.length && (x2 - startx) < 7)
					{
						if (mat[y2][x2].equals("1"))
						{
							// System.out.println("x: " + x2 + "y: " + y2);
							recursive(x2, y2, mat, letter, startx);
						}
					}
				}
			}
		}
	}
	public static String[] input()
	{
		return new String[] { "12", "I Love you" };
	}

	public static void output(String g)
	{

	}
}
