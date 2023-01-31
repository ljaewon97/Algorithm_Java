package boj.p5;

import java.util.LinkedList;
import java.util.Queue;

public class P5_15806_영우의_기숙사_청소 {
	static Reader in = new Reader();
	static Queue<Point> queue = new LinkedList<>();
	static boolean[][] inspect;
	static boolean[][][] map;
	static int N, M, K, t;
	static int[] dr = {-1,-2,-2,-1,1,2,2,1};
	static int[] dc = {-2,-1,1,2,2,1,-1,-2};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		t = in.nextInt();
		
		if(N == M && M == K && K == 1) {
			System.out.println("NO");
			return;
		}
		
		inspect = new boolean[N][N];
		map = new boolean[N][N][2];
		
		while(M-- > 0) {
			int r = in.nextInt()-1;
			int c = in.nextInt()-1;
			
			queue.add(new Point(r, c, 0));
			map[r][c][0] = true;
		}
		
		while(K-- > 0) {
			int r = in.nextInt()-1;
			int c = in.nextInt()-1;
			
			inspect[r][c] = true;
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.d == t) continue;
			
			int nd = p.d+1;
			
			for(int i = 0; i < 8; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || map[nr][nc][nd&1]) continue;
				
				queue.add(new Point(nr, nc, nd));
				map[nr][nc][nd&1] = true;
			}
		}
		
		t &= 1;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(inspect[r][c] && map[r][c][t]) {
					System.out.println("YES");
					return;
				}
			}
		}
		
		System.out.println("NO");
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
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
