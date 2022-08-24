package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_03055_탈출 {
	static Queue<int[]> queue = new LinkedList<>();
	static char[][] map;
	static boolean[][] visited, water;
	static int R, C, ans = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		water = new boolean[R][C];
		
		int sr = -1, sc = -1;
		
		for(int r = 0; r < R; r++) {
			String line = br.readLine();
			
			for(int c = 0; c < C; c++) {
				map[r][c] = line.charAt(c);
				
				if(map[r][c] == 'S') {
					sr = r; sc = c;
					map[r][c] = '.';
				}
				else if(map[r][c] == '*') {
					queue.add(new int[] {1, r, c});
					water[r][c] = true;
				}
			}
		}
		
		bfs(sr, sc);
		
		System.out.println(ans == -1 ? "KAKTUS" : ans);
	}
	
	static void bfs(int sr, int sc) {
		queue.add(new int[] {0, sr, sc, 0});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int type = cur[0];
			int r = cur[1];
			int c = cur[2];
			
			if(type == 0 && map[r][c] == 'D') {
				ans = cur[3];
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(type == 0 && isIn(nr, nc) && !visited[nr][nc] && !water[nr][nc] && map[nr][nc] != 'X') {
					queue.add(new int[] {0, nr, nc, cur[3]+1});
					visited[nr][nc] = true;
				}
				else if(type == 1 && isIn(nr, nc) && !water[nr][nc] && map[nr][nc] != 'X' && map[nr][nc] != 'D') {
					queue.add(new int[] {1, nr, nc});
					water[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
