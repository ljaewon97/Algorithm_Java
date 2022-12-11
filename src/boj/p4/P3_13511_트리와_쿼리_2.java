package boj.p4;

import java.util.ArrayList;
import java.util.List;

public class P3_13511_트리와_쿼리_2 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static int[][] parent;
	static long[][] cost;
	static int[] depth;
	static boolean[] visited;
	static int N, M, L;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		L = (int) Math.ceil(Math.log(N) / Math.log(2));
		
		graph = new ArrayList[N+1];
		parent = new int[N+1][L+1];
		cost = new long[N+1][L+1];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int w = in.nextInt();
			
			graph[u].add(new Edge(v, w));
			graph[v].add(new Edge(u, w));
		}
		
		dfs(1, 0);
		
		for(int i = 1; i <= L; i++) {
			for(int j = 1; j <= N; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				cost[j][i] = cost[j][i-1] + cost[parent[j][i-1]][i-1];
			}
		}
		
		M = in.nextInt();
		
		while(M-- > 0) {
			int type = in.nextInt();
			int u = in.nextInt();
			int v = in.nextInt();
			
			if(type == 1) {
				sb.append(lcaCost(u, v)).append("\n");
			}
			else {
				int k = in.nextInt() - 1;
				sb.append(lcaKth(u, v, k)).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur, int dep) {
		visited[cur] = true;
		depth[cur] = dep;
		
		for(Edge next: graph[cur]) {
			if(!visited[next.v]) {
				parent[next.v][0] = cur;
				cost[next.v][0] = next.d;
				dfs(next.v, dep+1);
			}
		}
	}
	
	static long lcaCost(int a, int b) {
		long ret = 0;
		
		if(depth[a] < depth[b]) return lcaCost(b, a);
		
		if(depth[a] != depth[b]) {
			for(int i = L; i >= 0; i--) {
				if(depth[a] >= depth[b] + (1 << i)) {
					ret += cost[a][i];
					a = parent[a][i];
				}
			}
		}
		
		if(a == b) return ret;
		
		for(int i = L; i >= 0; i--) {
			if(parent[a][i] != parent[b][i]) {
				ret += cost[a][i] + cost[b][i];
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		ret += cost[a][0] + cost[b][0];
		
		return ret;
	}
	
	static int lcaKth(int u, int v, int k) {
		if(u == v) return u;
		
		int uo = u, vo = v;
		int ud = 0, vd = 0;
		
		if(depth[u] > depth[v]) {
			for(int i = L; i >= 0; i--) {
				if(depth[u] >= depth[v] + (1 << i)) {
					ud += 1 << i;
					u = parent[u][i];
				}
			}
		}
		else if(depth[u] < depth[v]) {
			for(int i = L; i >= 0; i--) {
				if(depth[v] >= depth[u] + (1 << i)) {
					vd += 1 << i;
					v = parent[v][i];
				}
			}
		}
		
		if(u != v) {
			for(int i = L; i >= 0; i--) {
				if(parent[u][i] != parent[v][i]) {
					ud += 1 << i;
					vd += 1 << i;
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			
			ud++;
			vd++;
		}
		
		u = uo;
		v = vo;
		
		if(k <= ud) {
			for(int i = L; i >= 0; i--) {
				if(k >= 1 << i) {
					k -= 1 << i;
					u = parent[u][i];
				}
			}
			
			return u;
		}
		else {
			k = vd - (k - ud);
			
			for(int i = L; i >= 0; i--) {
				if(k >= 1 << i) {
					k -= 1 << i;
					v = parent[v][i];
				}
			}
			
			return v;
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
