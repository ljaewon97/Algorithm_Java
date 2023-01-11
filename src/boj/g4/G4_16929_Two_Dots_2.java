package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_16929_Two_Dots_2 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, cr, cc;
	static boolean ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		outer: for(int r = 0; r < N-1; r++) {
			for(int c = 0; c < M-1; c++) {
				if(map[r][c] == map[r+1][c] && map[r][c] == map[r][c+1]) {
					cr = r;
					cc = c;
					visited = new boolean[N][M];
					dfs(r, c, 0);
					if(ans) break outer;
				}
			}
		}
		
		System.out.println(ans ? "Yes" : "No");
	}
	
	static void dfs(int r, int c, int d) {
		if(ans) return;
		
		visited[r][c] = true;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr, nc) || map[r][c] != map[nr][nc]) continue;
			
			if(d >= 3 && nr == cr && nc == cc) {
				ans = true;
				return;
			}
			
			if(!visited[nr][nc]) dfs(nr, nc, d+1);
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
