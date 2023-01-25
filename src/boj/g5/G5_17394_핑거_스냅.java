package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_17394_핑거_스냅 {
	static boolean[] sieve;
	static int N, A, B;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		sieve = new boolean[100001];
		
		for(int i = 2; i <= 317; i++) {
			for(int j = 2*i; j <= 100000; j += i) {
				sieve[j] = true;
			}
		}
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			boolean flag = false;
			
			for(int i = A; i <= B; i++) {
				if(!sieve[i]) {
					flag = true;
					break;
				}
			}
			
			if(!flag) {
				sb.append("-1\n");
				continue;
			}
			
			if(A <= N && N <= B && !sieve[N]) {
				sb.append("0\n");
				continue;
			}
			
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int bfs() {
		Queue<State> queue = new LinkedList<>();
		boolean[] visited = new boolean[3000001];
		queue.add(new State(N, 0));
		visited[N] = true;
		
		while(!queue.isEmpty()) {
			State cur = queue.poll();
			
			if(A <= cur.p && cur.p <= B && !sieve[cur.p]) return cur.t;
			
			int np = cur.p / 2;
			if(valid(np) && !visited[np]) {
				queue.add(new State(np, cur.t+1));
				visited[np] = true;
			}
			
			np = cur.p / 3;
			if(valid(np) && !visited[np]) {
				queue.add(new State(np, cur.t+1));
				visited[np] = true;
			}
			
			np = cur.p + 1;
			if(valid(np) && !visited[np]) {
				queue.add(new State(np, cur.t+1));
				visited[np] = true;
			}
			
			np = cur.p - 1;
			if(valid(np) && !visited[np]) {
				queue.add(new State(np, cur.t+1));
				visited[np] = true;
			}
		}
		
		return -1;
	}
	
	static boolean valid(int pop) {
		return 0 <= pop && pop <= 3000001;
	}
	
	static class State {
		int p, t;
		
		public State(int p, int t) {
			this.p = p;
			this.t = t;
		}
	}
}
