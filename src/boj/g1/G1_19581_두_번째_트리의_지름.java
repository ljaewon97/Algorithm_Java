package boj.g1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class G1_19581_두_번째_트리의_지름 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static int[] dist;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		graph = new ArrayList[N+1];
		dist = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		
		Arrays.fill(dist, -1);
		dfs(1, 0);
		
		int max = 0, idx = -1;
		
		for(int i = 1; i <= N; i++) {
			if(dist[i] > max) {
				max = dist[i];
				idx = i;
			}
		}
		
		Arrays.fill(dist, -1);
		dfs(idx, 0);
		
		max = 0;
		int idx2 = -1;
		
		for(int i = 1; i <= N; i++) {
			if(dist[i] > max) {
				max = dist[i];
				idx2 = i;
			}
		}
		
		max = 0;
		
		for(int i = 1; i <= N; i++) {
			if(i != idx2) max = Math.max(max, dist[i]);
		}
		
		Arrays.fill(dist, -1);
		dist[idx] = -2;
		dfs(idx2, 0);
		
		for(int i = 1; i <= N; i++) {
			max = Math.max(max, dist[i]);
		}
		
		System.out.println(max);
	}
	
	static void dfs(int cur, int d) {
		dist[cur] = d;
		
		for(Edge next: graph[cur]) {
			if(dist[next.v] == -1) {
				dfs(next.v, d+next.d);
			}
		}
	}
	
	static class Edge {
		int v, d;
		
		public Edge(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
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
