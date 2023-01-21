package boj.p5;

import java.util.LinkedList;
import java.util.Queue;

public class P5_01981_배열에서_이동 {
	static Reader in = new Reader();
	static int[][] arr;
	static int N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		arr = new int[N][N];
		int min = 200;
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				arr[i][j] = in.nextInt();
				min = Math.min(min, arr[i][j]);
				max = Math.max(max, arr[i][j]);
			}
		}
		
		int i = min, ans = max-min;
		
		for(int j = min; j <= max; j++) {
			if(bfs(i, j)) {
				ans = Math.min(ans, j-i);
				
				while(i < j) {
					i++;
					
					if(bfs(i, j)) {
						ans = Math.min(ans, j-i);
					}
					else break;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean bfs(int min, int max) {
		boolean[][] map = new boolean[N][N];
		boolean[][] visited = new boolean[N][N];
		Queue<Point> queue = new LinkedList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(min <= arr[i][j] && arr[i][j] <= max) map[i][j] = true;
			}
		}
		
		if(map[0][0]) {
			queue.add(new Point(0, 0));
			visited[0][0] = true;
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.r == N-1 && p.c == N-1) return true;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[nr][nc] || !map[nr][nc]) continue;
				
				queue.add(new Point(nr, nc));
				visited[nr][nc] = true;
			}
		}
		
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
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
