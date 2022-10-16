package boj.g1;

public class G1_23290_마법사_상어와_복제 {
	static Reader in = new Reader();
	static int[][] dirs = new int[64][3];
	static Grid[][] map, temp;
	static boolean[][][] smell;
	static int[] dirOrder = {2,0,6,4};
	static int[] choosed = new int[3];
	static int M, S, sr, sc, t;
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {-1,-1,0,1,1,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		M = in.nextInt();
		S = in.nextInt();
		
		map = new Grid[5][5];
		temp = new Grid[5][5];
		smell = new boolean[S][5][5];
		
		makeDirs(0);
		
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				map[i][j] = new Grid();
				temp[i][j] = new Grid();
			}
		}
		
		for(int i = 0; i < M; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			int d = in.nextInt() - 1;
			
			map[r][c].fishes[d]++;
		}
		
		sr = in.nextInt();
		sc = in.nextInt();
		
		for(int i = 0; i < S; i++) {
			moveFish(i);
			moveShark(i);
			magic();
		}
		
		System.out.println(countFish());
	}
	
	static void makeDirs(int nth) {
		if(nth == 3) {
			for(int i = 0; i < 3; i++) {
				dirs[t][i] = choosed[i];
			}
			t++;
			return;
		}
		
		for(int d: dirOrder) {
			choosed[nth] = d;
			makeDirs(nth+1);
		}
	}
	
	static void moveFish(int turn) {
		for(int r = 1; r <= 4; r++) {
			for(int c = 1; c <= 4; c++) {
				for(int d = 0; d < 8; d++) {
					if(map[r][c].fishes[d] != 0) {
						boolean moved = false;
						
						for(int i = 0; i < 8; i++) {
							int nd = (d+7*i)%8;
							int nr = r + dr[nd];
							int nc = c + dc[nd];
							
							if(!isIn(nr, nc)) continue;
							if(nr == sr && nc == sc) continue;
							if(turn >= 1 && smell[turn-1][nr][nc]) continue;
							if(turn >= 2 && smell[turn-2][nr][nc]) continue;
							
							temp[nr][nc].fishes[nd] += map[r][c].fishes[d];
							moved = true;
							break;
						}
						
						if(!moved) temp[r][c].fishes[d] += map[r][c].fishes[d];
					}
				}
			}
		}
	}
	
	static void moveShark(int turn) {
		int idx = -1, max = -1;
		
		for(int d = 0; d < 64; d++) {
			boolean[][] removed = new boolean[5][5];
			boolean out = false;
			int nr = sr;
			int nc = sc;
			int cnt = 0;
			
			for(int i = 0; i < 3; i++) {
				nr += dr[dirs[d][i]];
				nc += dc[dirs[d][i]];
				
				if(!isIn(nr, nc)) {
					out = true;
					break;
				}
				
				if(!removed[nr][nc]) {
					removed[nr][nc] = true;
					for(int j = 0; j < 8; j++) {
						cnt += temp[nr][nc].fishes[j];
					}
				}
			}
			
			if(!out && cnt > max) {
				max = cnt;
				idx = d;
			}
		}
		
		int r = sr;
		int c = sc;
		
		for(int i = 0; i < 3; i++) {
			r += dr[dirs[idx][i]];
			c += dc[dirs[idx][i]];
			int cnt = 0;
			
			for(int j = 0; j < 8; j++) {
				cnt += temp[r][c].fishes[j];
				temp[r][c].fishes[j] = 0;
			}
			
			if(cnt != 0) smell[turn][r][c] = true;
		}
		
		sr = r;
		sc = c;
	}
	
	static void magic() {
		for(int r = 1; r <= 4; r++) {
			for(int c = 1; c <= 4; c++) {
				for(int i = 0; i < 8; i++) {
					map[r][c].fishes[i] += temp[r][c].fishes[i];
					temp[r][c].fishes[i] = 0;
				}
			}
		}
	}
	
	static int countFish() {
		int ret = 0;
		
		for(int r = 1; r <= 4; r++) {
			for(int c = 1; c <= 4; c++) {
				for(int i = 0; i < 8; i++) {
					ret += map[r][c].fishes[i];
				}
			}
		}	
		
		return ret;
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= 4 && 1 <= c && c <= 4;
	}
	
	static class Grid {
		int[] fishes;
		
		public Grid() {
			fishes = new int[8];
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
