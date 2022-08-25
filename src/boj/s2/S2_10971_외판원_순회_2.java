package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_10971_외판원_순회_2 {
	static int[][] graph;
	static boolean[] visited;
	static int N, ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		visited = new boolean[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0] = true;
		travel(0, 0, 0);
		
		System.out.println(ans);
	}
	
	static void travel(int nth, int cur, int cost) {
		if(nth == N-1 && graph[cur][0] != 0) {
			ans = Math.min(ans, cost+graph[cur][0]);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && graph[cur][i] != 0) {
				visited[i] = true;
				travel(nth+1, i, cost+graph[cur][i]);
				visited[i] = false;
			}
		}
	}
}
