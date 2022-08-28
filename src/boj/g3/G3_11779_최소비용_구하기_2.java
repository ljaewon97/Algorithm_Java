package boj.g3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

public class G3_11779_최소비용_구하기_2 {
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
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int w = in.nextInt();
			
			graph[a].add(new Edge(b, w));
		}
		
		int from = in.nextInt();
		int to = in.nextInt();
		
		dijkstra(from, to);
	}
	
	static void dijkstra(int from, int to) {
		long[][] dist = new long[N+1][2];
		for(int i = 1; i <= N; i++) {
			dist[i][0] = Long.MAX_VALUE;
		}
		dist[from][0] = 0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(from, 0));
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			if(dist[cur.to][0] < cur.w) continue;
			
			for(Edge next: graph[cur.to]) {
				int temp = cur.w + next.w;
				if(temp < dist[next.to][0]) {
					dist[next.to][0] = temp;
					dist[next.to][1] = cur.to;
					pq.add(new Edge(next.to, temp));
				}
			}
		}
		
		System.out.println(dist[to][0]);
		
		Stack<Long> stack = new Stack<>();
		int cnt = 1;
		long prev = to;
		stack.push(prev);
		
		while(prev != from) {
			prev = dist[(int) prev][1];
			stack.push(prev);
			cnt++;
		}
		
		System.out.println(cnt);
		
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int to;
		int w;
		
		public Edge(int to, int w) {
			this.to = to;
			this.w = w;
		}

		public int compareTo(Edge o) {
			return this.w - o.w;
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
			if (neg)
				return -n;
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
