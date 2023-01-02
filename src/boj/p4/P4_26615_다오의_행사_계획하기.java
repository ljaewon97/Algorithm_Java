package boj.p4;

import java.util.ArrayList;
import java.util.List;

public class P4_26615_다오의_행사_계획하기 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static int[][] parent, dist;
	static boolean[] visited;
	static long[] sum;
	static int[] depth;
	static int N, M, T, K, L;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		T = in.nextInt();
		L = (int) Math.ceil(Math.log(N*M) / Math.log(2));
		
		graph = new ArrayList[N*M];
		parent = new int[N*M][L+1];
		dist = new int[N*M][L+1];
		visited = new boolean[N*M];
		sum = new long[T+2];
		depth = new int[N*M];
		
		for(int i = 0; i < N*M; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N-1; i++) {
			for(int j = 0; j < M; j++) {
				if(in.nextInt() == 0) {
					int a = i*M+j;
					int b = a+M;
					
					graph[a].add(b);
					graph[b].add(a);
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M-1; j++) {
				if(in.nextInt() == 0) {
					int a = i*M+j;
					int b = a+1;
					
					graph[a].add(b);
					graph[b].add(a);
				}
			}
		}
		
		dfs(0);
		
		for(int i = 1; i <= L; i++) {
			for(int j = 0; j < N*M; j++) {
				parent[j][i] = parent[parent[j][i-1]][i-1];
				dist[j][i] = dist[j][i-1] + dist[parent[j][i-1]][i-1];
			}
		}
		
		K = in.nextInt();
		
		while(K-- > 0) {
			int S = in.nextInt();
			int E = in.nextInt();
			int A = in.nextInt()*M + in.nextInt();
			int B = in.nextInt()*M + in.nextInt();
			int V = in.nextInt();
			
			int people = lca(A, B) * V;
			sum[S] += people;
			sum[E+1] -= people;
		}
		
		for(int i = 1; i <= T; i++) {
			sum[i] += sum[i-1];
			sb.append(sum[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		
		for(int next: graph[cur]) {
			if(!visited[next]) {
				parent[next][0] = cur;
				dist[next][0] = 1;
				depth[next] = depth[cur] + 1;
				dfs(next);
			}
		}
	}
	
	static int lca(int a, int b) {
		if(depth[b] > depth[a]) return lca(b, a);
		
		int ret = 1;
		
		if(depth[a] != depth[b]) {
			for(int i = L; i >= 0; i--) {
				if(depth[a] >= depth[b] + (1<<i)) {
					ret += dist[a][i];
					a = parent[a][i];
				}
			}
		}
		
		if(a == b) return ret;
		
		for(int i = L; i >= 0; i--) {
			if(parent[a][i] != parent[b][i]) {
				ret += dist[a][i] + dist[b][i];
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		
		return ret + dist[a][0] + dist[b][0];
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
