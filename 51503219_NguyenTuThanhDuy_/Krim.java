import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Krim {
	private int V;
	private Integer[][] matrix;
	
	public Krim(String file_name){
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
	//find min v which is unvisited in MST
	public int findminV(int V[], boolean mstSet[]){
		//initialize min value 
		int minV = Integer.MAX_VALUE;
		int minIndex = -1;
		for(int v = 0; v < this.V ; v++){
			if(mstSet[v] == false && V[v] < minV){
				minV = V[v];
				minIndex = v;
			}
		}
		return minIndex;
	}
	//hàm in MST
	public void printMST(Integer graph[][], int n , int parent[]){
		System.out.println("Edge \t Weight");
		for(int i = 1; i < this.V ; i++){
			System.out.println(parent[i]+" - "+i+"\t"+graph[i][parent[i]]);
		}
	}
	public void primMST(Integer[][] graph){
		//store parent v
		int[] parent = new int[this.V];
		//store min weight edge which is picked from findminV
		int[] minWedge = new int[this.V];
		//store unvisited v in MST 
		boolean mstSet[] = new boolean[this.V];
		//initialize value
		for(int i = 0 ;i<this.V;i++){
			minWedge[i] = Integer.MAX_VALUE;
			mstSet[i] = false;
		}
		minWedge[0] = 0;
		parent[0] = -1;
		for(int i = 0;i< this.V-1;i++){
			int u = findminV(minWedge,mstSet);
			mstSet[u] = true;
			for(int v = 0; v<this.V;v++){
				if(graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < minWedge[v]){
					parent[v] = u;
					minWedge[v] = graph[u][v];
				}
			}
		}
		printMST(graph,this.V,parent);
	}
	public static void main(String[] args){
		Krim k = new Krim("Graph.txt");
		k.primMST(k.matrix);
	}
}