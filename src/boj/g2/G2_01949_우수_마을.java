package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G2_01949_우수_마을 {
	static List<Integer>[] graph;
	static int[][] dp;
	static int[] pop;
	static boolean[] visited;
	static int N, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N+1];
		dp = new int[N+1][2];
		pop = new int[N+1];
		visited = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			pop[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		dfs(1);
		
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		dp[cur][1] = pop[cur];
		
		for(int adj: graph[cur]) {
			if(!visited[adj]) {
				dfs(adj);
				dp[cur][0] += Math.max(dp[adj][0], dp[adj][1]);
				dp[cur][1] += dp[adj][0];
			}
		}
	}
}
