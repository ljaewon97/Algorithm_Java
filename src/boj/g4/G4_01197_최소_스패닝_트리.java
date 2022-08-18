package boj.g4;

import java.util.Arrays;

public class G4_01197_최소_스패닝_트리 {
	static Reader in = new Reader();
	static int V, E;
	static int[][] edges;
	static int[] parent, depth;
	
	public static void main(String[] args) throws Exception {
		V = in.nextInt();
		E = in.nextInt();
		
		edges = new int[E][];
		parent = new int[V+1];
		depth = new int[V+1];
		
		for(int i = 1; i <= V; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < E; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			edges[i] = new int[] {c, a, b};
		}
		
		Arrays.sort(edges, (o1, o2) -> o1[0] - o2[0]);
		
		int edgeCnt = 0, ans = 0;
		
		for(int i = 0; i < E; i++) {
			if(edgeCnt == V-1) break;
			
			int n1 = edges[i][1];
			int n2 = edges[i][2];
			
			n1 = find(n1);
			n2 = find(n2);
			
			if(n1 == n2) continue;
			
			if(depth[n1] > depth[n2]) {
				parent[n2] = n1;
				depth[n1] += depth[n2];
			}
			else {
				parent[n1] = n2;
				depth[n2] += depth[n1];
			}
			
			ans += edges[i][0];
			edgeCnt++;
		}
		
		System.out.println(ans);
	}
	
	static int find(int node) {
		if(parent[node] == node) {
			return node;
		}
		
		int root = find(parent[node]);
		parent[node] = root;
		return root;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if (neg)
				return -n;
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
