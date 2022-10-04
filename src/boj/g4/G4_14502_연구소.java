package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_14502_연구소 {
	static int[][] map;
	static boolean[][] visited;
	static List<Point> viruses = new ArrayList<>();
	static Point[] cands;
	static int N, M, ans, cnt, temp;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		cands = new Point[N*M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == 2) viruses.add(new Point(i, j));
				else if(map[i][j] == 0) cands[cnt++] = new Point(i, j);
			}
		}
		
		solve(0, 0);
		
		System.out.println(ans);
	}

	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc]) {
				dfs(nr, nc);
				temp++;
			}
		}
	}
	
	static void solve(int nth, int start) {
		if(nth == 3) {
			temp = 0;
			
			visited = new boolean[N][M];
			
			for(Point p: viruses) {
				dfs(p.r, p.c);
			}
			
			ans = Math.max(ans, cnt-3-temp);
			return;
		}
		
		for(int i = start; i < cnt; i++) {
			map[cands[i].r][cands[i].c] = 1;
			solve(nth+1, i+1);
			map[cands[i].r][cands[i].c] = 0;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
