package boj.p4;

import java.util.ArrayList;
import java.util.List;

public class P4_03176_도로_네트워크 {
	static Reader in = new Reader();
	static List<Road>[] roads;
	static int[][][] parent;
	static int[] depth, pow;
	static boolean[] visited;
	static int N, K, min, max;
	static final int LOG = 17;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		pow = new int[LOG+1];
		pow[0] = 1;
		
		for(int i = 1; i <= LOG; i++) {
			pow[i] = 2 * pow[i-1];
		}
		
		N = in.nextInt();
		
		roads = new ArrayList[N+1];
		parent = new int[N+1][LOG+1][3];
		depth = new int[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			roads[i] = new ArrayList<>();
			
			for(int j = 0; j <= LOG; j++) {
				parent[i][j][1] = 1000000;
				parent[i][j][2] = 1;
			}
		}
		
		for(int i = 0; i < N-1; i++) {
			int A = in.nextInt();
			int B = in.nextInt();
			int C = in.nextInt();
			
			roads[A].add(new Road(B, C));
			roads[B].add(new Road(A, C));
		}
		
		// BFS -> DFS
		dfs(1, 0);
		
		for(int i = 1; i <= LOG; i++) {
			for(int j = 1; j <= N; j++) {
				if(depth[j] < pow[i]) continue;
				
				parent[j][i][0] = parent[parent[j][i-1][0]][i-1][0];
				parent[j][i][1] = Math.min(parent[j][i-1][1], parent[parent[j][i-1][0]][i-1][1]);
				parent[j][i][2] = Math.max(parent[j][i-1][2], parent[parent[j][i-1][0]][i-1][2]);
			}
		}
		
		K = in.nextInt();
		
		while(K-- > 0) {
			int D = in.nextInt();
			int E = in.nextInt();
			
			min = 1000000;
			max = 1;
			
			lca(D, E);
			
			sb.append(String.format("%d %d\n", min, max));
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur, int d) {
		visited[cur] = true;
		depth[cur] = d;
		
		for(Road next: roads[cur]) {
			if(!visited[next.to]) {
				parent[next.to][0][0] = cur;
				parent[next.to][0][1] = next.len;
				parent[next.to][0][2] = next.len;
				dfs(next.to, d+1);
			}
		}
	}
	
	static void lca(int a, int b) {
		
		if(depth[b] > depth[a]) {
			int swap = a;
			a = b;
			b = swap;
		}
		
		if(depth[a] != depth[b]) {
			for(int i = LOG; i >= 0; i--) {
				if(depth[a] >= depth[b] + pow[i]) {
					min = Math.min(min, parent[a][i][1]);
					max = Math.max(max, parent[a][i][2]);
					a = parent[a][i][0];
				}
			}
		}
		
		if(a == b) return;
		
		for(int i = LOG; i >= 0; i--) {
			if(parent[a][i][0] != parent[b][i][0]) {
				min = Math.min(min, parent[a][i][1]);
				max = Math.max(max, parent[a][i][2]);
				a = parent[a][i][0];
				
				min = Math.min(min, parent[b][i][1]);
				max = Math.max(max, parent[b][i][2]);
				b = parent[b][i][0];
			}
		}
		
		min = Math.min(min, parent[a][0][1]);
		max = Math.max(max, parent[a][0][2]);
		min = Math.min(min, parent[b][0][1]);
		max = Math.max(max, parent[b][0][2]);
	}
	
	static class Road {
		int to, len;
		
		public Road(int to, int len) {
			this.to = to;
			this.len = len;
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