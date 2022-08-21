package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G4_04179_불 {
	static Queue<int[]> queue = new LinkedList<>();
	static char[][] map;
	static boolean[][] visited, fire;
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
		fire = new boolean[R][C];
		
		int jr = -1, jc = -1;
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				
				if(map[i][j] == 'J') {
					jr = i; jc = j;
				}
				else if(map[i][j] == 'F') {
					queue.add(new int[] {1, i, j});
					fire[i][j] = true;
				}
			}
		}
		
		bfs(jr, jc);
		
		System.out.println(ans == -1 ? "IMPOSSIBLE" : ans);
	}
	
	static void bfs(int jr, int jc) {
		queue.add(new int[] {0, jr, jc, 0});
		visited[jr][jc] = true;
		int j = 1;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int type = cur[0];
			int r = cur[1];
			int c = cur[2];
			
			if(j == 0) {
				ans = -1;
				return;
			}
			
			if(type == 0) j--;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(type == 0) {
					if(!isIn(nr, nc)) {
						ans = cur[3] + 1;
						return;
					}
					
					if(map[nr][nc] != '#' && !visited[nr][nc] && !fire[nr][nc]) {
						visited[nr][nc] = true;
						queue.add(new int[] {0, nr, nc, cur[3]+1});
						j++;
					}
				}
				else {
					if(isIn(nr, nc) && map[nr][nc] != '#' && !fire[nr][nc]) {
						fire[nr][nc] = true;
						queue.add(new int[] {1, nr, nc});
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
