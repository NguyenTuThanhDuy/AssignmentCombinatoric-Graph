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
  
        // shortestDs[i] will hold the 
        // shortest distance from src to i 
        int[] shortestDs = new int[nV]; 
  
        // added[i] will true if vertex i is 
        // included / in shortest path tree 
        // or shortest distance from src to  
        // i is finalized 
        boolean[] added = new boolean[nV]; 
  
        // Initialize all distances as  
        // INFINITE and added[] as false 
        for (int vIndex = 0; vIndex < nV; vIndex++)   
        { 
            shortestDs[vIndex] = Integer.MAX_VALUE; 
            added[vIndex] = false; 
        } 
          
        // Distance of source vertex from 
        // itself is always 0 
        shortestDs[s] = 0; 
  
        // Parent array to store shortest 
        // path tree 
        int[] parents = new int[nV]; 
  
        // The starting vertex does not  
        // have a parent 
        parents[s] = -1; 
  
        // Find shortest path for all  
        // vertices 
        for (int i = 1; i < nV; i++) 
        { 
  
            // Pick the minimum distance vertex 
            // from the set of vertices not yet 
            // processed. nearestV is  
            // always equal to startNode in  
            // first iteration. 
            int nearestV = -1; 
            int shortestD = Integer.MAX_VALUE; 
            for (int vIndex = 0; 
                     vIndex < nV;  
                     vIndex++) 
            { 
                if (!added[vIndex] && 
                    shortestDs[vIndex] <  
                    shortestD)  
                { 
                    nearestV = vIndex; 
                    shortestD = shortestDs[vIndex]; 
                } 
            } 
  
            // Mark the picked vertex as 
            // processed 
            added[nearestV] = true; 
  
            // Update dist value of the 
            // adjacent vertices of the 
            // picked vertex. 
            for (int vIndex = 0; 
                     vIndex < nV;  
                     vIndex++)  
            { 
                int E_Dist = graph[nearestV][vIndex]; 
                  
                if (E_Dist > 0
                    && ((shortestD + E_Dist) <  
                        shortestDs[vIndex]))  
                { 
                    parents[vIndex] = nearestV; 
                    shortestDs[vIndex] = shortestD +  
                                                       E_Dist; 
                } 
            } 
        } 
  
        printSolution(s, shortestDs, parents); 
    } 
  
    // A utility function to print  
    // the constructed distances 
    // array and shortest paths 
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
  
    // Function to print shortest path 
    // from source to currV 
    // using parents array 
    public void printPath(int currV, 
                                  int[] parents) 
    { 
          
        // Base case : Source node has 
        // been processed 
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