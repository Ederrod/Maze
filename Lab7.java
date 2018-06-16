/**
 * Course: CS2302
 * Author: Eder Rodriguez 
 * Professor: Dr. Olac Fuentes
 * TA: Abdul Kader
 * Assignment: Lab1 
 * Last Day of Modification: 4/11/2017
 * On the previous lab, the objective was to create a maze using dsf. The objective for this lab was to 
 * make a representation of the maze with an Adjacency List. After performing the graph, using each vertex and edge 
 * to depict the path from the start point and end point using Breath-first search and depth-first search.
 */

import java.util.Random;

public class Lab7 {
	static int N = 25; 
	
	static Random r = new Random(); 
	
	public static void main(String[] args){
		union();  
	}

	private static void union() {
		Maze maze = new Maze(N); 
		dsf S = new dsf(N*N); 
		GraphList graph = new GraphList(N*N);  
		StdDraw.show(0);
		while(S.numOfSets() > 1){
			int cell = r.nextInt((N*N)-1);
			int neighborcell = GetNeighbor(cell);  
			if(!S.inSameSet(cell, neighborcell)){
				S.union_by_size_compression(cell, neighborcell);
				graph.insert(cell, neighborcell);
				maze.remove_wall(getX(cell), getY(cell), getDirection(cell,neighborcell));
			}
		}
		/**Debugging code**/
//		S.print();
		//graph.print();
		/****Draw Maze****/
		maze.draw();
		
		/*****Draw Path*****/
		//maze.path(graph.Breath_First_Search(0));
		
		//maze.path(graph.Depth_First_Search_Stack(0));
		graph.Depth_First_Search(0);
		maze.path(graph.path);
	}

	private static void print(int[] breath_First_Search) {
		int n = breath_First_Search.length;
		System.out.print("Path: "); 
		for(int i = 0; i < n; i++ )
			System.out.print(breath_First_Search[i]+ " "); 
		System.out.println();
	}

	private static char getDirection(int cell, int neighborcell) {
		return (neighborcell != cell+1)? 'U':'R'; 
	}

	private static int getY(int cell) {
		return cell/N; 
	}

	private static int getX(int cell) {
		return cell%N; 
	}

	private static int GetNeighbor(int cell) {
		if(cell%N == (N-1))
			return cell+N; 
		else if(cell>=(N*N)-N)
			return cell+1; 
		int select = r.nextInt(2)+1;
		if(select == 1 && cell+1 != N*N)
			return cell+1; 
		return cell+N; 
	}
}
