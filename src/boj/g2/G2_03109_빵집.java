package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G2_03109_빵집 {
	static char[][] map;
	static int R, C, ans;
	static boolean arrived;
	static boolean[][] visited;
	static int[] dr = {1,0,-1};
	static int[] dc = {1,1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = R-1; i >= 0; i--) {
			arrived = false;
			dfs(i, 0);
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		if(arrived) return;
		
		if(c == C-1) {
			ans++;
			arrived = true;
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			if(arrived) return;
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 'x') {
				dfs(nr, nc);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
