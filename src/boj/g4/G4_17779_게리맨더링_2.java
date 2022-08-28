package boj.g4;

public class G4_17779_게리맨더링_2 {
	static Reader in = new Reader();
	static int[][] map;
	static int N, sum;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		map = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = in.nextInt();
				sum += map[i][j];
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int r = 1; r < N-1; r++) {
			for(int c = 2; c < N; c++) {
				for(int d1 = 1; d1 <= c-1; d1++) {
					for(int d2 = 1; d2 <= N-c; d2++) {
						if(r+d1+d2 <= N) {
							int temp = divide(r, c, d1, d2);
							ans = Math.min(ans, temp);
						}
					}
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static int divide(int x, int y, int d1, int d2) {
		int[] pops = new int[5];
		
		for(int r = 1; r < x+d1; r++) {
			for(int c = 1; c <= y-(r>=x?r-x+1:0); c++) {
				pops[0] += map[r][c];
			}
		}
		
		for(int c = N; c > y; c--) {
			for(int r = 1; r <= x+d2-(c>y+d2?0:y+d2-c+1); r++) {
				pops[1] += map[r][c];
			}
		}
		
		for(int c = 1; c < y-d1+d2; c++) {
			for(int r = x+d1+(c<y-d1?0:c-y+d1+1); r <= N; r++) {
				pops[2] += map[r][c];
			}
		}
		
		for(int r = N; r > x+d2; r--) {
			for(int c = y-d1+d2+(r>x+d1+d2?0:x+d1+d2-r+1); c <= N; c++) {
				pops[3] += map[r][c];
			}
		}
		
		pops[4] = sum - pops[0] - pops[1] - pops[2] - pops[3];
		
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < 5; i++) {
			max = Math.max(max, pops[i]);
			min = Math.min(min, pops[i]);
		}
		
		return max - min;
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
