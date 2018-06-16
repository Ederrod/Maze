/*************************************
 ** Basic Disjoint set forest        **
 ** Programmed by Olac Fuentes       **
 ** Last modified  April 3, 2017     **
 **	Adjusted by Eder Rodriguez       **
 **************************************/

//import java.io.*;
public class dsf{	

	private int[] S;
	public int comparison = 0; 

	public dsf(int n){ //Initialize disjoint set forest with n elements. Each element is a root
		S = new int[n];
		for(int i=0;i<n;i++)
			S[i] = -1;
	}   

	public dsf(int [] A){ //Initialize disjoint set forest copying its elements from an array A
		S = new int[A.length];
		for(int i=0;i<S.length;i++)
			S[i] = A[i];
	}   

	public int find(int i){ // Returns the root of the set element i belongs to
		this.comparison++; 
		if(S[i]<0)
			return i;
		return find(S[i]);   
	}    

	public int find_c(int i){// Returns the root of the set element i belongs to
		this.comparison++; 
		if(S[i]<0)
			return i;
		return S[i] = find_c(S[i]);   
	}    

	public void union(int i, int j){ // Returns the root of the set element i belongs to
		int ri = find(i);
		int rj = find(j);
		S[rj] = ri;   
	}  
	public void union_with_pathCompression(int i, int j){
		int ri = find_c(i);
		int rj = find_c(j);
		S[rj] = ri;   
	}
	public void union_by_height(int i, int j){
		int ri = find(i); 
		int rj = find(j); 
		if(ri == rj) return; 
		if(S[ri] > S[rj])
			S[ri] = rj; 
		else if(S[ri]< S[rj])
			S[rj] = ri; 
		else{
			S[rj] = ri; 
			S[ri]--; 
		}
	}

	public void union_by_size(int i, int j){
		int ri = find(i);
		int rj = find(j);
		if (ri==rj)return;
		int s;
		if(S[ri]>S[rj]){
			s=S[ri];
			S[ri]=rj;
			S[rj]+=s;
		}
		else{
			s = S[rj];
			S[rj]=ri;
			S[ri]+=s;
		}

	}
	
	public void union_by_size_compression(int i, int j){
		int ri = find_c(i);
		int rj = find_c(j);
		if (ri==rj)return;
		int s;
		if(S[ri]>S[rj]){
			s=S[ri];
			S[ri]=rj;
			S[rj]+=s;
		}
		else{
			s = S[rj];
			S[rj]=ri;
			S[ri]+=s;
		}
	}
	
	public boolean inSameSet(int i, int j){ 
		return find(i) == find(j);   
	}      

	public void print(){
		for(int i=0;i<S.length;i++)  
			System.out.print(S[i]+" ");
		System.out.println();
	}   

	public int numOfSets(){
		int count = 0; 
		for(int i = 0; i < S.length; i++)
			if(S[i] < 0) count++; 
		return count; 
	}
}