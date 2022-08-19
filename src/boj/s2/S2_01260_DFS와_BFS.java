package boj.s2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class S2_01260_DFS와_BFS {
	static StringBuilder sb = new StringBuilder();
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static boolean[] visited;
	static int N, M, V;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		V = in.nextInt();
		
		graph = new ArrayList[N+1];
		
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
		
		visited = new boolean[N+1];
		dfs(V);
		
		sb.append("\n");
		
		visited = new boolean[N+1];
		bfs(V);
		
		System.out.println(sb);
	}
	
	static void dfs(int node) {
		visited[node] = true;
		sb.append(node).append(" ");
		
		for(int next: graph[node]) {
			if(!visited[next]) {
				visited[next] = true;
				dfs(next);
			}
		}
	}
	
	static void bfs(int start) {
		Deque<Integer> deque = new LinkedList<>();
		deque.add(start);
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int node = deque.poll();
			sb.append(node).append(" ");
			
			for(int next: graph[node]) {
				if(!visited[next]) {
					deque.add(next);
					visited[next] = true;
				}
			}
		}
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
			while ((b = read()) <= 32);
			do n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			return n;
		}
	}
}
