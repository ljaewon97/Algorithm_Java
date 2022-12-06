package boj.g1;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class G1_02021_최소_환승_경로 {
	static Reader in = new Reader();
	static List<Integer>[] graph;
	static int N, L, startSt, endSt;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		L = in.nextInt();
		
		graph = new LinkedList[N+L+1];
		
		for(int i = 1; i <= N+L; i++) {
			graph[i] = new LinkedList<>();
		}
		
		for(int l = 1; l <= L; l++) {
			while(true) {
				int s = in.nextInt();
				
				if(s == -1) break;
				
				graph[s].add(N+l);
				graph[N+l].add(s);
			}
		}
		
		startSt = in.nextInt();
		endSt = in.nextInt();
		
		if(startSt == endSt) System.out.println(0);
		else System.out.println(bfs());
	}
	
	static int bfs() {
		int ret = -1;
		Queue<Edge> queue = new LinkedList<>();
		boolean[] visited = new boolean[N+L+1];
		queue.add(new Edge(startSt, -1));
		visited[startSt] = true;
		
		while(!queue.isEmpty()) {
			Edge e = queue.poll();
			
			if(e.v == endSt) {
				ret = e.t;
				break;
			}
			
			for(int n: graph[e.v]) {
				if(!visited[n]) {
					visited[n] = true;
					queue.add(new Edge(n, e.t+(n>N?1:0)));
				}
			}
		}
		
		return ret;
	}
	
	static class Edge {
		int v, t;
		
		public Edge(int v, int t) {
			this.v = v;
			this.t = t;
		}
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
