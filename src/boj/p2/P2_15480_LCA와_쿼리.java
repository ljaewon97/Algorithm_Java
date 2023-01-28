package boj.p2;

import java.util.ArrayList;
import java.util.List;

public class P2_15480_LCA와_쿼리 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static int[][] parent;
	static int[] depth;
	static boolean[] visited;
	static int N, M, L;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		L = (int) Math.ceil(Math.log(N) / Math.log(2));
		
		graph = new ArrayList[N+1];
		parent = new int[N+1][L+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		dfs(1, 0);
		
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
			}
		}
		
		M = in.nextInt();
		
		while(M-- > 0) {
			int r = in.nextInt();
			int u = in.nextInt();
			int v = in.nextInt();
			
			int p1 = lca(r, u);
			int p2 = lca(r, v);
			int p3 = lca(u, v);
			
			int ans = depth[p1] >= depth[p3] ? p1 : p3;
			ans = depth[ans] >= depth[p2] ? ans : p2;
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur, int dep) {
		visited[cur] = true;
		depth[cur] = dep;
		
		for(int next: graph[cur]) {
			if(!visited[next]) {
				parent[next][0] = cur;
				dfs(next, dep+1);
			}
		}
	}
	
	static int lca(int u, int v) {
		if(depth[v] > depth[u]) return lca(v, u);
		
		for(int i = L; i >= 0; i--) {
			if(depth[u] >= depth[v] + (1<<i)) {
				u = parent[u][i];
			}
		}
		
		if(u == v) return u;
		
		for(int i = L; i >= 0; i--) {
			if(parent[u][i] != parent[v][i]) {
				u = parent[u][i];
				v = parent[v][i];
			}
		}
		
		return parent[u][0];
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
