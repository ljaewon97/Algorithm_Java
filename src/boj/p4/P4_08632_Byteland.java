package boj.p4;

import java.util.Arrays;

public class P4_08632_Byteland {
	static Reader in = new Reader();
	static int[][] edges, parent;
	static int[] depth;
	static boolean[] ans;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		edges = new int[M][];
		parent = new int[N+1][2];
		depth = new int[N+1];
		ans = new boolean[M];
		
		for(int i = 1; i <= N; i++) {
			parent[i][0] = i;
			parent[i][1] = -1;
			depth[i] = 1;
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int c = in.nextInt();
			
			edges[i] = new int[] {a, b, c, i};
		}
		
		Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
		
		int edgeCnt = 0;
		boolean flag = false;
		int temp = -1;
		
		for(int i = 0; i < M; i++) {
			if(edgeCnt == N-1) {
				flag = true;
				temp = edges[i-1][2];
			}
			
			if(flag && edges[i][2] != temp) break;
			
			if(union(i, edges[i][0], edges[i][1])) {
				edgeCnt++;
			}
		}
		
		for(int i = 0; i < M; i++) {
			sb.append(ans[i] ? "TAK" : "NIE").append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int find(int node) {
		if(parent[node][0] == node) return node;
		return parent[node][0] = find(parent[node][0]);
	}
	
	static boolean union(int idx, int n1, int n2) {
		int p1 = find(n1);
		int p2 = find(n2);
		
		if(p1 == p2) {
			int w = edges[idx][2];
			
			while(n1 != p1) {
				if(edges[parent[n1][1]][2] == w) {
					ans[edges[idx][3]] = true;
					return false;
				}
			}
			
			while(n2 != p1) {
				if(edges[parent[n2][1]][2] == w) {
					ans[edges[idx][3]] = true;
					return false;
				}
			}
			
			return false;
		}
		
		if(depth[p1] < depth[p2]) {
			parent[p1][0] = p2;
			parent[p1][1] = idx;
			depth[p2] += depth[p1];
		}
		else {
			parent[p2][0] = p1;
			parent[p2][1] = idx;
			depth[p1] += depth[p2];
		}
		
		ans[edges[idx][3]] = true;
		
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
