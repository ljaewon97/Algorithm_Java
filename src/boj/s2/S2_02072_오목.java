package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_02072_오목 {
	static int[][] board = new int[20][20];
	static int[] dr = {-1,-1,0,1};
	static int[] dc = {0,1,1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] locs = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			locs[i][0] = Integer.parseInt(st.nextToken());
			locs[i][1] = Integer.parseInt(st.nextToken());
		}
		
		int ans = -1;
		
		for(int i = 1; i <= N; i++) {
			int r = locs[i][0];
			int c = locs[i][1];
			int t = i%2 + 1;
			
			board[r][c] = t;
			boolean fin = false;
			
			for(int j = 0; j < 4; j++) {
				int cnt = 1;
				
				int step = 1;
				while(true) {
					int nr = r + dr[j] * step;
					int nc = c + dc[j] * step;
					
					if(!isIn(nr, nc) || board[nr][nc] != t) break;
					
					step++;
					cnt++;
				}
				
				step = 1;
				while(true) {
					int nr = r - dr[j] * step;
					int nc = c - dc[j] * step;
					
					if(!isIn(nr, nc) || board[nr][nc] != t) break;
					
					step++;
					cnt++;
				}
				
				if(cnt == 5) {
					fin = true;
					break;
				}
			}
			
			if(fin) {
				ans = i;
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= 19 && 1 <= c && c <= 19;
	}
}
