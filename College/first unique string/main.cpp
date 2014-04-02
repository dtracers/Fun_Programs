/*
 * main.cpp
 *
 *  Created on: Mar 7, 2012
 *      Author: gigemjt
 */




/*
 * stringblah.cpp
 *
 *  Created on: Mar 7, 2012
 *      Author: gigemjt
 */

#include<map>
#include<queue>
#include<iostream>
#include<string>
using namespace std;
int findFirstUnique(string g);
int main()
{
	string in="";
	cin>>in;
	int k=findFirstUnique(in);
	cout<<k<<endl;
	return 0;
}

int findFirstUnique(string g)
{
	map<char,int> occurences;
	queue<char> firstletter;
	queue<int> firstIndex;
	for(int k=0;k<g.length();k++)
	{
		char c=g[k];
		if(occurences[c]==0)
		{
			occurences[c]=1;
			firstletter.push(c);
			firstIndex.push(k);
		}
		else if(occurences[c]==1)
		{
			occurences[c]++;
			if(c==firstletter.front())
			{
				while(!firstletter.empty()&&occurences[firstletter.front()]>1)
				{
					firstletter.pop();
					firstIndex.pop();
				}
			}
		}
		else if(occurences[c]>1)
		{
			occurences[c]++;
		}
	}
	if(firstIndex.empty())
	{
		return -1;
	}
	return firstIndex.front();
}
