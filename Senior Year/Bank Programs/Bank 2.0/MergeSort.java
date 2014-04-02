/**
 * MergeSort.java  1/10/2010
 *
 * @author - Jane Doe
 * @author - Period n
 * @author - Id nnnnnnn
 *
 * @author - I received help from ...
 *
 */
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.net.*;
import java.text.*;
import javax.swing.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
import sun.audio.*;

public class MergeSort
{
	private Sorter[] intArray;
	private Sorter[] tempArray;
	int which=0;
	public MergeSort()
	{
	}
    public void merge(int first, int mid, int last)
	{
		int p = first;
		int q = mid+1;
		int k = first;
		while (p <= mid && q <= last)
		{
			if (intArray[p].compareTo(intArray[q],which)<0)
			{
				tempArray[k] = intArray[p];
				p++;
			}
			else
			{
				tempArray[k] = intArray[q];
				q++;
			}
			k++;
		}
		while (p <= mid)
		{
			tempArray[k] = intArray[p];
			p++;
			k++;
		}
		while (q <= last)
		{
			tempArray[k] = intArray[q];
			q++;
			k++;
		}
		for (int h = first; h <= last; h++)
			intArray[h] = tempArray[h];
	}
	public void mergeSort(int first, int last)
	{
		if (first < last)
		{
			int mid = (first + last) / 2;
			mergeSort(first,mid);
			mergeSort(mid+1,last);
			merge(first,mid,last);
		}
	}
	public void sort(int c,ArrayList s)
	{
		intArray=toArray((ArrayList<Sorter>)s);
		which=c;
		mergeSort(0,intArray.length);
		toList((ArrayList<Sorter>)s,intArray);
	}
	public Sorter[] toArray(ArrayList<Sorter> s)
	{
		Sorter[] t=new Sorter[s.size()];
		for(int k=0;k<s.size();k++)
		{
			t[k]=s.get(k);
		}
		return t;
	}
	public void toList(ArrayList<Sorter> s,Sorter[] t)
	{
		for(int k=0;k<s.size();k++)
		{
			s.set(k,t[k]);
		}
	}
}