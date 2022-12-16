package boj.g2;

public class G2_20061_모노미노도미노_2 {
	static Reader in = new Reader();
	static int[][][] boards = new int[2][6][4];
	static int N, score, no = 1;
	
	public static void main(String[] args) throws Exception {
		N = in.nextInt();
		
		while(N-- > 0) {
			int t = in.nextInt();
			int x = in.nextInt();
			int y = in.nextInt();
			
			putBlock(boards[0], t, y);
			putBlock(boards[1], t > 1 ? 5-t : t, t == 3 ? 2-x : 3-x);
			
			no++;
		}
		
		System.out.println(score);
		System.out.println(countBlocks());
	}
	
	static void putBlock(int[][] board, int t, int y) {
		if(t == 1 || t == 3) {
			int r = findRow(board, y);
			
			board[r][y] = no;
			if(t == 3) board[r-1][y] = no;
		}
		else {
			int r = Math.min(findRow(board, y), findRow(board, y+1));
			
			board[r][y] = board[r][y+1] = no;
		}
		
		while(true) {
			int r = checkRow(board);
			
			if(r == -1) break;
			
			score++;
			
			for(int i = r; i > 0; i--) {
				moveBlocks(board, i, i-1);
			}
			
			for(int c = 0; c < 4; c++) {
				board[0][c] = 0;
			}
		}
		
		int cnt = checkLight(board);
		
		if(cnt > 0) {
			for(int i = 5; i >= cnt; i--) {
				moveBlocks(board, i, i-cnt);
			}
			
			for(int r = 0; r < 2; r++) {
				for(int c = 0; c < 4; c++) {
					board[r][c] = 0;
				}
			}
		}
	}
	
	static int findRow(int[][] board, int y) {
		for(int r = 0; r < 6; r++) {
			if(board[r][y] != 0) return r-1;
		}
		
		return 5;
	}
	
	static int checkRow(int[][] board) {
		for(int r = 0; r < 6; r++) {
			if(board[r][0] != 0 && board[r][1] != 0 && board[r][2] != 0 && board[r][3] != 0) return r;
		}
		
		return -1;
	}
	
	static int checkLight(int[][] board) {
		int ret = 0;
		
		for(int r = 0; r < 2; r++) {
			if(board[r][0] != 0 || board[r][1] != 0 || board[r][2] != 0 || board[r][3] != 0) ret++;
		}
		
		return ret;
	}
	
	static void moveBlocks(int[][] board, int r1, int r2) {
		for(int c = 0; c < 4; c++) {
			board[r1][c] = board[r2][c];
		}
	}
	
	static int countBlocks() {
		int ret = 0;
		
		for(int c = 0; c < 2; c++) {
			for(int x = 0; x < 6; x++) {
				for(int y = 0; y < 4; y++) {
					if(boards[c][x][y] != 0) ret++;
				}
			}
		}
		
		return ret;
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
