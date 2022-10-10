package boj.g5;

import java.util.LinkedList;
import java.util.Queue;

public class G5_21610_마법사_상어와_비바라기 {
	static Reader in = new Reader();
	static Queue<Cloud> clouds = new LinkedList<>();
	static int[][] map;
	static boolean[][] prev;
	static int N, M;
	static int[] dr = {0,0,-1,-1,-1,0,1,1,1};
	static int[] dc = {0,-1,-1,0,1,1,1,0,-1};	
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		M = in.nextInt();
		
		map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = in.nextInt();
			}
		}
		
		init();
		
		while(M-- > 0) {
			int d = in.nextInt();
			int s = in.nextInt() % N;
			
			moveAndRain(dr[d] * s, dc[d] * s);
			waterMagic();
			makeClouds();
		}
		
		System.out.println(waterSum());
	}
	
	static void init() {
		clouds.add(new Cloud(N, 1));
		clouds.add(new Cloud(N, 2));
		clouds.add(new Cloud(N-1, 1));
		clouds.add(new Cloud(N-1, 2));
	}
	
	static void moveAndRain(int drv, int dcv) {
		for(Cloud cloud: clouds) {
			cloud.move(drv, dcv);
			cloud.rain();
		}
	}
	
	static void waterMagic() {
		prev = new boolean[N+1][N+1];
		
		while(!clouds.isEmpty()) {
			Cloud cloud = clouds.poll();
			
			cloud.magic();
			prev[cloud.r][cloud.c] = true;
		}
	}
	
	static void makeClouds() {
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				if(!prev[r][c] && map[r][c] >= 2) {
					clouds.add(new Cloud(r, c));
					map[r][c] -= 2;
				}
			}
		}
	}
	
	static int waterSum() {
		int sum = 0;
		
		for(int r = 1; r <= N; r++) {
			for(int c = 1; c <= N; c++) {
				sum += map[r][c];
			}
		}
		
		return sum;
	}
	
	static class Cloud {
		int r, c;
		
		public Cloud(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		void move(int drv, int dcv) {
			r = validCoord(r + drv);
			c = validCoord(c + dcv);
		}
		
		void rain() {
			map[r][c]++;
		}
		
		void magic() {
			for(int i = 2; i <= 8; i += 2) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && map[nr][nc] > 0) map[r][c]++;
			}
		}
		
		boolean isIn(int nr, int nc) {
			return 1 <= nr && nr <= N && 1 <= nc && nc <= N;
		}
		
		int validCoord(int x) {
			if(x < 1) return N + x;
			else if(x > N) return x - N;
			else return x;
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
			return neg ? -n : n;
		}

		long nextLong() throws Exception {
			long n = 0;
			byte c;
			while ((c = read()) <= 32)
				;
			boolean neg = c == '-' ? true : false;
			if (neg)
				c = read();
			do
				n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			return neg ? -n : n;
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
