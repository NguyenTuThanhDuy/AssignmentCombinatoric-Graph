
import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//This class represents an undirected Graph using adjacency list
class SudokuSolver 
{
 public SudokuGenerator g;  
 private int cColored;
 public Writer fileOut;
 private int[] result;
 //Constructor
 SudokuSolver(int v, String name)
 {
     cColored = 0;
     g = new SudokuGenerator(v);
     result = new int[v];
     g.readSudokuGenerator(name, result);
 }
 
 void latinSquare(String output_name)
 {
     solve(0);
     writeSolvePuzzle(output_name);
 }
 
 public boolean writeSolvePuzzle(String output_name)
 {
     try{
                     fileOut = new FileWriter(output_name); 
                    // write code here 
                     int count =0;
                     for(int i=0;i<result.length;i++){
                    	 if(count == 9){
                    		 fileOut.write("\n");
                    		 count =0;
                    	 }
                    	 fileOut.write(result[i]+" ");
                    	 count = count +1;
                     }
                     fileOut.close();
                     return true;
           }
           catch(IOException e)
           {
               System.out.println(e);
               return false;
           }
 }
 
 private LinkedList<Integer> findNumber(int v){
	 LinkedList<Integer> list = g.getListAdj(v);
	 boolean[] number = new boolean[(int) Math.sqrt(g.getV())];
	 for(int i=0;i<list.size();i++){
		 if(result[list.get(i)]!=0){
			 number[result[list.get(i)]-1]=true;
		 }
	 }
	 LinkedList<Integer> listNum = new LinkedList<Integer>();
	 for(int i=0;i<number.length;i++){
		 if(number[i]==false){
			 listNum.add(i+1);
		 }
	 }
	 return listNum;
 }
 
 public boolean solve(int v){
		if (v == g.getV()){
			return true;
		}
		if (result[v] != 0){
			return solve(v + 1);
		}else{
			LinkedList<Integer> remainColor = findNumber(v);
			for (int i = 0 ; i < remainColor.size() ; i++){
				
					result[v] = remainColor.get(i);
					if (solve(v + 1)){
						return true;
					}else{
						result[v] = 0;
					}			
			}
			return false;
		}
	}
 
 public static void main(String args[])
 {
	 SudokuGenerator g = new SudokuGenerator(9);
	 g.nextBoard(Integer.parseInt(args[2]));
	 g.writeGenerator(args[0]);
     SudokuSolver g1 = new SudokuSolver(81,args[0]);
     g1.latinSquare(args[1]);
 }
}
