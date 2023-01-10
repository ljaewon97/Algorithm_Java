package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class G4_02665_미로만들기 {
	static int[][] map;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = '1' - line.charAt(j);
			}
		}
		
		System.out.println(dijkstra());
	}
	
	static int dijkstra() {
		int[][] dist = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		
		dist[0][0] = 0;
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.add(new Point(0, 0, 0));
		
		while(!pq.isEmpty()) {
			Point cur = pq.poll();
			
			if(cur.d > dist[cur.r][cur.c]) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				int nd = cur.d + map[nr][nc];
				if(nd < dist[nr][nc]) {
					dist[nr][nc] = nd;
					pq.add(new Point(nr, nc, nd));
				}
			}
		}
		
		return dist[N-1][N-1];
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Point implements Comparable<Point> {
		int r, c, d;
		
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public int compareTo(Point o) {
			return this.d - o.d;
		}
	}
}
