package boj.g2;

import java.util.HashMap;
import java.util.Map;

public class G2_19237_어른_상어 {
	static Reader in = new Reader();
	static Map<Integer, int[][]> dirs = new HashMap<>();
	static int[][][] map, smell, temp;
	static int N, M, K, cnt;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		K = in.nextInt();
		
		map = new int[N][N][2];
		temp = new int[N][N][2];
		smell = new int[N][N][2];
		Map<Integer, Point> locs = new HashMap<>();
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				map[r][c][0] = in.nextInt();
				if(map[r][c][0] != 0) {
					locs.put(map[r][c][0], new Point(r, c));
					dirs.put(map[r][c][0], new int[4][4]);
					smell[r][c][0] = map[r][c][0];
					smell[r][c][1] = K;
				}
			}
		}
		
		for(int i = 1; i <= locs.size(); i++) {
			Point loc = locs.get(i);
			map[loc.r][loc.c][1] = in.nextInt()-1;
		}
		
		for(int i = 1; i <= locs.size(); i++) {
			int[][] dir = dirs.get(i);
			for(int j = 0; j < 4; j++) {
				for(int k = 0; k < 4; k++) {
					dir[j][k] = in.nextInt()-1;
				}
			}
		}
		
		int time = 0;
		
		while(true) {
			if(time >= 1000) {
				time = -1;
				break;
			}
			sharkMove();
			lowerSmell();
			moveFinish();
			time++;
			if(cnt == locs.size()-1) break;
		}
		
		System.out.println(time);
	}
	
	static void sharkMove() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c][0] != 0) {
					int[] dir = dirs.get(map[r][c][0])[map[r][c][1]];
					int d = -1;
					boolean moved = false;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[dir[i]];
						int nc = c + dc[dir[i]];
						
						if(!isIn(nr, nc)) continue;
						
						if(smell[nr][nc][0] == 0) {
							moved = true;
							d = dir[i];
							break;
						}
					}
					
					if(!moved) {
						for(int i = 0; i < 4; i++) {
							int nr = r + dr[dir[i]];
							int nc = c + dc[dir[i]];
							
							if(!isIn(nr, nc)) continue;
							
							if(smell[nr][nc][0] == map[r][c][0]) {
								d = dir[i];
								break;
							}
						}
					}
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(temp[nr][nc][0] != 0) cnt++;
					if(temp[nr][nc][0] == 0 || map[r][c][0] < temp[nr][nc][0]) {
						temp[nr][nc][0] = map[r][c][0];
						temp[nr][nc][1] = d;
					}
				}
			}
		}
	}
	
	static void lowerSmell() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(smell[r][c][1] > 1) {
					smell[r][c][1]--;
				}
				else if(smell[r][c][1] == 1) {
					smell[r][c][0] = smell[r][c][1] = 0;
				}
			}
		}
	}
	
	static void moveFinish() {
		map = temp;
		temp = new int[N][N][2];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c][0] != 0) {
					smell[r][c][0] = map[r][c][0];
					smell[r][c][1] = K;
				}
			}
		}
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
