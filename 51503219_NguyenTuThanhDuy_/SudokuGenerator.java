
import java.io.*;
import java.util.*;

// This class represents an undirected Graph using adjacency list
class SudokuGenerator
{
	public int[][] board;
	public Writer fileOut;
	private int operations;
    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List
    
    //Constructor
    SudokuGenerator(int v)
    {
    	board = new int[v][v];
        V = v;
       
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i)
            adj[i] = new LinkedList();
            
          
    }
    /**
	 *Driver method for nextBoard.
	 *@param  difficult the number of blank spaces to insert
	 *@return board, a partially completed 9x9 Graph board
	 */
	public int[][] nextBoard(int numHole)
	{
		board = new int[V][V];
		nextCell(0,0);
		diggingHoles(numHole);
		return board;

	}
	
	/*
	 Recursive method that attempts to place every number in a cell.
	 */
	public boolean nextCell(int x, int y)
	{
		int nextX = x;
		int nextY = y;
		int[] toCheck = {1,2,3,4,5,6,7,8,9};
		Random r = new Random();
		int tmp = 0;
		int current = 0;
		int top = toCheck.length;

   		for(int i=top-1;i>0;i--)
		{
		    current = r.nextInt(i);
		    tmp = toCheck[current];
		    toCheck[current] = toCheck[i];
		    toCheck[i] = tmp;
    	}
		
		for(int i=0;i<toCheck.length;i++)
		{
			if(legalMove(x, y, toCheck[i]))
			{
				board[x][y] = toCheck[i];
				if(x == 8)
				{
					if(y == 8)
						return true;
					else
					{
						nextX = 0;
						nextY = y + 1;
					}
				}
				else
				{
					nextX = x + 1;
				}
				if(nextCell(nextX, nextY))
					return true;
			}
		}
		board[x][y] = 0;
		return false;
	}
	
	/*
	 Given a cell's coordinates and a possible number for that cell,
	 determine if that number can be inserted into said cell legally.
	 */
	private boolean legalMove(int x, int y, int current) {
		for(int i=0;i<9;i++) {
			if(current == board[x][i])
				return false;
		}
		for(int i=0;i<9;i++) {
			if(current == board[i][y])
				return false;
		}
		int cornerX = 0;
		int cornerY = 0;
		if(x > 2)
			if(x > 5)
				cornerX = 6;
			else
				cornerX = 3;
		if(y > 2)
			if(y > 5)
				cornerY = 6;
			else
				cornerY = 3;
		for(int i=cornerX;i<10 && i<cornerX+3;i++)
			for(int j=cornerY;j<10 && j<cornerY+3;j++)
				if(current == board[i][j])
					return false;
		return true;
	}
	
	/*
	 Given a completed board, replace a given amount of cells with 0
	 */
	public void diggingHoles(int holes)
	{
		double remainingSquares = 81;
		double remainingHoles = (double)holes;
		
		for(int i=0;i<9;i++)
			for(int j=0;j<9;j++)
			{
				double holeChance = remainingHoles/remainingSquares;
				if(Math.random() <= holeChance)
				{
					board[i][j] = 0;
					remainingHoles--;
				}
				remainingSquares--;
			}
	}
	public boolean writeGenerator(String output_name) {
		try {
			fileOut = new FileWriter(output_name); 
            // write code here 
             int count =0;
             for(int i=0;i<board.length;i++){
            	 for(int j = 0 ; j < board.length;j++) {
            		 if(count == 9){
            		 fileOut.write("\n");
            		 count =0;
            	 }
            	 fileOut.write(board[i][j]+" ");
            	 count = count +1;
            	 }
             }
             fileOut.close();
             return true;
		}catch(IOException e)
        {
            System.out.println(e);
            return false;
        }
	}
    //Function to add an edge into the SudokuGenerator
    void addEdge(int v,int w)
    {
        adj[v].add(w);
        adj[w].add(v); //Graph is undirected
    }
    public int getV()
    {
    	return V;
    }
    public void printSudokuGenerator()
    {
       
    }
    public LinkedList<Integer> getListAdj(int u)
    {
    	return adj[u];
    }
    public boolean readSudokuGenerator(String input_name, int result[])
    {
    	for(int i=0;i<V;i++){
    		findEdge(i);
    	}
    	try {
			Scanner sc =new Scanner(new File(input_name));
			int i=0;
			while(sc.hasNextInt()){
				result[i]=sc.nextInt();
				i++;
			}
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
          return true;
    }
    
    private int findBlock(int v){
    	int row = v/9;
    	int col = v%9;
    	if(row >= 0 && row <=2){
    		if(col >=0 && col <=2){
    			return 0;
    		}
    		else if(col >=3 && col <=5){
    			return 1;
    		}
    		else{
    			return 2;
    		}
    	}
    	else if(row >= 3 && row <=5){
    		if(col >=0 && col <=2){
    			return 3;
    		}
    		else if(col >=3 && col <=5){
    			return 4;
    		}
    		else{
    			return 5;
    		}
    	}
    	else if(row >= 6 && row <=8){
    		if(col >=0 && col <=2){
    			return 6;
    		}
    		else if(col >=3 && col <=5){
    			return 7;
    		}
    		else{
    			return 8;
    		}
    	}
    	else{
    		return -1;
    	}
    }
    
    private boolean isExists(int v , int u){
    	LinkedList<Integer> list = getListAdj(v);
    	for(int i=0;i < list.size() ; i++){
    		if(list.get(i)==u){
    			return true;
    		}
    	}
    	return false;
    }
    
    private LinkedList<Integer> sameBlock(int v) {
    	LinkedList<Integer> list = new LinkedList<Integer>();
    	for(int i=0;i<V;i++){
    		int a = findBlock(v);
    		int b = findBlock(i);
    		if(v!=i && a==b && isExists(v,i)== false){
    			list.add(i);
    		}
    	}
    	return list;
    }
    
    private void addBlock(int v){
    	LinkedList<Integer> list = sameBlock(v);
    	for(int i=0;i<list.size();i++){
    			addEdge(v, list.get(i));
    	}
    }
    
    private void findEdge(int v){
    	// add v same row
    	for(int i= v + 1;(i % Math.sqrt(V)) != 0 ; i++){
    		addEdge(v,i);
    	}
    	
    	// add v same column
    	for(int i =(int) (v + Math.sqrt(V));i < V ; i = (int) (i+Math.sqrt(V))){
    		addEdge(v,i);
    	}
    	
    	//add v same block
    	addBlock(v);
    }
    
} 
