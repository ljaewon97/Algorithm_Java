package swea;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SWEA_D4_3124_최소_스패닝_트리_Prim {
	static Reader in = new Reader();
	static List<int[]>[] graph;
	static long[] dist;
	static boolean[] visited;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 1; t <= T; t++) {
			V = in.nextInt();
			E = in.nextInt();
			
			graph = new LinkedList[V+1];
			dist = new long[V+1];
			visited = new boolean[V+1];
			
			for(int i = 1; i <= V; i++) {
				graph[i] = new LinkedList<>();
				dist[i] = Long.MAX_VALUE;
			}
			
			for(int i = 0; i < E; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				int w = in.nextInt();
				
				graph[a].add(new int[] {b, w});
				graph[b].add(new int[] {a, w});
			}
			
			sb.append(String.format("#%d %d\n", t, prim()));
		}
		
		System.out.println(sb);
	}
	
	static long prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		dist[1] = 0;
		pq.add(new int[] {1, 0});
		long sum = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			visited[node] = true;
			
			for(int[] next: graph[node]) {
				if(!visited[next[0]] && next[1] < dist[next[0]]) {
					dist[next[0]] = next[1];
					pq.add(next);
				}
			}
		}
		
		for(int i = 1; i <= V; i++) {
			sum += dist[i];
		}
		
		return sum;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			boolean neg = false;
			while ((b = read()) <= 32);
			if (b == '-') {
				neg = true;
				b = read();
			}
			do n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}
	}
}
