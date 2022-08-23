package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Prim {
	static int[][] graph;
	static boolean[] visited;
	static int[] dist;
	static int N;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		visited = new boolean[N];
		dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int ans = prim(0);
		
		System.out.println(ans);
	}
	
	static int prim(int node) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
		int ans = 0;
		pq.add(new int[] {0, node});
		dist[node] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			node = cur[1];
			visited[node] = true;
			
			for(int i = 0; i < N; i++) {
				if(!visited[i] && graph[node][i] > 0 && graph[node][i] < dist[i]) {
					dist[i] = graph[node][i];
					pq.add(new int[] {dist[i], i}); 
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			ans += dist[i];
		}
		
		return ans;
	}
	
	static int findNode() {
		int min = Integer.MAX_VALUE;
		int node = -1;
		
		for(int i = 0; i < N; i++) {
			if(!visited[i] && dist[i] < min) {
				min = dist[i];
				node = i;
			}
		}
		
		return node;
	}
}
