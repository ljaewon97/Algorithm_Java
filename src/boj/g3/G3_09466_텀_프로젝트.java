package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_09466_텀_프로젝트 {
	static int[] graph;
	static boolean[] visited, finished;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			graph = new int[N+1];
			visited = new boolean[N+1];
			finished = new boolean[N+1];
			cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= N; i++) {
				dfs(i);
			}
			
			sb.append(N-cnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int node) {
		if(visited[node]) return;
		
		visited[node] = true;
		int next = graph[node];
		
		if(!visited[next]) {
			dfs(next);
		}
		else if(!finished[next]) {
			cnt++;
			for(int i = next; i != node; i = graph[i]) {
				cnt++;
			}
		}
		
		finished[node] = true;
	}
}