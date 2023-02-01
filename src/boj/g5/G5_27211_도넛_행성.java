package boj.g5;

import java.util.LinkedList;
import java.util.Queue;

public class G5_27211_도넛_행성 {
	static Reader in = new Reader();
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				map[r][c] = in.nextInt();
			}
		}
		
		int ans = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 0 && !visited[r][c]) {
					bfs(r, c);
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(nr == -1) nr = N-1;
				else if(nr == N) nr = 0;
				else if(nc == -1) nc = M-1;
				else if(nc == M) nc = 0;
				
				if(map[nr][nc] == 0 && !visited[nr][nc]) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
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
