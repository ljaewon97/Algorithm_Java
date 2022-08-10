package boj.p5;

public class P5_17470_배열_돌리기_5 {
	static Reader in = new Reader();
	static int[][] arr, simple, ans;
	static int[] state = new int[3];
	static int N, M, R;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		
		int[][] input = new int[N][M];
		simple = new int[][] {{0,1},{3,2}};
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				input[i][j] = in.nextInt();
			}
		}
		
		int hn = N / 2;
		int hm = M / 2;
		int[][][] part = new int[4][hn][hm];
		
		for(int i = 0; i < hn; i++) {
			for(int j = 0; j < hm; j++) {
				part[0][i][j] = input[i][j];
				part[1][i][j] = input[i][j+hm];
				part[2][i][j] = input[i+hn][j+hm];
				part[3][i][j] = input[i+hn][j];
				
			}
		}
		
		for(int i = 0; i < R; i++) {
			int x = in.nextInt();
			
			switch(x) {
			case 1: simple1(); break;
			case 2: simple2(); break;
			case 3: simple3(); break;
			case 4: simple4(); break;
			case 5: simple5(); break;
			case 6: simple6(); break;
			}
		}
		
		state[0] %= 2;
		state[1] %= 2;
		state[2] %= 4;
		
		if(state[2] % 2 == 1) {
			ans = new int[M][N];
		}
		else {
			ans = new int[N][M];
		}
		
		int R = ans.length;
		int C = ans[0].length;
		int hr = R / 2;
		int hc = C / 2;
		
		for(int p = 0; p < 2; p++) {
			for(int q = 0; q < 2; q++) {
				arr = part[simple[p][q]];
				
				for(int i = 0; i < state[2]; i++) {
					arr = rotateCW();
				}
				
				for(int i = 0; i < state[0]; i++) {
					upDown();
				}
				
				for(int i = 0; i < state[1]; i++) {
					leftRight();
				}
						
				for(int r = 0; r < hr; r++) {
					for(int c = 0; c < hc; c++) {
						ans[r+hr*p][c+hc*q] = arr[r][c];
					}
				}
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void simple1() {
		int temp = simple[0][0];
		simple[0][0] = simple[1][0];
		simple[1][0] = temp;
		
		temp = simple[0][1];
		simple[0][1] = simple[1][1];
		simple[1][1] = temp;
		
		state[0]++;
	}
	
	static void simple2() {
		int temp = simple[0][0];
		simple[0][0] = simple[0][1];
		simple[0][1] = temp;
		
		temp = simple[1][0];
		simple[1][0] = simple[1][1];
		simple[1][1] = temp;
		
		state[1]++;
	}
	
	static void simple3() {
		simple5();
		
		if((state[0] % 2 == 1 && state[1] % 2 == 1) || (state[0] % 2 == 0 && state[1] % 2 == 0)) {
			state[2]++;
		}
		else {
			state[2] += 3;
		}
	}
	
	static void simple4() {
		simple6();
		
		if((state[0] % 2 == 1 && state[1] % 2 == 1) || (state[0] % 2 == 0 && state[1] % 2 == 0)) {
			state[2] += 3;
		}
		else {
			state[2]++;
		}
	}
	
	static void simple5() {
		int temp = simple[0][0];
		simple[0][0] = simple[1][0];
		simple[1][0] = simple[1][1];
		simple[1][1] = simple[0][1];
		simple[0][1] = temp;
	}
	
	static void simple6() {
		int temp = simple[0][0];
		simple[0][0] = simple[0][1];
		simple[0][1] = simple[1][1];
		simple[1][1] = simple[1][0];
		simple[1][0] = temp;
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
