package boj.s1;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class S1_14940_쉬운_최단거리 {
	static Reader in = new Reader();
	static int[][] map, dist;
	static boolean[][] visited;
	static int N, M, sr, sc;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();

		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M];
		dist = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			Arrays.fill(dist[i], -1);
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
				if(map[i][j] == 2) {
					sr = i;
					sc = j;
				}
				else if(map[i][j] == 0) dist[i][j] = 0;
			}
		}
		
		bfs();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sb.append(dist[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			dist[p.r][p.c] = p.d;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					queue.add(new Point(nr, nc, p.d+1));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Point {
		int r, c, d;
		
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
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
			while ((c = read()) <= 32);
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
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
