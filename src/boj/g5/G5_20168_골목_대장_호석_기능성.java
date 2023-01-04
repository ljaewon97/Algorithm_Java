package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_20168_골목_대장_호석_기능성 {
	static List<Edge>[] graph;
	static int N, M, A, B, C, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ans = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		
		solve(A, C, 0, 1<<A);
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void solve(int cur, int money, int maxCost, int visited) {
		if(cur == B) {
			ans = Math.min(ans, maxCost);
			return;
		}
		
		for(Edge next: graph[cur]) {
			if((visited & (1<<next.v)) == 0 && money >= next.c) {
				solve(next.v, money-next.c, Math.max(maxCost, next.c), visited | (1<<next.v));
			}
		}
	}
	
	static class Edge {
		int v, c;
		
		public Edge(int v, int c) {
			this.v = v;
			this.c = c;
		}
	}
}
