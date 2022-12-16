package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_21736_헌내기는_친구가_필요해 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 605);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		visited = new boolean[N][M];
		
		int sr = -1, sc = -1;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == 'I') {
					sr = r;
					sc = c;
				}
			}
		}
		
		dfs(sr, sc);
		
		System.out.println(ans == 0 ? "TT" : ans);
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		if(map[r][c] == 'P') ans++;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'X') {
				dfs(nr, nc);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
