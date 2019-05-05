package tthdt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
public class Dijkstra {
	private int V;
	private Integer[][] matrix;
	
	public Dijkstra(String file_name){
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
	public void print(){
		for(int i =0;i<this.V;i++) {
			for(int j =0;j<this.V;j++) {
				System.out.print(this.matrix[i][j]+" ");
			}
			System.out.println();
		}
	}
    public void shortestPath(Integer[][] graph, 
                                        int s) 
    { 
        int nV = graph.length; 
        int[] shortestDs = new int[nV]; 
        boolean[] visited = new boolean[nV]; 
        for (int vIndex = 0; vIndex < nV; vIndex++)   
        { 
            shortestDs[vIndex] = Integer.MAX_VALUE; 
            visited[vIndex] = false; 
        } 
        shortestDs[s] = 0; 
        int[] parents = new int[nV]; 
        parents[s] = -1; 
        for (int i = 1; i < nV; i++) 
        { 
            int nearestV = -1; 
            int shortestD = Integer.MAX_VALUE; 
            for (int vIndex = 0; vIndex < nV;vIndex++) 
            { 
                if (!visited[vIndex] && shortestDs[vIndex] < shortestD)  
                { 
                    nearestV = vIndex; 
                    shortestD = shortestDs[vIndex]; 
                } 
            }  
            visited[nearestV] = true; 
            for (int vIndex = 0;vIndex < nV;vIndex++)  
            { 
                int E_Dist = graph[nearestV][vIndex]; 
                if (E_Dist > 0 && ((shortestD + E_Dist) < shortestDs[vIndex]))  
                { 
                    parents[vIndex] = nearestV; 
                    shortestDs[vIndex] = shortestD +E_Dist; 
                } 
            } 
        } 
        printSolution(s, shortestDs, parents); 
    } 
    public void printSolution(int s, 
                                      int[] distances, 
                                      int[] parents) 
    { 
        int nV = distances.length; 
        System.out.print("Vertex\t Distance\tPath"); 
          
        for (int vIndex = 0;  
                 vIndex < nV;  
                 vIndex++)  
        { 
            if (vIndex != s)  
            { 
                System.out.print("\n" + s + " -> "); 
                System.out.print(vIndex + " \t "); 
                System.out.print(distances[vIndex] + "\t\t"); 
                printPath(vIndex, parents); 
            } 
        } 
    } 

    public void printPath(int currV, 
                                  int[] parents) 
    { 
        if (currV == -1) 
        { 
            return; 
        } 
        printPath(parents[currV], parents); 
        System.out.print(currV + " "); 
    }
	public static void main(String[] args){
		Dijkstra d = new Dijkstra("Graph.txt");
		d.print();
		d.shortestPath(d.matrix,0);
	}
}