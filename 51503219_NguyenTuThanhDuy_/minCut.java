package tthdt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
public class minCut {
	private int V;
	private Integer[][] matrix;
	
	public minCut(String file_name){
		BufferedReader reader;
		try{
			reader = new BufferedReader(new FileReader(file_name));
			String line = reader.readLine();
			this.V = Integer.parseInt(line);
			this.matrix = new Integer[this.V][this.V];
			for(int i = 0;i<this.V;i++){
				line = reader.readLine();
				String[] e = line.split(" ");
				for(int j = 0 ;j<this.V;j++)
					this.matrix[i][j] = Integer.parseInt(e[j]);
			}
				reader.close();
		}catch(IOException e){
}}
	public boolean bfs(Integer[][] rGraph,int v,int t, int[] parent){
		Queue<Integer> queue = new LinkedList<Integer>();
		boolean[] visited = new boolean[rGraph.length];
		visited[v] = true;
		queue.add(v);
		parent[v] = -1;
		while(queue.size() != 0){
			v = queue.poll();
			for(int i = 0; i< rGraph.length ; i++)
				if(rGraph[v][i] > 0 && visited[i] == false){
					visited[i] = true;
					queue.offer(i);
					parent[i] = v;
				}
			}
		return (visited[t] == true);
		}
	public void dfs(Integer[][] rGraph , int v , boolean[] visited){
		visited[v] = true; 
        for (int i = 0; i < rGraph.length; i++) { 
                if (rGraph[v][i]> 0 && !visited[i]) { 
                    dfs(rGraph, i, visited); 
                } 
			}
	}
	public void print(){
		for(int i =0;i<this.V;i++) {
			for(int j =0;j<this.V;j++) {
				System.out.print(this.matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
	public int findminCut(Integer[][] graph,int s , int t) {
		System.out.println("Source : " +s+ " " + "Terminal : "+t);
		System.out.println("List of Min Cut : ");
		int u , v;
		int maxFlow = 0;
		Integer[][] rGraph = new Integer[graph.length][graph.length];
		for(int i = 0;i<graph.length;i++) {
			for(int j = 0 ; j<graph.length;j++) {
				rGraph[i][j] = graph[i][j];
			}
		}
		int[] parent = new int[graph.length];
		while(bfs(rGraph,s,t,parent) ){
			int pathFlow = Integer.MAX_VALUE;
			for(v = t ;v != s; v = parent[v]) {
				u = parent[v];
				pathFlow = Math.min(pathFlow,rGraph[u][v]);
			}
			for(v = t;v!= s ;v = parent[v]) {
				 u = parent[v];
				 rGraph[u][v] = rGraph[u][v] - pathFlow;
				 rGraph[v][u] = rGraph[v][u] + pathFlow;
			}
			maxFlow += pathFlow;
		}
		boolean[] isVisited = new boolean[graph.length];
		dfs(rGraph,s,isVisited);
		for (int i = 0; i < graph.length; i++) { 
            for (int j = 0; j < graph.length; j++) { 
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) { 
                    System.out.println(i + " - " + j); 
                } 
            } 
        } 
		return maxFlow;
		}
	public static void main(String[] args) {
		minCut mC = new minCut("Graph.txt");
		mC.print();
		System.out.println("The maximum flow :" +mC.findminCut(mC.matrix,0,5));
	}
}