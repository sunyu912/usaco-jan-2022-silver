import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

import java.io.*;

public class test {
	public static void main(String[] args) throws IOException {
		/* 
			7
			4 3 1 2 5 6 7
		*/

		Scanner sc = new Scanner(System.in);
		int num_cows = Integer.parseInt(sc.nextLine().split(" ")[0]);
		String[] data = sc.nextLine().split(" ");
		int[] cows = new int[num_cows];

		for(int i = 0; i < num_cows; i++){
			cows[i] = Integer.parseInt(data[i]);
		}

		int sum = 0;
		int l = 0;
		int r = 1;

		// find largest right bound
		for(int i = 0; i < num_cows-1; i++){
			int current_min = Math.min(cows[i], cows[i+1]);
			int current_maxmin = Math.min(cows[i], cows[i+1]);
			for(int j = i+1; j < num_cows; j++){
				if(current_min > cows[j]){
					current_min = cows[j];
					// System.out.println("Skipped ("+(i+1)+","+(j+1)+")");
					continue;
				}
				if(current_maxmin > cows[j]){
					// System.out.println("skipped ("+(i+1)+","+(j+1)+")");
					continue;
				} else {
					// System.out.println("("+(i+1)+","+(j+1)+")");
					sum += j-i+1;
					current_maxmin = cows[j];
					if(cows[i] < cows[j]){
						break;
					}
				}
				if(cows[i] < cows[j]){
					sum += j-i+1;
					break;
				}
			}
		}

		System.out.println(sum);
		sc.close();
	}
}