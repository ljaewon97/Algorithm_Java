package boj.s2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class S2_24445_너비_우선_탐색_2 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static boolean[] visited;
	static int[] order;
	static int N, M, R, o = 1;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		order = new int[N+1];
		
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
		
		for(int i = 1; i <= N; i++) {
			Collections.sort(graph[i], Collections.reverseOrder());
		}
		
		bfs();
		
		for(int i = 1; i <= N; i++) {
			sb.append(order[i]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Deque<Integer> deque = new LinkedList<>();
		deque.add(R);
		visited[R] = true;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			order[cur] = o++;
			
			for(int next: graph[cur]) {
				if(!visited[next]) {
					deque.add(next);
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
