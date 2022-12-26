package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class G4_10282_해킹 {
	static List<Node>[] graph;
	static int n, d, c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			graph = new ArrayList[n+1];
			
			for(int i = 1; i <= n; i++) {
				graph[i] = new ArrayList<>();
			}
			
			while(d-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				graph[b].add(new Node(a, s));
			}
			
			int[] res = dijkstra();
			
			sb.append(String.format("%d %d\n", res[0], res[1]));
		}
		
		System.out.println(sb);
	}
	
	static int[] dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[c] = 0;
		pq.add(new Node(c, 0));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.t > dist[cur.v]) continue;
			
			for(Node next: graph[cur.v]) {
				if(cur.t + next.t < dist[next.v]) {
					dist[next.v] = cur.t + next.t;
					pq.add(new Node(next.v, dist[next.v]));
				}
			}
		}
		
		int max = 0, cnt = 0;
		
		for(int i = 1; i <= n; i++) {
			if(dist[i] != Integer.MAX_VALUE) {
				max = Math.max(max, dist[i]);
				cnt++;
			}
		}
		
		return new int[] {cnt, max};
	}
	
	static class Node implements Comparable<Node> {
		int v, t;
		
		public Node(int v, int t) {
			this.v = v;
			this.t = t;
		}

		public int compareTo(Node o) {
			return Integer.compare(this.t, o.t);
		}
	}
}
