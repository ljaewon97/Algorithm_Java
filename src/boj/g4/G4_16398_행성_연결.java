package boj.g4;

import java.util.Arrays;
import java.util.PriorityQueue;

public class G4_16398_행성_연결 {
	static Reader in = new Reader();
	static int[][] graph;
	static boolean[] visited;
	static int[] dist;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		graph = new int[N][N];
		visited = new boolean[N];
		dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				graph[i][j] = in.nextInt();
			}
		}
		
		System.out.println(prim());
	}
	
	static long prim() {
		long ans = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
		pq.add(new int[] {0, 0});
		dist[0] = 0;
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int node = cur[0];
			
			visited[node] = true;
			
			for(int next = 0; next < N; next++) {
				if(!visited[next] && graph[node][next] != 0 && graph[node][next] < dist[next]) {
					dist[next] = graph[node][next];
					pq.add(new int[] {next, dist[next]});
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			ans += dist[i];
		}
		
		return ans;
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
