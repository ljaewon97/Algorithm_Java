package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class S1_01697_숨바꼭질 {
	static boolean[] visited;
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(N > K) {
			System.out.println(N - K);
			return;
		}
		
		visited = new boolean[100001];
		
		System.out.println(bfs(N));
	}
	
	static int bfs(int start) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {start, 0});
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int x = cur[0];
			int dist = cur[1];
			
			if(x == K) return dist;
			
			for(int nx: new int[] {x-1, x+1, 2*x}) {
				if(isIn(nx) && !visited[nx]) {
					visited[nx] = true;
					deque.add(new int[] {nx, dist+1});
				}
			}
		}
		
		return 0;
	}
	
	static boolean isIn(int x) {
		return 0 <= x && x <= 100000;
	}
}
