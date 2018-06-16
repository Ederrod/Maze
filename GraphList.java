/** Node to create adjacency list
 ** Programmed by Eder Rodriguez
 **/

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphList {
	private gNode[] Graph; 

	/******Used for Depth-First Search Recursion*******/
	private boolean[] visited; 
	public int[] path;  

	public GraphList(int n){
		Graph = new gNode[n]; 


		visited = new boolean[n]; 
		path = new int[n]; 

		for(int i = 0; i < n; i++)
			Graph[i] = null; 
		for(int i = 0; i < n; i++)
			visited[i] = false; 
		for(int i = 0; i < n; i++)
			path[i] = -1; 
	}

	public void insert(int i, int j){
		Graph[i] = new gNode(j, Graph[i]); 
		Graph[j] = new gNode(i,Graph[j]);
	}

	public void print(){
		for(int i = 0; i < Graph.length; i++){
			System.out.print("G["+i+"]: ");
			for(gNode t = Graph[i]; t != null; t = t.next)
				System.out.print(t.value+ " ");
			System.out.println();
		}
	}

	public int[] Breath_First_Search(int s){
		int n = Graph.length; 
		//Initialize arrays
		boolean[] visited = new boolean[n]; 
		for(int i = 0; i < n; i++)
			visited[i] = false; 

		int[] path = new int[n]; 
		for(int i = 0; i < n; i++)
			path[i] = -1; 
		//make queue
		Queue<Integer> q = new LinkedList<Integer>(); 
		q.add(s); 

		visited[s] = true; 

		while(!q.isEmpty()){
			int u = q.remove(); 
			for(gNode t = Graph[u]; t!= null; t = t.next){
				if(!visited[t.value]){
					visited[t.value] = true; 
					path[t.value] = u; 
					q.add(t.value); 
				}
			}
		}
		return path; 
	}

	public int[] Depth_First_Search_Stack(int s){
		int n = Graph.length; 
		Stack stack = new Stack<Integer>(); 
		boolean[] visited = new boolean[n]; 
		for(int i = 0; i < n; i++)
			visited[i] = false; 

		int[] path = new int[n]; 
		for(int i = 0; i < n; i++)
			path[i] = -1; 

		visited[s] = true; 
		stack.push(s); 

		while(!stack.isEmpty()){
			int u = (int)stack.pop();
			for(gNode t = Graph[u]; t != null; t = t.next){
				if(!visited[t.value]){
					visited[t.value] = true; 
					path[t.value] = u; 
					stack.push(t.value); 
				}
			}
		}
		return path; 
	}

	public void Depth_First_Search(int s){
		visited[s] = true;
		for(gNode t = Graph[s]; t != null; t = t.next){
			if(!visited[t.value]){
				path[t.value] = s; 
				Depth_First_Search(t.value); 
			}
		}
	}
}
