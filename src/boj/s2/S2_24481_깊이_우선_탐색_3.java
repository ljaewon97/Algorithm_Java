package boj.s2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class S2_24481_깊이_우선_탐색_3 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	static int N, M, R;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		depth = new int[N+1];
		
		Arrays.fill(depth, -1);
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i]);
		}
		
		dfs(R, 0);
		
		for(int i = 1; i <= N; i++) {
			sb.append(depth[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int node, int d) {
		visited[node] = true;
		
		depth[node] = d;
		
		for(int next: graph[node]) {
			if(!visited[next]) {
				dfs(next, d+1);
			}
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
