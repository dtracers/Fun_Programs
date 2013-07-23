import java.io.*;
import java.text.*;
import java.util.*;
import java.lang.*;
import static java.lang.System.*;
import static java.lang.Math.*;
import static java.lang.String.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.util.Arrays.*;

public class mazedecoder
{
    public static void main(String[] args) throws IOException
    {
    	HashMap<String,String> key = new HashMap<String,String>();
    	key.put("|","X");
    	key.put("_","X");
    	key.put(" "," ");
    	key.put("S","S");
    	key.put("E","E");
    	//Scanner s = new Scanner(in);
    	String fileName = "input6";
    	int rows = 401;
	    Scanner s = new Scanner(new File(fileName+".txt"));
	    Scanner temp2 = new Scanner(new File(fileName+".txt"));
	    String[][]maze=new String[rows][temp2.nextLine().length()];
	    //BufferedReader f = new BufferedReader(new FileReader("mazedecoder.dat"));
	    //int sets = s.nextInt();
	    //s.nextLine();
	    //int sets = parseInt(f.readLine());
	    //DecimalFormat form = new DecimalFormat("$00.00");

	    for(int q = 0; q<rows; q++)
	    {
			String str = s.nextLine();
			for(int k = 0;k<str.length();k++)
			{
				maze[q][k]=key.get(""+str.charAt(k));
			}
	    }
	    File f = new File(fileName+"2.txt");
		if(f!=null)
			f.delete();
		f.createNewFile();
		PrintStream ps = new PrintStream(f);
		ps.println(maze.length+" "+maze[0].length);
	    for(int k = 0;k<maze.length;k++)
	    {
	    	for(int l = 0;l<maze[l].length;l++)
	    	{
	    		ps.print(maze[k][l]);
	    	}
	    	ps.println();
	    }
	    ps.close();
    }
}