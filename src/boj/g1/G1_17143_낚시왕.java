package boj.g1;

public class G1_17143_낚시왕 {
	static Reader in = new Reader();
	static int[][][] map;
	static int R, C, M, ans;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,1,-1};
	static int[] ex;
	
	public static void main(String[] args) throws Exception {
		R = in.nextInt();
		C = in.nextInt();
		M = in.nextInt();
		
		map = new int[R+1][C+1][3];
		ex = new int[] {0,1,R,C,1};
		
		for(int i = 0; i < M; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			map[r][c][0] = in.nextInt();
			map[r][c][1] = in.nextInt();
			map[r][c][2] = in.nextInt();
		}
		
		for(int i = 1; i <= C; i++) {
			catchShark(i);
			map = move();
		}
		
		System.out.println(ans);
	}
	
	static void catchShark(int idx) {
		for(int r = 1; r <= R; r++) {
			if(map[r][idx][2] != 0) {
				ans += map[r][idx][2];
				map[r][idx] = new int[] {0, 0, 0};
				break;
			}
		}
	}
	
	static int[][][] move() {
		int[][][] res = new int[R+1][C+1][3];
		
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				if(map[r][c][2] != 0) {
					int s = map[r][c][0];
					int d = map[r][c][1];
					int nr = r + dr[d] * s;
					int nc = c + dc[d] * s;
					
					if(!isIn(nr, nc)) {
						int exceed = (d < 3 ? Math.abs(nr - ex[d]) : Math.abs(nc - ex[d])) - 1;
						d = (exceed / (d < 3 ? R-1 : C-1)) % 2 == 0 ? (d % 2 == 1 ? d+1 : d-1) : d;
						if(d < 3) {
							nr = d == 1 ? R - (exceed % (R-1) + 1) : 1 + (exceed % (R-1) + 1);
						}
						else {
							nc = d == 3 ? 1 + (exceed % (C-1) + 1) : C - (exceed % (C-1) + 1);
						}
					}
					
					if(map[r][c][2] > res[nr][nc][2]) {
						res[nr][nc] = new int[] {s, d, map[r][c][2]};
					}
				}
			}
		}
		
		return res;
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= R && 1 <= c && c <= C;
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