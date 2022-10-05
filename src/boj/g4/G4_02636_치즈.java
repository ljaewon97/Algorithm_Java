package boj.g4;

import java.util.LinkedList;
import java.util.Queue;

public class G4_02636_치즈 {
	static Reader in = new Reader();
	static Queue<Point> queue = new LinkedList<>();
	static Queue<Point> pend = new LinkedList<>();
	static int[][] map, corners;
	static boolean[][] visited;
	static int R, C;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		R = in.nextInt();
		C = in.nextInt();
		
		map = new int[R][C];
		visited = new boolean[R][C];
		corners = new int[][] {{0,0},{0,C-1},{R-1,0},{R-1,C-1}};
		
		for(int[] corner: corners) {
			queue.add(new Point(corner[0], corner[1]));
			visited[corner[0]][corner[1]] = true;
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		int time = 0;
		int prev = -1;
		
		while(true) {
			int cnt = find();
			melt();
			
			if(cnt == 0) break;
			
			time++;
			prev = cnt;
		}
		
		System.out.println(time);
		System.out.println(prev);
	}
	
	static int find() {
		int cnt = 0;
	
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				if(!visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
				else if(!visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					pend.add(new Point(nr, nc));
					cnt++;
				}
			}
		}
		
		return cnt;
	}
	
	static void melt() {
		while(!pend.isEmpty()) {
			Point cur = pend.poll();
			
			map[cur.r][cur.c] = 0;
			queue.add(cur);
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
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
