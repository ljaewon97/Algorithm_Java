package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_1238_Contact {
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		String line;
		int t = 1;
		
		while((line = br.readLine()) != null) {
			st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[101];
			dist = new int[101];
			visited = new boolean[101];
			
			Arrays.fill(dist, -1);
			
			for(int i = 1; i <= 100; i++) {
				graph[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N/2; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a].add(b);
			}
			
			bfs(S);
			
			int n = 0, max = Integer.MIN_VALUE;
			
			for(int i = 100; i >= 1; i--) {
				if(dist[i] > max) {
					max = dist[i];
					n = i;
				}
			}
			
			sb.append(String.format("#%d %d\n", t, n));
			
			t++;
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {start, 0});
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int node = cur[0];
			int d = cur[1];
			
			dist[node] = d;
			
			for(int next: graph[node]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(new int[] {next, d+1});
				}
			}
		}
	}
}
