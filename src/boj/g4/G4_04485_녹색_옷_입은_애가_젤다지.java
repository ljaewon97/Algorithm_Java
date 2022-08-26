package boj.g4;

import java.util.PriorityQueue;

public class G4_04485_녹색_옷_입은_애가_젤다지 {
	static class Node implements Comparable<Node> {
		int r;
		int c;
		int w;
		
		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
	
	static Reader in = new Reader();
	static int[][] map, dist;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int p = 1;
		
		while(true) {
			N = in.nextInt();
			
			if(N == 0) break;
			
			map = new int[N][N];
			dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = in.nextInt();
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			
			dijkstra(0, 0);
			
			sb.append(String.format("Problem %d: %d\n", p, dist[N-1][N-1]));
			
			p++;
		}
		
		System.out.println(sb);
	}
	
	static void dijkstra(int sr, int sc) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		dist[sr][sc] = map[sr][sc];
		pq.add(new Node(sr, sc, dist[sr][sc]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int r = cur.r;
			int c = cur.c;
			
			if(r == N-1 && c == N-1) return;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
				
				int temp = dist[r][c] + map[nr][nc];
				if(temp < dist[nr][nc]) {
					dist[nr][nc] = temp;
					pq.add(new Node(nr, nc, dist[nr][nc]));
				}
			}
		}
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
		byte[] buffer = new byte[SIZE];
		int idx, size;

		boolean isNumber(byte b) {
			return 47 < b && b < 58;
		}

		byte read() throws Exception {
			if (idx == size) {
				size = System.in.read(buffer, idx = 0, SIZE);
				if (size < 0) {
					buffer[0] = -1;
				}
			}
			return buffer[idx++];
		}

		int nextInt() throws Exception {
			int n = 0;
			byte b;
			boolean neg = false;
			while ((b = read()) <= 32)
				;
			if (b == '-') {
				neg = true;
				b = read();
			}
			do
				n = (n << 3) + (n << 1) + (b & 15);
			while (isNumber(b = read()));
			if (neg)
				return -n;
			return n;
		}
	}
}
