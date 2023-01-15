package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_09944_NxM_보드_완주하기 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, count, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		String line = null;
		int tc = 0;
		
		while((line = br.readLine()) != null) {
			ans = Integer.MAX_VALUE;
			count = 0;
			tc++;
			
			st = new StringTokenizer(line);
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N][M];
			visited = new boolean[N][M];
			
			for(int r = 0; r < N; r++) {
				line = br.readLine();
				for(int c = 0; c < M; c++) {
					map[r][c] = line.charAt(c);
					if(map[r][c] == '.') count++;
				}
			}
			
			if(count == 1) {
				sb.append(String.format("Case %d: 0\n", tc));
				continue;
			}
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < M; c++) {
					if(map[r][c] == '.') {
						for(int i = 0; i < 4; i++) {
							int nr = r + dr[i];
							int nc = c + dc[i];
							
							if(isIn(nr, nc) && map[nr][nc] == '.') {
								visited[r][c] = true;
								dfs(r, c, i, 1, 1);
								visited[r][c] = false;
							}
						}
					}
				}
			}
			
			if(ans == Integer.MAX_VALUE) ans = -1;
			sb.append(String.format("Case %d: %d\n", tc, ans));
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int r, int c, int d, int cnt, int t) {
		if(t > ans) return;
		
		if(cnt == count) {
			ans = Math.min(ans, t);
			return;
		}
		
		int nr = r + dr[d];
		int nc = c + dc[d];
		
		if(isIn(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
			visited[nr][nc] = true;
			dfs(nr, nc, d, cnt+1, t);
			visited[nr][nc] = false;
		}
		else {
			for(int i = 0; i < 4; i++) {
				if(i == d) continue;
				nr = r + dr[i];
				nc = c + dc[i];
				
				if(isIn(nr, nc) && map[nr][nc] == '.' && !visited[nr][nc]) {
					visited[nr][nc] = true;
					dfs(nr, nc, i, cnt+1, t+1);
					visited[nr][nc] = false;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
