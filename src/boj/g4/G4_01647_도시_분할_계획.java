package boj.g4;

import java.util.Arrays;

public class G4_01647_도시_분할_계획 {
	static Reader in = new Reader();
	static int[][] edges;
	static int[] parent, depth;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		edges = new int[M][3];
		parent = new int[N+1];
		depth = new int[N+1];
		
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
			depth[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			edges[i][0] = in.nextInt();
			edges[i][1] = in.nextInt();
			edges[i][2] = in.nextInt();
		}
		
		Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
		
		int edgeCnt = 0;
		long ans = 0;
		
		for(int i = 0; i < M; i++) {
			if(union(edges[i][0], edges[i][1])) {
				edgeCnt++;
				if(edgeCnt == N-1) break;
				ans += edges[i][2];
			}
		}
		
		System.out.println(ans);
	}
	
	static int find(int node) {
		if(parent[node] == node) return node;
		return parent[node] = find(parent[node]);
	}
	
	static boolean union(int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) return false;
		
		if(depth[p1] < depth[p2]) {
			parent[p1] = p2;
			depth[p2] += depth[p1];
		}
		else {
			parent[p2] = p1;
			depth[p1] += depth[p2];
		}
		
		return true;
	}
	
	static class Reader {
		final int SIZE = 1 << 13;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
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
