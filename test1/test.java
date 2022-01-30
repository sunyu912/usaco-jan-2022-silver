import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;

public class test {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		String[] data = sc.nextLine().split(" ");

		int num_pairs = Integer.parseInt(data[0]);
		int[][] pairs = new int[num_pairs][2];
		
		for(int i = 0; i < num_pairs; i++){
			data = sc.nextLine().split(" ");
			pairs[i][0] = Integer.parseInt(data[0]);
			pairs[i][1] = Integer.parseInt(data[1]);
		}

		System.out.println("=====");

		// code start

		// step down
		for(int i = 0; i < num_pairs; i++){
			System.out.println(step_up(
				step_down(pairs[i][0], pairs[i][1])[1],
				pairs[i][1]
			) + step_down(pairs[i][0], pairs[i][1])[0]);
		}

		sc.close();
	}

	public static int[] step_down(int start, int end){
		int steps = 0;

		while(start > end){
			if(start % 2 == 0){
				start /= 2;
				steps ++;
				continue;
			}
			start+=1;
			steps++;
		}

		return new int[]{steps, start};
	}

	public static int step_up(int start, int end){
		int difference = end - start;

		if(difference < 8){
			return difference;
		}

		// generate pattern
		// find the nearest number that is a power of 2 
		int steps_to_8_multiples = 0;
		int closest_8_multiple = difference;
		for(int i = difference; i % 8 == 0; i--){
			if(i % 8 == 0){
				continue;
			}
			closest_8_multiple = i;
			steps_to_8_multiples++;
		}
		
		int steps_to_p2 = 0;
		int closest_p2 = closest_8_multiple;
		for(int i = difference; isPowerOfTwo(i); i-=8){
			if(isPowerOfTwo(i)){
				continue;
			}
			closest_p2 = i;
			steps_to_p2++;
		}
		
		// find out how many steps that power of 2 number has
		int steps_to_8 = 0;
		for(int j = closest_p2; j == 8; j/=2){
			if(j == 8){
				continue;
			}
			steps_to_8++;
		}

		// do some math
		int sum = 6;
		sum += steps_to_8 * 2;
		sum += steps_to_p2 * 2;
		sum += steps_to_8_multiples;
		System.out.println(">>>" +steps_to_8_multiples);

		return sum;
	}

	static boolean isPowerOfTwo(int n)
    {
        return (int)(Math.ceil((Math.log(n) / Math.log(2))))
            == (int)(Math.floor(((Math.log(n) / Math.log(2)))));
    }
}