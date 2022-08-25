package boj.g4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class G4_01753_최단경로 {
	static Reader in = new Reader();
	static List<int[]>[] graph;
	static boolean[] visited;
	static int[] dist;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		V = in.nextInt();
		E = in.nextInt();
		
		graph = new ArrayList[V+1];
		visited = new boolean[V+1];
		dist = new int[V+1];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		
		int start = in.nextInt();
		
		for(int i = 0; i < E; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int w = in.nextInt();
			
			graph[a].add(new int[] {b, w});
		}
		
		dijkstra(start);
		
		for(int i = 1; i <= V; i++) {
			sb.append(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		dist[start] = 0;
		pq.add(new int[] {start, dist[start]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cur_node = cur[0];
			int cur_dist = cur[1];
			
			visited[cur_node] = true;
			
			for(int[] next: graph[cur_node]) {
				int next_node = next[0];
				int temp = cur_dist + next[1];
				
				if(!visited[next_node] && temp < dist[next_node]) {
					dist[next_node] = temp;
					pq.add(new int[] {next_node, dist[next_node]});
				}
			}
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
			return n;
		}

		boolean isNumber(byte c) {
			return 47 < c && c < 58;
		}

		byte read() throws Exception {
			if (index == size) {
				size = System.in.read(buffer, index = 0, SIZE);
				if (size < 0)
					buffer[0] = -1;
			}
			return buffer[index++];
		}
	}
}
