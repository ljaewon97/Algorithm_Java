package swea;

import java.util.Arrays;

public class SWEA_D4_3124_최소_스패닝_트리 {
	static Reader in = new Reader();
	static int[][] edges;
	static int[] parent, depth;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int V = in.nextInt();
			int E = in.nextInt();
			
			edges = new int[E][3];
			parent = new int[V+1];
			depth = new int[V+1];
			
			for(int i = 1; i <= V; i++) {
				parent[i] = i;
				depth[i] = 1;
			}
			
			for(int i = 0; i < E; i++) {
				edges[i][0] = in.nextInt();
				edges[i][1] = in.nextInt();
				edges[i][2] = in.nextInt();
			}
			
			Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
			
			int edgeCnt = 0;
			long ans = 0L;
			
			for(int i = 0; i < E; i++) {
				int n1 = edges[i][0];
				int n2 = edges[i][1];
				int w = edges[i][2];
				
				int p1 = find(n1);
				int p2 = find(n2);
				
				if(p1 == p2) continue;
				
				if(depth[p1] < depth[p2]) {
					parent[p1] = p2;
					depth[p2] += p1;
				}
				else {
					parent[p2] = p1;
					depth[p1] += p2;
				}
				
				edgeCnt++;
				ans += w;
				
				if(edgeCnt == V-1) break;
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		
		return parent[node] = find(parent[node]);
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			boolean neg = false;
			while ((b = read()) <= 32);
			if (b == '-') {
				neg = true;
				b = read();
			}
			do n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}
	}
}
