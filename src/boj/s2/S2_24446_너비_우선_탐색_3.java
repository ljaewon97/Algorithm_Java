package boj.s2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class S2_24446_너비_우선_탐색_3 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] depth;
	static int N, M, R, o = 1;
	
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
		
		int a = 0, b = 0;
		for(int i = 0; i < M; i++) {
			a = in.nextInt();
			b = in.nextInt();
			
			graph[a].add(b);
			graph[b].add(a);
		}
		
		bfs(R);
		
		for(int i = 1; i <= N; i++) {
			sb.append(depth[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {start, 0});
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int node = cur[0];
			int d = cur[1];
			
			depth[node] = d;
			
			for(int next: graph[node]) {
				if(!visited[next]) {
					deque.add(new int[] {next, d+1});
					visited[next] = true;
				}
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
