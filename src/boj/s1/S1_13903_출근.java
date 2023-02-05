package boj.s1;

import java.util.LinkedList;
import java.util.Queue;

public class S1_13903_출근 {
	static Reader in = new Reader();
	static boolean[][] map, visited;
	static int[] dr, dc;
	static int R, C, N;
	
	public static void main(String[] args) throws Exception {
		R = in.nextInt();
		C = in.nextInt();
		
		map = new boolean[R][C];
		visited = new boolean[R][C];
		
		for(int r = 0; r < R; ++r) {
			for(int c = 0; c < C; ++c) {
				map[r][c] = in.nextInt() == 1 ? true : false;
			}
		}
		
		N = in.nextInt();
		
		dr = new int[N];
		dc = new int[N];
		
		for(int i = 0; i < N; ++i) {
			dr[i] = in.nextInt();
			dc[i] = in.nextInt();
		}
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		
		for(int c = 0; c < C; c++) {
			if(map[0][c]) {
				queue.add(new Point(0, c, 0));
				visited[0][c] = true;
			}
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < N; ++i) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && map[nr][nc] && !visited[nr][nc]) {
					if(nr == R-1) return p.d+1;
					
					queue.add(new Point(nr, nc, p.d+1));
					visited[nr][nc] = true;
				}
			}
		}
		
		
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
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
