package boj.p4;

public class P4_12094_2048_Hard {
	static Reader in = new Reader();
	static int[][] board;
	static int N, maxMove, ans;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		maxMove = 10;
		
		board = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = in.nextInt();
				if(board[i][j] > ans) {
					ans = board[i][j];
				}
			}
		}
		
		move(0, board);
		
		System.out.println(ans);
	}
	
	static void move(int nth, int[][] arr) {
		if(nth == maxMove) {
			return;
		}
		
		move(nth+1, moveLeft(arr));
		move(nth+1, moveRight(arr));
		move(nth+1, moveUp(arr));
		move(nth+1, moveDown(arr));
	}
	
	static int[][] moveLeft(int[][] arr) {
		int[][] temp = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			int idx = 0, prev = -1;
			
			for(int c = 0; c < N; c++) {
				if(arr[r][c] == 0) continue;
				
				if(arr[r][c] == prev) {
					if(prev == ans) ans *= 2;
					temp[r][idx-1] *= 2;
					prev = -1;
				}
				else {
					temp[r][idx++] = arr[r][c];
					prev = arr[r][c];
				}
			}
		}
		
		return temp;
	}
	
	static int[][] moveRight(int[][] arr) {
		int[][] temp = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			int idx = N-1, prev = -1;
			
			for(int c = N-1; c >= 0; c--) {
				if(arr[r][c] == 0) continue;
				
				if(arr[r][c] == prev) {
					if(prev == ans) ans *= 2;
					temp[r][idx+1] *= 2;
					prev = -1;
				}
				else {
					temp[r][idx--] = arr[r][c];
					prev = arr[r][c];
				}
			}
		}
		
		return temp;
	}
	
	static int[][] moveDown(int[][] arr) {
		int[][] temp = new int[N][N];
		
		for(int c = 0; c < N; c++) {
			int idx = N-1, prev = -1;
			
			for(int r = N-1; r >= 0; r--) {
				if(arr[r][c] == 0) continue;
				
				if(arr[r][c] == prev) {
					if(prev == ans) ans *= 2;
					temp[idx+1][c] *= 2;
					prev = -1;
				}
				else {
					temp[idx--][c] = arr[r][c];
					prev = arr[r][c];
				}
			}
		}
		
		return temp;
	}
	
	static int[][] moveUp(int[][] arr) {
		int[][] temp = new int[N][N];
		
		for(int c = 0; c < N; c++) {
			int idx = 0, prev = -1;
			
			for(int r = 0; r < N; r++) {
				if(arr[r][c] == 0) continue;
				
				if(arr[r][c] == prev) {
					if(prev == ans) ans *= 2;
					temp[idx-1][c] *= 2;
					prev = -1;
				}
				else {
					temp[idx++][c] = arr[r][c];
					prev = arr[r][c];
				}
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
