package Practise;

// this is the algorithm used to perform BFS and store the number of shortest path from two nodes

import java.io.*;
import java.util.*;

//This class represents can be used for undirected or directed graph using adjacency list
//this algorithm is an extension of this simple BFS algorithm from this website
//https://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/ 

public class BFS_Count_Shortest_Path
{
private int V;   // No. of vertices
int number;
private LinkedList<Integer> adj[]; //Adjacency Lists
private LinkedList<Integer> preceding_numLayer[];//used to store num of layer and how many preceding layer nodes connected to this node.

// Constructor
BFS_Count_Shortest_Path(int v)
{
   V = v;
   adj = new LinkedList[v];
   preceding_numLayer=new LinkedList[V];
   for (int i=0; i<v; ++i)
       {adj[i] = new LinkedList();
       preceding_numLayer[i] = new LinkedList();// initialize lists used for storing preceding nodes and layer num
       }	   
}

// Function to add an edge into the graph
void addEdge(int v,int w)
{
   adj[v].add(w);//right now for undirected graph, so when applying for directed graph, just deleting the second one
   adj[w].add(v);
}


void BFS(int s,int d)//start node s and end node d.
{
	int b=s;

   // Mark all the vertices as not visited(By default, they are false)
   boolean visited[] = new boolean[V];
  
   // Create a queue for BFS
   LinkedList<Integer> queue = new LinkedList<Integer>();

   queue.add(s);// add the start node into the queue
   preceding_numLayer[s].add(0,0);//label the start node as layer 0
   while (queue.size() != 0)
   {
       // Dequeue a vertex from queue and print it
       s = queue.poll();
       visited[s]=true;//label this node as visited so later this node will not be visited again.
       
       System.out.print(s+" ");//trace the process of the queue

       // Get all adjacent vertices of the dequeued vertex s
       // If a adjacent has not been visited and not already in the queue, then mark it
       // visited and enqueue it
       Iterator<Integer> i = adj[s].listIterator();
       while (i.hasNext())
       {
           int n = i.next();
           if (!visited[n]&&!queue.contains(n))//check whether this node has been added into the queque, if added, it shouldnot be added again.
           {  
               queue.add(n);
               preceding_numLayer[n].add(0,preceding_numLayer[s].get(0)+1);//label which layer this node should be, its value should be one greater than the layer number of its preceding nodes.
           }
           if(preceding_numLayer[n].get(0)>preceding_numLayer[s].get(0))//when the layer num of n is greater than the layer num of s, node s is above n
        	   preceding_numLayer[n].add(s);//add the preceding node,recording which preceding node is connected to it and there could be multiple preceding nodes
       }
   }
   
   System.out.println("\nnum of shortest path from "+b+" to "+d+" is:"+countNum(preceding_numLayer[d]));
}//end of BFS

public int countNum(LinkedList<Integer> element){//using recursive to count number of shortest path, stop condition is layer number equals to 2
	for(int i=1;i<element.size();i++){
		if(element.get(0)==2)
			return number=number+(element.size()-1);
		else
			countNum(preceding_numLayer[element.get(i)]);
	}//end of for	
	return number;
}//end of recursive counting method

// Driver method to test
public static void main(String args[])
{
   BFS_Count_Shortest_Path g = new BFS_Count_Shortest_Path(7);

   g.addEdge(0, 1);
   g.addEdge(0, 2);
   g.addEdge(1, 2);
   g.addEdge(0, 3);
   g.addEdge(3, 5);
   g.addEdge(5, 4);
   g.addEdge(1, 4);
   g.addEdge(2, 4);
   g.addEdge(6, 4);
   g.addEdge(5, 6);
   g.addEdge(2, 3);
   
   System.out.println("Here is the process of the BFS ");

   g.BFS(3,4);
}
}
//This code is contributed by Aakash Hasija
