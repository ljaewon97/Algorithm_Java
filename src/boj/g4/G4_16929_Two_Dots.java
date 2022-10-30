package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_16929_Two_Dots {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, sr, sc;
	static boolean ans;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		outer: for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				sr = r;
				sc = c;
				visited = new boolean[N][M];
				dfs(r, c, 0);
				if(ans) break outer;
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
			
			if(isIn(nr, nc) && map[nr][nc] == map[r][c]) {
				if(d >= 3 && nr == sr && nc == sc) {
					ans = true;
					return;
				}
				
				if(!visited[nr][nc]) {
					dfs(nr, nc, d+1);
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
