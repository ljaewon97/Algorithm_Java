package boj.p5;

import java.util.LinkedList;
import java.util.List;

public class P5_01761_정점들의_거리 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static int[][] parent;
	static int[][] dist;
	static int[] depth;
	static boolean[] visited;
	static int N, M, L;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		L = (int) Math.ceil(Math.log(N) / Math.log(2));
		
		graph = new LinkedList[N+1];
		parent = new int[N+1][L+1];
		dist = new int[N+1][L+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}
		
		dfs(1, 0);
		
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				dist[j][i] = dist[j][i-1] + dist[parent[j][i-1]][i-1];
			}
		}
		
		M = in.nextInt();
		
		while(M-- > 0) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			sb.append(lca(a, b)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur, int dep) {
		visited[cur] = true;
		depth[cur] = dep;
		
		for(Edge next: graph[cur]) {
			if(!visited[next.v]) {
				visited[next.v] = true; 
				parent[next.v][0] = cur;
				dist[next.v][0] = next.d;
				
				dfs(next.v, dep+1);
			}
		}
	}
	
	static int lca(int a, int b) {
		if(depth[a] < depth[b]) return lca(b, a);
		
		int ret = 0;
		
		for(int i = L; i >= 0; i--) {
			if(depth[a] >= depth[b] + (1 << i)) {
				ret += dist[a][i];
				a = parent[a][i];
			}
		}
		
		if(a == b) return ret;
		
		for(int i = L; i >= 0; i--) {
			if(parent[a][i] != parent[b][i]) {
				ret += dist[a][i] + dist[b][i];
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		ret += dist[a][0] + dist[b][0];
		
		return ret;
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
