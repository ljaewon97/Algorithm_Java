package boj.g4;

public class G4_14500_테트로미노 {
	static Reader in = new Reader();
	static int[][] map;
	static int N, M, ans;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		tetromino1();
		tetromino2();
		tetromino34();
		tetromino5();
		
		System.out.println(ans);
	}
	
	static void tetromino1() {
		for(int r = 0; r < N; r++) {
			int temp = 0;
			
			for(int c = 0; c < 4; c++) {
				temp += map[r][c];
			}
			
			ans = Math.max(ans, temp);
			
			for(int c = 4; c < M; c++) {
				temp += map[r][c];
				temp -= map[r][c-4];
				
				ans = Math.max(ans, temp);
			}
		}
		
		for(int c = 0; c < M; c++) {
			int temp = 0;
			
			for(int r = 0; r < 4; r++) {
				temp += map[r][c];
			}
			
			ans = Math.max(ans, temp);
			
			for(int r = 4; r < N; r++) {
				temp += map[r][c];
				temp -= map[r-4][c];
				
				ans = Math.max(ans, temp);
			}
		}
	}
	
	static void tetromino2() {
		for(int r = 1; r < N; r++) {
			int temp = map[r-1][0] + map[r-1][1] + map[r][0] + map[r][1];
			
			ans = Math.max(ans, temp);
			
			for(int c = 2; c < M; c++) {
				temp += map[r-1][c] + map[r][c];
				temp -= map[r-1][c-2] + map[r][c-2];
				
				ans = Math.max(ans, temp);
			}
		}
	}
	
	static void tetromino34() {
		int[][] dirs = {{2,2,1},{2,2,3},{2,1,2},{2,3,2}};
		
		for(int d = 0; d < 4; d++) {
			for(int i = 0; i < 4; i++) {
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < M; c++) {
						ans = Math.max(ans, sum(r, c, dirs[d], i));
					}
				}
			}
		}
	}
	
	static void tetromino5() {
		int[] dirs = {1,2,3};
		
		for(int i = 0; i < 4; i++) {
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					ans = Math.max(ans, sum2(r, c, dirs, i));
				}
			}
		}
	}
	
	static int sum(int r, int c, int[] dirs, int rotate) {
		int res = map[r][c];
		
		for(int i = 0; i < 3; i++) {
			r += dr[(dirs[i]+rotate)%4];
			c += dc[(dirs[i]+rotate)%4];
			
			if(!isIn(r, c)) return -1;
			
			res += map[r][c];
		}
		
		return res;
	}
	
	static int sum2(int r, int c, int[] dirs, int rotate) {
		int res = map[r][c];
		
		for(int i = 0; i < 3; i++) {
			int nr = r + dr[(dirs[i]+rotate)%4];
			int nc = c + dc[(dirs[i]+rotate)%4];
			
			if(!isIn(nr, nc)) return -1;
			
			res += map[nr][nc];
		}
		
		return res;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
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
