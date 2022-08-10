package boj.s2;

public class S2_17276_배열_돌리기 {
	static Reader in = new Reader();
	static int[][] arr;
	static int N;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		int T = in.nextInt();
		
		for(int t = 0; t < T; t++) {
			N = in.nextInt();
			int angle = in.nextInt() / 45;
			if(angle < 0) {
				angle = 8 + angle;
			}
			
			arr = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					arr[i][j] = in.nextInt();
				}
			}
			
			for(int i = 0; i < angle; i++) {
				rotate();
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void rotate() {
		int[] temp = new int[N];
		int half = N/2;
		
		for(int r = 0; r < N; r++) {
			temp[r] = arr[r][half];
		}
		
		for(int i = 0; i < N; i++) {
			arr[i][half] = arr[i][i];
			arr[i][i] = arr[half][i];
			arr[half][i] = arr[N-1-i][i];
			arr[N-1-i][i] = temp[N-1-i];
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
			boolean neg = c == '-' ? true : false;
			if(neg) c = read();
			do n = (n << 3) + (n << 1) + (c & 15);
			while (isNumber(c = read()));
			if(neg) return -n;
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
