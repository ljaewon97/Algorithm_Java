package boj.g4;

import java.util.Arrays;

public class G4_11657_타임머신 {
	static Reader in = new Reader();
	static Edge[] edges;
	static long[] dist;
	static final long INF = Long.MAX_VALUE/2-1;
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		
		edges = new Edge[M];
		dist = new long[N+1];
		
		Arrays.fill(dist, INF);
		
		for(int i = 0; i < M; i++) {
			edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());
		}
		
		boolean negCycle = bf();
		
		if(negCycle) System.out.println(-1);
		else {
			for(int i = 2; i <= N; i++) {
				if(dist[i] == INF) sb.append(-1);
				else sb.append(dist[i]);
				sb.append("\n");
			}
			
			System.out.println(sb);
		}
	}
	
	static boolean bf() {
		dist[1] = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int from = edges[j].from;
				int to = edges[j].to;
				int d = edges[j].d;
				
				if(dist[from] != INF && dist[from] + d < dist[to]) {
					dist[to] = dist[from] + d;
					
					if(i == N-1) return true;
				}
			}
		}
		
		return false;
	}
	
	static class Edge {
		int from, to, d;
		
		public Edge(int from, int to, int d) {
			this.from = from;
			this.to = to;
			this.d = d;
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
