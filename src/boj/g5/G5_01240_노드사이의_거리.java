package boj.g5;

import java.util.ArrayList;
import java.util.List;

public class G5_01240_노드사이의_거리 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static int[][] parent, dist;
	static int[] depth;
	static boolean[] visited;
	static int N, M, L;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		L = (int) Math.ceil(Math.log(N) / Math.log(2));
		
		graph = new ArrayList[N+1];
		parent = new int[N+1][L+1];
		dist = new int[N+1][L+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int d = in.nextInt();
			
			graph[u].add(new Edge(v, d));
			graph[v].add(new Edge(u, d));
		}
		
		dfs(1, 0);
		
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				dist[j][i] = dist[j][i-1] + dist[parent[j][i-1]][i-1];
			}
		}
		
		while(M-- > 0) {
			int u = in.nextInt();
			int v = in.nextInt();
			
			sb.append(lca(u, v)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur, int dep) {
		visited[cur] = true;
		depth[cur] = dep;
		
		for(Edge next: graph[cur]) {
			if(!visited[next.v]) {
				parent[next.v][0] = cur;
				dist[next.v][0] = next.d;
				dfs(next.v, dep+1);
			}
		}
	}
	
	static int lca(int u, int v) {
		if(depth[v] > depth[u]) return lca(v, u);
		
		int ret = 0;
		
		for(int i = L; i >= 0; i--) {
			if(depth[u] >= depth[v] + (1<<i)) {
				ret += dist[u][i];
				u = parent[u][i];
			}
		}
		
		if(u == v) return ret;
		
		for(int i = L; i >= 0; i--) {
			if(parent[u][i] != parent[v][i]) {
				ret += dist[u][i] + dist[v][i];
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		
		return ret + dist[u][0] + dist[v][0];
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
