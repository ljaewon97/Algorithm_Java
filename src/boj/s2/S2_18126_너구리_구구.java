package boj.s2;

import java.util.ArrayList;
import java.util.List;

public class S2_18126_너구리_구구 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static boolean[] visited;
	static long ans;
	static int N;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		
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
		
		dfs(1, 0);
		
		System.out.println(ans);
	}
	
	static void dfs(int node, long dist) {
		visited[node] = true;
		int cnt = 0;
		
		for(Edge next: graph[node]) {
			if(!visited[next.v]) {
				dfs(next.v, dist+next.d);
				cnt++;
			}
		}
		
		if(cnt == 0) ans = Math.max(ans, dist);
	}
	
	static class Edge {
		int v, d;
		
		public Edge(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
