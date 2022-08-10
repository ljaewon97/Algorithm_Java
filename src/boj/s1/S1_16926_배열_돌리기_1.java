package boj.s1;

public class S1_16926_배열_돌리기_1 {
	static Reader in = new Reader();
	static int N, M, R;
	static int[][] arr;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		
		int layer = Math.min(N, M) / 2;
		for(int i = 0, around = 2*N + 2*M - 4; i < layer; i++, around -= 8) {
			int step = R % around;
			for(int j = 0; j < step; j++) {
				rotate(i);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void rotate(int layer) {
		int temp = arr[N-1-layer][layer];
		
		for(int r = N-1-layer; r > layer; r--) {
			arr[r][layer] = arr[r-1][layer];
		}
		
		for(int c = layer; c < M-1-layer; c++) {
			arr[layer][c] = arr[layer][c+1];
		}
		
		for(int r = layer; r < N-1-layer; r++) {
			arr[r][M-1-layer] = arr[r+1][M-1-layer];
		}
		
		for(int c = M-1-layer; c > layer+1; c--) {
			arr[N-1-layer][c] = arr[N-1-layer][c-1];
		}
		
		arr[N-1-layer][layer+1] = temp;
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