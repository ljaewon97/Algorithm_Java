package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class G5_10026_적록색약 {
	static char[][] map;
	static boolean[][] visited, visited2;
	static int N, ans, ans2;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][];
		visited = new boolean[N][N];
		visited2 = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(!visited[i][j]) {
					bfs(i, j);
					ans++;
				}
				if(!visited2[i][j]) {
					bfs2(i, j);
					ans2++;
				}
			}
		}
		
		System.out.println(ans + " " + ans2);
	}
	
	static void bfs(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
					visited[nr][nc] = true;
					queue.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	static void bfs2(int sr, int sc) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {sr, sc});
		visited2[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(map[r][c] == 'R' || map[r][c] == 'G') {
					if(isIn(nr, nc) && !visited2[nr][nc] && (map[nr][nc] == 'R' || map[nr][nc] == 'G')) {
						visited2[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}
				else {
					if(isIn(nr, nc) && !visited2[nr][nc] && map[nr][nc] == map[r][c]) {
						visited2[nr][nc] = true;
						queue.add(new int[] {nr, nc});
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
