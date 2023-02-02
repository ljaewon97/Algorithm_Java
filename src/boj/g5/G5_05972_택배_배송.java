package boj.g5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class G5_05972_택배_배송 {
	static Reader in = new Reader();
	static List<Edge>[] graph;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		while(M-- > 0) {
			int A = in.nextInt();
			int B = in.nextInt();
			int C = in.nextInt();
			
			graph[A].add(new Edge(B, C));
			graph[B].add(new Edge(A, C));
		}
		
		System.out.println(dijkstra());
	}
	
	static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.add(new Edge(1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(cur.d > dist[cur.v]) continue;
			
			for(Edge next: graph[cur.v]) {
				int nd = cur.d + next.d;
				if(nd < dist[next.v]) {
					pq.add(new Edge(next.v, nd));
					dist[next.v] = nd; 
				}
			}
		}
		
		return dist[N];
	}
	
	static class Edge implements Comparable<Edge> {
		int v, d;
		
		public Edge(int v, int d) {
			this.v = v;
			this.d = d;
		}

		@Override
		public int compareTo(Edge o) {
			return this.d - o.d;
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
