package boj.g3;

public class G3_20327_배열_돌리기_6 {
	static Reader in = new Reader();
	static int[][] arr, state;
	static int N, R;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		N = in.nextInt();
		R = in.nextInt();
		
		arr = new int[1<<N][1<<N];
		state = new int[N+1][3];
		
		for(int i = 0; i < 1<<N; i++) {
			for(int j = 0; j < 1<<N; j++) {
				arr[i][j] = in.nextInt();
			}
		}
		
		while(R-- > 0) {
			int k = in.nextInt();
			int l = in.nextInt();
			
			oper(k, l);
		}
		
		for(int i = N; i >= 1; i--) {
			state[i][0] %= 2;
			state[i][1] %= 2;
			state[i][2] %= 4;
			
			for(int r = 0; r < 1<<N; r += 1<<i) {
				for(int c = 0; c < 1<<N; c += 1<<i) {
					for(int j = 0; j < state[i][2]; j++) {
						rotate(r, c, 1<<i);
					}
					
					if(state[i][0] == 1) upDown(r, c, 1<<i);
					
					if(state[i][1] == 1) leftRight(r, c, 1<<i);
				}
			}
		}
		
		for(int r = 0; r < 1<<N; r++) {
			for(int c = 0; c < 1<<N; c++) {
				sb.append(arr[r][c]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void oper(int k, int l) {
		if(k == 1) {
			for(int i = l; i >= 1; i--) {
				state[i][0]++;
			}
		}
		else if(k == 2) {
			for(int i = l; i >= 1; i--) {
				state[i][1]++;
			}
		}
		else if(k == 3) {
			for(int i = l; i >= 1; i--) {
				if(state[i][0] % 2 == state[i][1] % 2) state[i][2]++;
				else state[i][2] += 3;
			}
		}
		else if(k == 4) {
			for(int i = l; i >= 1; i--) {
				if(state[i][0] % 2 == state[i][1] % 2) state[i][2] += 3;
				else state[i][2]++;
			}
		}
		else if(k == 5) {
			k -= 4;
			for(int i = l+1; i <= N; i++) {
				state[i][0]++;
			}
		}
		else if(k == 6) {
			k -= 4;
			for(int i = l+1; i <= N; i++) {
				state[i][1]++;
			}
		}
		else if(k == 7) {
			k -= 4;
			for(int i = l+1; i <= N; i++) {
				if(state[i][0] % 2 == state[i][1] % 2) state[i][2]++;
				else state[i][2] += 3;
			}
		}
		else {
			k -= 4;
			for(int i = l+1; i <= N; i++) {
				if(state[i][0] % 2 == state[i][1] % 2) state[i][2] += 3;
				else state[i][2]++;
			}
		}
	}
	
	static void upDown(int sr, int sc, int s) {
		int half = s/2;
		
		for(int r = sr; r < sr+half; r++) {
			for(int c = sc; c < sc+s; c++) {
				int swap = arr[r][c];
				arr[r][c] = arr[r+half][c];
				arr[r+half][c] = swap;
			}
		}
	}
	
	static void leftRight(int sr, int sc, int s) {
		int half = s/2;
		
		for(int c = sc; c < sc+half; c++) {
			for(int r = sr; r < sr+s; r++) {
				int swap = arr[r][c];
				arr[r][c] = arr[r][c+half];
				arr[r][c+half] = swap;
			}
		}
	}
	
	static void rotate(int sr, int sc, int s) {
		int half = s/2;
		
		for(int r = sr; r < sr+half; r++) {
			for(int c = sc; c < sc+half; c++) {
				int swap = arr[r][c];
				arr[r][c] = arr[r+half][c];
				arr[r+half][c] = arr[r+half][c+half];
				arr[r+half][c+half] = arr[r][c+half];
				arr[r][c+half] = swap;
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
