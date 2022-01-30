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

		int cows = Integer.parseInt(data[0]);
		int cookies = Integer.parseInt(data[1]);
		
		TreeMap<Integer, ArrayList<Integer>> primary = new TreeMap<Integer, ArrayList<Integer>>();
		TreeMap<Integer, Integer> count = new TreeMap<Integer, Integer>();
		TreeMap<Integer, ArrayList<Integer>> secondary = new TreeMap<Integer, ArrayList<Integer>>();

		TreeMap<Integer, Integer> bridge = new TreeMap<Integer, Integer>();
		TreeMap<Integer, Integer> bridge_primary = new TreeMap<Integer, Integer>();

		for(int i = 1; i < cookies; i++){
			primary.put(i, new ArrayList<Integer>());
			count.put(i, 1);
			secondary.put(i, new ArrayList<Integer>());
		}
		
		for(int i = 1; i <= cows; i++){
			data = sc.nextLine().split(" ");
			primary.get(Integer.parseInt(data[0])).add(i);
			secondary.get(Integer.parseInt(data[1])).add(i);
			bridge.put(i, Integer.parseInt(data[1]));
			bridge_primary.put(i, Integer.parseInt(data[0]));
		}

		// start parsing
		int hungry = 0;
		ArrayList<Integer> order = new ArrayList<Integer>();
		for(int i = 0; i < cookies; i++){
			if(primary.get(i) == null) continue;

			ArrayList<Integer> current_primaries = primary.get(i);
			
			TreeMap<Integer, ArrayList<Integer>> temp = new TreeMap<Integer, ArrayList<Integer>>();

			for(int j = 0; j < current_primaries.size(); j++){ // check which one has the most amount of secondaries, and remove that cow.
				int size = secondary.get(bridge.get(current_primaries.get(j))).size();
				if(temp.get(size) == null){
					ArrayList<Integer> temp_cow = new ArrayList<Integer>();
					temp_cow.add(current_primaries.get(j));
					temp.put(size, temp_cow);
				} else {
					temp.get(size).add(current_primaries.get(j));
				}
			}

			// System.out.println(primary.get(i) + " | " + temp);

			ArrayList<Integer> list = new ArrayList(temp.keySet());
			Collections.sort(list, Collections.reverseOrder());

			for(int j : list){
				for(int k : temp.get(j)){
					// remove that cow's primary cereal if it exists
					if(count.get(bridge_primary.get(k)) != 0){
						count.put(bridge_primary.get(k), 0);
						order.add(k);
					} else if (count.get(bridge.get(k)) != 0){
						count.put(bridge.get(k), 0);
						order.add(k);
					} else {
						hungry++;
						order.add(k);
					}
				}
			}
		}

		System.out.println(hungry);

		for(int i : order){
			System.out.println(order);
		}

		sc.close();
	}
}