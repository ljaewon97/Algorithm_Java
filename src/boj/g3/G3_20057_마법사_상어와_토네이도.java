package boj.g3;

public class G3_20057_마법사_상어와_토네이도 {
	static Reader in = new Reader();
	static int[][] map;
	static int N, ans, tr, tc;
	static int[] dr = {0,1,0,-1}; // 좌 하 우 상 순서
	static int[] dc = {-1,0,1,0};
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		tr = tc = N/2;
		int d = 0;
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				move(d);
			}
			d = (d+1)%4;
			for(int j = 0; j < i; j++) {
				move(d);
			}
			d = (d+1)%4;
		}
		
		for(int j = 0; j < N-1; j++) {
			move(d);
		}
		
		System.out.println(ans);
	}
	
	static void move(int d) {
		tr += dr[d];
		tc += dc[d];
		
		int s1 = map[tr][tc] * 1 / 100;
		int s2 = map[tr][tc] * 2 / 100;
		int s5 = map[tr][tc] * 5 / 100;
		int s7 = map[tr][tc] * 7 / 100;
		int s10 = map[tr][tc] * 10 / 100;
		
		int nr = tr + 2*dr[d];
		int nc = tc + 2*dc[d];
		moveSand(nr, nc, s5);
		
		nr = tr + dr[(d+3)%4];
		nc = tc + dc[(d+3)%4];
		moveSand(nr, nc, s7);
		
		int nr2 = nr + dr[d];
		int nc2 = nc + dc[d];
		moveSand(nr2, nc2, s10);
		
		nr2 = nr + dr[(d+3)%4];
		nc2 = nc + dc[(d+3)%4];
		moveSand(nr2, nc2, s2);
		
		nr2 = nr + dr[(d+2)%4];
		nc2 = nc + dc[(d+2)%4];
		moveSand(nr2, nc2, s1);
		
		nr = tr + dr[(d+1)%4];
		nc = tc + dc[(d+1)%4];
		moveSand(nr, nc, s7);
		
		nr2 = nr + dr[d];
		nc2 = nc + dc[d];
		moveSand(nr2, nc2, s10);
		
		nr2 = nr + dr[(d+1)%4];
		nc2 = nc + dc[(d+1)%4];
		moveSand(nr2, nc2, s2);
		
		nr2 = nr + dr[(d+2)%4];
		nc2 = nc + dc[(d+2)%4];
		moveSand(nr2, nc2, s1);
		
		nr = tr + dr[d];
		nc = tc + dc[d];
		moveSand(nr, nc, map[tr][tc]);
	}
	
	static void moveSand(int r, int c, int amount) {
		if(amount == 0) return;
		
		map[tr][tc] -= amount;
		
		if(isIn(r, c)) map[r][c] += amount;
		else ans += amount;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
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
