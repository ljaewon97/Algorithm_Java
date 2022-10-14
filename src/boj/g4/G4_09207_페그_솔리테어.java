package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_09207_페그_솔리테어 {
	static char[][] board;
	static int ans, minMove;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			board = new char[5][];
			ans = minMove = Integer.MAX_VALUE;
			
			for(int i = 0; i < 5; i++) {
				board[i] = br.readLine().toCharArray();
			}
			
			recur(0);
			
			sb.append(String.format("%d %d\n", ans, minMove));
			
			if(N != 0) br.readLine();
		}
		
		System.out.println(sb);
	}
	
	static void recur(int nth) {
		boolean flag = true;
		int cnt = 0;
		
		for(int r = 0; r < 5; r++) {
			for(int c = 0; c < 9; c++) {
				if(board[r][c] == 'o') {
					cnt++;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(!isIn(nr, nc) || board[nr][nc] == '.' || board[nr][nc] == '#') continue;
						
						int nr2 = nr + dr[i];
						int nc2 = nc + dc[i];
						
						if(isIn(nr2, nc2) && board[nr2][nc2] == '.') {
							flag = false;
							board[r][c] = board[nr][nc] = '.';
							board[nr2][nc2] = 'o';
							recur(nth+1);
							board[r][c] = board[nr][nc] = 'o';
							board[nr2][nc2] = '.';
						}
					}
				}
			}
		}
		
		if(flag) {
			if(cnt < ans) {
				ans = cnt;
				minMove = nth;
			}
			else if(cnt == ans) {
				minMove = Math.min(minMove, nth);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 5 && 0 <= c && c < 9;
	}
}
