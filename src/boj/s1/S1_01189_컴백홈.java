package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_01189_컴백홈 {
	static char[][] map;
	static int R, C, K, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		dfs(R-1, 0, 1 << ((R-1)*C), 1);
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c, int bit, int d) {
		if(r == 0 && c == C-1) {
			if(d == K) ans++;
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr, nc) || map[nr][nc] == 'T' || (bit & (1 << (nr*C+nc))) != 0) continue;
			
			dfs(nr, nc, bit | (1 << (nr*C+nc)), d+1);
		}
	}
	
	static boolean isIn(int r , int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
