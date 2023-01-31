package boj.g2;

import java.util.ArrayList;
import java.util.List;

public class G2_01167_트리의_지름 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static boolean[] visited;
	static int V, max, node;
	
	public static void main(String[] args) throws Exception {
		V = in.nextInt();
		
		graph = new ArrayList[V+1];
		
		for(int i = 1; i <= V; i++) {
			int no = in.nextInt();
			graph[no] = new ArrayList<>();
			
			while(true) {
				int v = in.nextInt();
				
				if(v == -1) break;
				
				int d = in.nextInt();
				
				graph[no].add(new Edge(v, d));
			}
		}
		
		visited = new boolean[V+1];
		dfs(1, 0);
		
		int start = node;
		max = 0;
		
		visited = new boolean[V+1];
		dfs(start, 0);
		
		System.out.println(max);
	}
	
	static void dfs(int cur, int dist) {
		visited[cur] = true;
		if(dist > max) {
			max = dist;
			node = cur;
		}
		
		for(Edge next: graph[cur]) {
			if(!visited[next.v]) {
				dfs(next.v, dist+next.d);
			}
		}
	}
	
	static class Edge {
		int v, d;
		
		public Edge(int v, int d) {
			this.v = v;
			this.d = d;
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int index, size;

		int nextInt() throws Exception {
			int n = 0;
			byte c;
			while ((c = read()) <= 32);
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
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
