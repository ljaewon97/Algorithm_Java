package boj.p4;

import java.util.Arrays;

public class P4_01185_유럽여행 {
	static Reader in = new Reader();
	static Edge[] edges;
	static int[] cost, parent, depth;
	static int N, P;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		P = in.nextInt();
		
		cost = new int[N+1];
		parent = new int[N+1];
		depth = new int[N+1];
		edges = new Edge[P];
		
		for(int i = 1; i <= N; i++) {
			cost[i] = in.nextInt();
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < P; i++) {
			int u = in.nextInt();
			int v = in.nextInt();
			int c = in.nextInt();
			
			edges[i] = new Edge(u, v, c, cost[u], cost[v]);
		}
		
		Arrays.sort(edges);
		
		int edgeCnt = 0;
		long ans = 0;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < P; i++) {
			int u = edges[i].u;
			int v = edges[i].v;
			
			u = find(u);
			v = find(v);
			
			if(u == v) continue;
			
			if(depth[u] >= depth[v]) {
				parent[v] = u;
				depth[u] += depth[v];
			}
			else {
				parent[u] = v;
				depth[v] += depth[u];
			}
			
			edgeCnt++;
			ans += edges[i].visitc;
			min = Math.min(min, edges[i].min);
			
			if(edgeCnt == N-1) break;
		}
		
		System.out.println(ans+min);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static class Edge implements Comparable<Edge> {
		int u, v, visitc, min;
		
		public Edge(int u, int v, int c, int vu, int vv) {
			this.u = u;
			this.v = v;
			this.visitc = 2*c+vu+vv;
			this.min = Math.min(vu, vv);
		}

		@Override
		public int compareTo(Edge o) {
			return this.visitc - o.visitc;
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
