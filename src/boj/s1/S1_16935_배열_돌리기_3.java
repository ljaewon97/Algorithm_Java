package boj.s1;

public class S1_16935_배열_돌리기_3 {
	static Reader in = new Reader();
	static int[][] arr;
	static int N, M, R;
	
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
		
		for(int i = 0; i < R; i++) {
			int x = in.nextInt();
			
			switch(x) {
			case 1: upDown(); break;
			case 2: leftRight(); break;
			case 3: arr = rotateCW(); break;
			case 4: arr = rotateCCW(); break;
			case 5: oper5(); break;
			case 6: oper6(); break;
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void upDown() {
		int R = arr.length;
		
		for(int i = 0; i < R/2; i++) {
			int[] temp = arr[i];
			arr[i] = arr[R-1-i];
			arr[R-1-i] = temp;
		}
	}
	
	static void leftRight() {
		int R = arr.length;
		int C = arr[0].length;
		
		for(int c = 0; c < C/2; c++) {
			for(int r = 0; r < R; r++) {
				int temp = arr[r][c];
				arr[r][c] = arr[r][C-1-c];
				arr[r][C-1-c] = temp;
			}
		}
	}
	
	static int[][] rotateCW() {
		int C = arr.length;
		int R = arr[0].length;
		int[][] temp = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				temp[i][j] = arr[C-1-j][i];
			}
		}
		
		return temp;
	}
	
	static int[][] rotateCCW() {
		int C = arr.length;
		int R = arr[0].length;
		int[][] temp = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				temp[i][j] = arr[j][R-1-i];
			}
		}
		
		return temp;
	}
	
	static void oper5() {
		int R = arr.length;
		int C = arr[0].length;
		int hr = R / 2;
		int hc = C / 2;
		
		for(int i = 0; i < hr; i++) {
			for(int j = 0; j < hc; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i+hr][j];
				arr[i+hr][j] = arr[i+hr][j+hc];
				arr[i+hr][j+hc] = arr[i][j+hc];
				arr[i][j+hc] = temp;
			}
		}
	}
	
	static void oper6() {
		int R = arr.length;
		int C = arr[0].length;
		int hr = R / 2;
		int hc = C / 2;
		
		for(int i = 0; i < hr; i++) {
			for(int j = 0; j < hc; j++) {
				int temp = arr[i][j];
				arr[i][j] = arr[i][j+hc];
				arr[i][j+hc] = arr[i+hr][j+hc];
				arr[i+hr][j+hc] = arr[i+hr][j];
				arr[i+hr][j] = temp;
			}
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
