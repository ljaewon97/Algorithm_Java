package boj.g4;

import java.util.Arrays;

public class G4_01922_네트워크_연결_2 {
	static Reader in = new Reader();
	static int[] parent, depth;
	static Edge[] edges;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		parent = new int[N+1];
		depth = new int[N+1];
		edges = new Edge[M];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
		}
		
		Arrays.sort(edges);
		
		int edge = 0;
		long ans = 0;
		
		for(int i = 0; i < M; i++) {
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
			
			edge++;
			ans += edges[i].c;
			
			if(edge == N-1) break;
		}
		
		System.out.println(ans);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static class Edge implements Comparable<Edge> {
		int u, v, c;
		
		public Edge(int u, int v, int c) {
			this.u = u;
			this.v = v;
			this.c = c;
		}

		@Override
		public int compareTo(Edge o) {
			return this.c - o.c;
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
