package boj.s2;

public class S2_01780_종이의_개수 {
	static Reader in = new Reader();
	static int[][] mat;
	static int[] ans;
	static int N;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		
		mat = new int[N][N];
		ans = new int[3];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				mat[i][j] = in.nextInt();
			}
		}
		
		recur(0, 0, N);
		
		sb.append(ans[0]).append("\n");
		sb.append(ans[1]).append("\n");
		sb.append(ans[2]).append("\n");
		
		System.out.println(sb);
	}
	
	static void recur(int r, int c, int n) {
		if(n == 1) {
			ans[mat[r][c]+1]++;
			return;
		}
		
		if(check(r, c, n)) ans[mat[r][c]+1]++;
		else {
			int nn = n / 3;
			
			for(int i = 0; i < n; i += nn) {
				for(int j = 0; j < n; j += nn) {
					recur(r+i, c+j, nn);
				}
			}
		}
	}
	
	static boolean check(int r, int c, int n) {
		int num = mat[r][c];
		
		for(int i = r; i < r+n; i++) {
			for(int j = c; j < c+n; j++) {
				if(mat[i][j] != num) return false;
			}
		}
		
		return true;
	}
	
	static class Reader {
		final int SIZE = 1 << 15;
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
