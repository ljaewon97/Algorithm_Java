package boj.g1;

public class G1_17143_낚시왕_ver2 {
	static Reader in = new Reader();
	static int[][][] map, temp;
	static int R, C, M, ans;
	
	public static void main(String[] args) throws Exception {
		R = in.nextInt();
		C = in.nextInt();
		M = in.nextInt();
		
		map = new int[R+1][C+1][3]; // 속력, 이동방향, 크기
		temp = new int[R+1][C+1][3];
		
		for(int i = 0; i < M; i++) {
			int r = in.nextInt();
			int c = in.nextInt();
			
			map[r][c][0] = in.nextInt();
			map[r][c][1] = in.nextInt();
			map[r][c][2] = in.nextInt();
		}
		
		for(int c = 1; c <= C; c++) {
			ans += catchShark(c);
			move();
		}
		
		System.out.println(ans);
	}
	
	static void move() {
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				if(map[r][c][2] != 0) {
					int s = map[r][c][0];
					int d = map[r][c][1];
					int z = map[r][c][2];
					int nr = -1, nc = -1, nd = d;
					
					for(int i = 0; i < 3; i++) {
						map[r][c][i] = 0;
					}
		
					// 위, 아래
					if(d <= 2) {
						nc = c;
						int ex = d == 1 ? s - r + 1 : s - R + r;
						if(ex > 0) {
							int div = (ex-1) / (R-1);
							int mod = (ex-1) % (R-1) + 1;
							nr = d == 1 ? (div % 2 == 0 ? 1 + mod : R - mod) : (div % 2 == 0 ? R - mod : 1 + mod); 
							nd = div % 2 == 0 ? 3 - d : d;
						}
						else nr = d == 1 ? r - s : r + s;
					}
					// 왼, 오른
					else {
						nr = r;
						int ex = d == 3 ? s - C + c : s - c + 1;
						if(ex > 0) {
							int div = (ex-1) / (C-1);
							int mod = (ex-1) % (C-1) + 1;
							nc = d == 4 ? (div % 2 == 0 ? 1 + mod : C - mod) : (div % 2 == 0 ? C - mod : 1 + mod); 
							nd = div % 2 == 0 ? 7 - d : d;
						}
						else nc = d == 4 ? c - s : c + s;
					}
					
					if(z > temp[nr][nc][2]) {
						temp[nr][nc][0] = s;
						temp[nr][nc][1] = nd;
						temp[nr][nc][2] = z;
					}
				}
			}
		}
		
		int[][][] swap = map;
		map = temp;
		temp = swap;
	}
	
	static int catchShark(int c) {
		for(int r = 1; r <= R; r++) {
			if(map[r][c][2] != 0) {
				int ret = map[r][c][2];
				
				for(int i = 0; i < 3; i++) {
					map[r][c][i] = 0;
				}
				
				return ret;
			}
		}
		
		return 0;
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
