package boj.g4;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class G4_01707_이분_그래프 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static int[] visited;
	static int V, E;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		int K = in.nextInt();
		
		for(int t = 0; t < K; t++) {
			V = in.nextInt();
			E = in.nextInt();
			
			graph = new ArrayList[V+1];
			visited = new int[V+1];
			
			for(int i = 1; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			
			for(int i = 0; i < E; i++) {
				int a = in.nextInt();
				int b = in.nextInt();
				
				graph[a].add(b);
				graph[b].add(a);
			}
			
			String ans = "YES";
			for(int i = 1; i <= V; i++) {
				if(visited[i] == 0 && !isBipartiteGraph(i)) {
					ans = "NO";
					break;
				}
			}
			
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static boolean isBipartiteGraph(int node) {
		Deque<Integer> deque = new LinkedList<>();
		deque.add(node);
		visited[node] = 1;
		
		while(!deque.isEmpty()) {
			int cur = deque.poll();
			
			for(int next: graph[cur]) {
				if(visited[next] == 0) {
					visited[next] = visited[cur] == 1 ? 2 : 1;
					deque.add(next);
				}
				else {
					if(visited[cur] == visited[next]) {
						return false;
					}
				}
			}
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
