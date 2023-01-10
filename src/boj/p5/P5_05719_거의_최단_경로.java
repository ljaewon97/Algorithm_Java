package boj.p5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class P5_05719_거의_최단_경로 {
	static Reader in = new Reader();
	static List<Road>[] graph;
	static boolean[][] blocked;
	static boolean[] visited;
	static int N, M, S, D, shortest;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			N = in.nextInt();
			M = in.nextInt();
			
			if(N+M == 0) break;
			
			S = in.nextInt();
			D = in.nextInt();
			
			graph = new ArrayList[N];
			blocked = new boolean[N][N];
			visited = new boolean[N];
			
			for(int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			while(M-- > 0) {
				int U = in.nextInt();
				int V = in.nextInt();
				int P = in.nextInt();
				
				graph[U].add(new Road(V, P));
			}
			
			shortest = dijkstra();
			
			visited[S] = true;
			dfs(S, -1, 0);
			
			sb.append(dijkstra2()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int dijkstra2() {
		int[] dist = new int[N];
		
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
	
	static boolean dfs(int cur, int prev, int dist) {
		if(dist > shortest) return false;
		
		if(cur == D && dist == shortest) {
			if(prev != -1) blocked[prev][cur] = true;
			return true;
		}
		
		boolean flag = false;
		
		for(Road next: graph[cur]) {
			if(!visited[next.v]) {
				visited[next.v] = true;
				if(dfs(next.v, cur, dist+next.d)) {
					if(prev != -1) blocked[prev][cur] = true;
					flag = true;
				}
				visited[next.v] = false;
			}
		}
		
		return flag;
	}
	
	static int dijkstra() {
		int[] dist = new int[N];
		
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
		
		return dist[D];
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
