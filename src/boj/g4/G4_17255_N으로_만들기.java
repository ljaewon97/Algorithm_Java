package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class G4_17255_N으로_만들기 {
	static Set<String> set = new HashSet<>();
	static String N;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = br.readLine();
		visited = new boolean[N.length()];
		
		recur(0, -1, "");

		System.out.println(set.size());
	}
	
	static void recur(int nth, int head, String order) {
		if(nth == N.length()) {
			set.add(order);
			return;
		}
		
		if(nth == 0) {
			for(int i = 0; i < N.length(); i++) {
				StringBuilder temp = new StringBuilder(order);
				temp.append(N.charAt(i));
				visited[i] = true;
				recur(1, i, temp.toString());
				visited[i] = false;
			}
		}
		else {
			int[] cand = {head-1, head+nth};
			for(int i: cand) {
				if(i < 0 || i >= N.length() || visited[i]) continue;
				
				StringBuilder temp = new StringBuilder(order);
				if(i == head-1) {
					StringBuilder temp2 = new StringBuilder();
					temp2.append(N.charAt(i));
					temp2.append(order.substring(order.length()-nth));
					temp.append(temp2);
				}
				else {
					StringBuilder temp2 = new StringBuilder();
					temp2.append(order.substring(order.length()-nth));
					temp2.append(N.charAt(i));
					temp.append(temp2);
				}
				
				visited[i] = true;
				recur(nth+1, i < head ? head-1 : head, temp.toString());
				visited[i] = false;
			}
		}
	}
}
