package boj.s2;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

public class S2_18352_특정_거리의_도시_찾기 {
	static Reader in = new Reader();
	static ArrayList<Integer>[] graph;
	static boolean[] visited;
	static Set<Integer> ans = new TreeSet<>();
	static int K;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int N = in.nextInt();
		int M = in.nextInt();
		K = in.nextInt();
		int X = in.nextInt();
		
		graph = new ArrayList[N+1];
		visited = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			
			graph[a].add(b);
		}
		
		bfs(X);
		
		if(ans.isEmpty()) {
			System.out.println(-1);
		}
		else {
			for(int num: ans) {
				sb.append(num).append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static void bfs(int start) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {start, 0});
		visited[start] = true;
		
		while(!deque.isEmpty()) {
			int[] temp = deque.poll();
			int cur = temp[0];
			int dist = temp[1];
			
			if(dist == K) {
				ans.add(cur);
				continue;
			}
			
			for(int next: graph[cur]) {
				if(!visited[next]) {
					visited[next] = true;
					deque.add(new int[] {next, dist+1});
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
