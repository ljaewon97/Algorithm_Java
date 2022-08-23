package swea;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SWEA_D4_7465_창용_마을_무리의_개수 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int T = in.nextInt();
		
		for(int t = 1; t <= T; t++) {
			int N = in.nextInt();
			int M = in.nextInt();
			int ans = 0;
			
			graph = new ArrayList[N+1];
			visited = new boolean[N+1];
			
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
				if(!visited[i]) {
					bfs(i);
					ans++;
				}
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(start);
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(int next: graph[node]) {
				if(!visited[next]) {
					visited[next] = true;
					queue.add(next);
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
