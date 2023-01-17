package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class G2_01525_퍼즐 {
	static Set<Integer> visited;
	static int[] pow;
	static int target;
	static int[] d = {-3,-1,1,3};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		pow = new int[9];
		pow[0] = 1;
		
		for(int i = 1; i < 9; i++) {
			pow[i] = 9 * pow[i-1];
		}
		
		visited = new HashSet<>();
		
		int hash = 0;
		int pow = 1;
		
		for(int r = 0; r < 3; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < 3; c++) {
				int x = Integer.parseInt(st.nextToken());
				hash += pow * x;
				pow *= 9;
			}
		}
		
		pow = 1;
		
		for(int i = 1; i <= 8; i++) {
			target += pow * i;
			pow *= 9;
		}
		
		System.out.println(bfs(hash));
	}
	
	static int bfs(int hash) {
		Queue<State> queue = new LinkedList<>();
		queue.add(new State(hash, 0));
		visited.add(hash);
		
		while(!queue.isEmpty()) {
			State cur = queue.poll();
			int chash = cur.hash;
			
			if(cur.hash == target) return cur.t;
			
			int[] arr = new int[9];
			int x = 0;
			
			for(int i = 0; i < 9; i++) {
				arr[i] = cur.hash % 9;
				cur.hash /= 9;
				if(arr[i] == 0) x = i;
			}
			
			for(int i = 0; i < 4; i++) {
				if(x%3 == 0 && i == 1) continue;
				if(x%3 == 2 && i == 2) continue;
				
				int nx = x + d[i];
				
				if(nx < 0 || nx > 8) continue;
				
				int nhash = chash - pow[nx] * arr[nx] + pow[x] * arr[nx];
				
				if(!visited.contains(nhash)) {
					queue.add(new State(nhash, cur.t+1));
					visited.add(nhash);
				}
			}
		}
		
		return -1;
	}
	
	static class State {
		int hash, t;
		
		public State(int hash, int t) {
			this.hash = hash;
			this.t = t;
		}
	}
}
