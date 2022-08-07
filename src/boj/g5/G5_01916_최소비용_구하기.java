package boj.g5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class G5_01916_최소비용_구하기 {
	static Reader in = new Reader();
	static Map<Integer, List<int[]>> graph = new HashMap<>();
	static int N, M;
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		for(int i = 1; i <= N; i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for(int i = 0; i < M; i++) {
			int s = in.nextInt();
			int e = in.nextInt();
			int c = in.nextInt();
			
			graph.get(s).add(new int[] {e, c});
		}
		
		dist = new int[N+1];
		for(int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		int from = in.nextInt();
		int to = in.nextInt();
		
		dijkstra(from);
		System.out.println(dist[to]);
	}
	
	static void dijkstra(int from) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});
		
		dist[from] = 0;
		pq.add(new int[] {from, 0});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cur_dest = cur[0];
			int cur_dist = cur[1];
			
			if(dist[cur_dest] < cur_dist) {
				continue;
			}
			
			for(int[] next: graph.get(cur_dest)) {
				int new_dest = next[0];
				int new_dist = next[1];
				int temp = new_dist + cur_dist;
				
				if(temp < dist[new_dest]) {
					dist[new_dest] = temp;
					pq.add(new int[] {new_dest, temp});
				}
			}
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

		long nextLong() throws Exception {
			long n = 0;
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
