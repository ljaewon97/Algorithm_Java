package boj.g3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class G3_01238_파티 {
	static Reader in = new Reader();
	static List<Node>[] graph, graphR;
	static int N, M, X;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		X = in.nextInt();
		
		graph = new ArrayList[N+1];
		graphR = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
			graphR[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int t = in.nextInt();
			
			graph[a].add(new Node(b, t));
			graphR[b].add(new Node(a, t));
		}
		
		int[] distToHome = dijkstra(graph, X);
		int[] distToParty = dijkstra(graphR, X);
		
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			ans = Math.max(ans, distToHome[i] + distToParty[i]);
		}
		
		System.out.println(ans);
	}
	
	static int[] dijkstra(List<Node>[] graph, int start) {
		int[] dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[start] = 0;
		pq.add(new Node(start, dist[start]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.no] < cur.time) continue;
			
			for(Node next: graph[cur.no]) {
				int temp = cur.time + next.time;
				if(temp < dist[next.no]) {
					dist[next.no] = temp;
					pq.add(new Node(next.no, temp));
				}
			}
		}
		
		return dist;
	}
	
	static class Node implements Comparable<Node> {
		int no;
		int time;
		
		public Node(int no, int time) {
			this.no = no;
			this.time = time;
		}
		
		public int compareTo(Node o) {
			return this.time - o.time;
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
