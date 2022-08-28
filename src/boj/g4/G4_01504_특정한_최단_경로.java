package boj.g4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class G4_01504_특정한_최단_경로 {
	static Reader in = new Reader();
	static final long INF = Long.MAX_VALUE;
	static List<Node>[] graph;
	static int N, E;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		E = in.nextInt();
		
		graph = new ArrayList[N+1];
		
		for(int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int w = in.nextInt();
			
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		int v1 = in.nextInt();
		int v2 = in.nextInt();
		
		long[] dist1 = dijkstra(1);
		
		if(dist1[N] == INF) {
			System.out.println(-1);
			return;
		}
		
		long[] distv1 = dijkstra(v1);
		
		if(distv1[v2] == INF) {
			System.out.println(-1);
			return;
		}
		
		long[] distv2 = dijkstra(v2);
		
		long case1 = (dist1[v1] == INF || distv1[v2] == INF || distv2[N] == INF) ? INF : dist1[v1] + distv1[v2] + distv2[N];
		long case2 = (dist1[v2] == INF || distv2[v1] == INF || distv1[N] == INF) ? INF : dist1[v2] + distv2[v1] + distv1[N];
		
		long ans = Math.min(case1, case2);
		
		System.out.println(ans == INF ? -1 : ans);
	}
	
	static long[] dijkstra(int start) {
		long[] dist = new long[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(dist[cur.no] < cur.w) continue;
			
			for(Node next: graph[cur.no]) {
				int temp = cur.w + next.w;
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
		int w;
		
		public Node(int no, int w) {
			this.no = no;
			this.w = w;
		}
		
		public int compareTo(Node o) {
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
