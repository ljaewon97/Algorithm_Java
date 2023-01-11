package boj.p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class P5_05719_거의_최단_경로 {
	static Reader in = new Reader();
	static List<Road>[] graph;
	static List<Road>[] graphR;
	static boolean[][] blocked;
	static int[] dist;
	static int N, M, S, D;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			N = in.nextInt();
			M = in.nextInt();
			
			if(N+M == 0) break;
			
			S = in.nextInt();
			D = in.nextInt();
			
			graph = new ArrayList[N];
			graphR = new ArrayList[N];
			blocked = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
				graphR[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				int U = in.nextInt();
				int V = in.nextInt();
				int P = in.nextInt();
				
				graph[U].add(new Road(V, P));
				graphR[V].add(new Road(U, P));
			}
			
			dijkstra();
			
			bfs();
			
			sb.append(dijkstra2()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int dijkstra2() {
		dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[S] = 0;
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.add(new Road(S, 0));
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			
			if(cur.d > dist[cur.v]) continue;
			
			for(Road next: graph[cur.v]) {
				if(blocked[cur.v][next.v]) continue;
				int temp = cur.d + next.d;
				if(temp < dist[next.v]) {
					dist[next.v] = temp;
					pq.add(new Road(next.v, temp));
				}
			}
		}
		
		return dist[D] == Integer.MAX_VALUE ? -1 : dist[D];
	}
	
	static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(D);
		
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			for(Road prev: graphR[node]) {
				if(dist[prev.v] + prev.d == dist[node] && !blocked[prev.v][node]) {
					queue.add(prev.v);
					blocked[prev.v][node] = true;
				}
			}
		}
	}
	
	static void dijkstra() {
		dist = new int[N];
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[S] = 0;
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.add(new Road(S, 0));
		
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			
			if(cur.d > dist[cur.v]) continue;
			
			for(Road next: graph[cur.v]) {
				int temp = cur.d + next.d;
				if(temp < dist[next.v]) {
					dist[next.v] = temp;
					pq.add(new Road(next.v, temp));
				}
			}
		}
	}
	
	static class Road implements Comparable<Road> {
		int v, d;
		
		public Road(int v, int d) {
			this.v = v;
			this.d = d;
		}

		public int compareTo(Road o) {
			return Integer.compare(this.d, o.d);
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
