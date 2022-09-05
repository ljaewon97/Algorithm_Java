package boj.s1;

import java.util.LinkedList;
import java.util.Queue;

public class S1_01743_음식물_피하기 {
	static Reader in = new Reader();
	static boolean[][] map, visited;
	static int N, M, K, ans = 0;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		map = new boolean[N+1][M+1];
		visited = new boolean[N+1][M+1];
		
		for(int i = 0; i < K; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			
			map[r][c] = true;
		}
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= M; c++) {
				if(map[r][c] && !visited[r][c]) {
					bfs(r, c);
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc));
		visited[sr][sc] = true;
		int cnt = 0;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			cnt++;
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}
		
		ans = Math.max(ans, cnt);
	}
	
	static boolean isIn(int r, int c) {
		return 0 < r && r <= N && 0 < c && c <= M;
	}
	
	static private class Point {
		int r;
		int c;
		
		private Point(int r, int c) {
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
