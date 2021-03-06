//
//

import java.util.*;
import java.io.*;
import java.util.Random;
import java.util.BitSet;
import java.lang.Math;

public class Bingo {
	final static int SIZE = 5;
	public static void main (String[] args) {
		int[][] Card = new int [SIZE][SIZE];
		fillCard(Card);
		printCard(Card);
		pickNum(Card);
		printCard(Card);
	}
	
	//a method that can fill up the Bingo Card 
	public static void fillCard  (int[][] Card) {
		
		String filename;
		try {
			//input bingo.in and put the numbers in the array
			Scanner input = new Scanner(System.in);
			input = new Scanner (new File("bingo.in"));
			
			for(int i = 0; i < Card.length; i++) {
				for(int j = 0; j < Card[0].length; j++) {
					Card[i][j] = input.nextInt();
				}
			}
		}

		catch (IOException e) {
			System.out.println(e.getMessage());		
		}
	}

	//a method that prints out the bingo Card
	public static void printCard(int[][] Card) {
	
		System.out.println(" YOUR BINGO CARD: ");
		System.out.println();	
		for(int r = 0; r < Card.length; r++) {
			//print a "X" when the number was picked
			//print a space in front of the number below 10
			for(int c = 0; c < Card[0].length; c++) {
				if(Card[r][c] < 10){
					if(Card[r][c] == 0)
						System.out.print(" " + "X"       + " | ");
					else
						System.out.print(" " + Card[r][c] + " | ");

				}
				
				else{ 
					if(Card[r][c] == 0)
						System.out.print("X"       + " | ");
					else
						System.out.print(Card[r][c] + " | ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}	
	
	// a class method that can pick random number for the bingo game
	public static void pickNum (int[][] Card){
		int ranNum;
		int win = 0;
		//ArrayList name BinNum that use to record the picked numbers
		ArrayList<Integer> BinNum = new ArrayList<Integer>(5);
		//A Bitset that set the picked numbers to false avoiding repetition
		BitSet Bin = new BitSet(5);
		for(int i = 1; i < 76; i++){
			Bin.set(i);
		}
//		System.out.print(Bin);
//		System.out.println("BINGO NUMBERS PICKED AT RANDOM FROM BIN : ");
		
		while (win == 0){
			ranNum =(int) Math.round((Math.random()*74 + 1));
			if(Bin.get(ranNum) == true){	
				Bin.clear(ranNum);
				BinNum.add(ranNum);
				for(int r = 0; r < Card.length; r++) {
					for(int c = 0; c < Card[0].length; c++) {
						if(ranNum == Card[r][c]){
							Card[r][c] = 0;
						}
					}
				}		
			}
		win = checkWin(Card);
		}
		printWin(Card,win,BinNum);
		
	}
	
	// a class method that checks if the game ended or not
	public static int checkWin (int[][] Card){
		int win = 0;	
		
		//check the sum of each row of the array
		for (int i = 0; i < Card.length; i++){
			int hsum = 0;
			for (int j = 0; j < Card[0].length; j++){
				hsum += Card[i][j];
			}
		//	System.out.println(hsum);
			if(hsum == 0){
			 	win = 1;
			}
		}
		
		//sum for each column
		for (int j = 0; j < Card[0].length; j++){
			int vsum = 0;
			for (int i = 0; i < Card.length; i++){
				vsum += Card[i][j];
			}
			if(vsum == 0){
				win = 2;
			}	
		}
		
		//sum for diagonal from top left to bottom right
		int dsum1 = 0;
		for (int i = 0; i < Card.length; i++){
			dsum1 += Card[i][i];
		}
		if (dsum1 == 0){
			win = 3;
		}

		//sum for diagonal from top right to bottom left
		int dsum2 = 0;
		for (int i = 0; i < Card.length; i++){
			dsum2 += Card[i][Card.length-i-1];
		}
		if (dsum2 == 0){
			win = 3; 
		}
		
		return win;
	}

	//a method that print the Bin number and announed the win
	public static void printWin (int[][] Card,int win,ArrayList<Integer> BinNum){
			String [] winType = {"N/A","HORIZONTAL","VERTICAL","DIAGONAL"};
			System.out.println("BINGO NUMBERS PICKED AT RANDOM FROM BIN : ");
			for(int i = 1; i < BinNum.size(); i++){
				if( i % 10  != 0){
					if(BinNum.get(i) < 10)
						System.out.print(BinNum.get(i) + "    ");
					else
						 System.out.print(BinNum.get(i) + "   ");
				}
				else{
					if(BinNum.get(i) < 10)
						System.out.println(BinNum.get(i) + "    ");
					else
						System.out.println(BinNum.get(i) + "   ");
				}
			}
			System.out.println();
			System.out.println("YOU WIN WITH A " + winType[win] + " BINGO AFTER " + BinNum.size() + " PICKS !");
			
			System.out.println();
	}
}
