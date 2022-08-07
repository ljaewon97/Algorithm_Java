package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class G4_11559_Puyo_Puyo {
	static char[][] map = new char[12][];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int ans = 0;
		
		for(int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray(); 
		}
		
		while(true) {
			int exp = 0;
			
			for(int i = 11; i >= 0; i--) {
				for(int j = 0; j < 6; j++) {
					if(map[i][j] != '.') {
						exp += explode(i, j);
					}
				}
			}
			
			if(exp > 0) {
				ans++;
				fall();
			}
			else {
				break;
			}
		}
		
		System.out.println(ans);
	}
	
	static int explode(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		Deque<int[]> pend = new LinkedList<>();
		boolean[][] visited = new boolean[12][6];
		int cnt = 1;
		
		deque.add(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
					pend.add(new int[] {nr, nc});
					cnt++;
				}
			}
		}
		
		if(cnt >= 4) {
			map[sr][sc] = '.';
			
			while(!pend.isEmpty()) {
				int[] cur = pend.poll();
				map[cur[0]][cur[1]] = '.';
			}
			
			return 1;
		}
		
		pend.clear();
		return 0;
	}
	
	static void fall() {
		for(int c = 0; c < 6; c++) {
			for(int r = 1; r < 11; r++) {
				for(int i = 11; i >= r; i--) {
					if(map[i][c] == '.' && map[i-1][c] != '.') {
						map[i][c] = map[i-1][c];
						map[i-1][c] = '.';
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 12 && 0 <= c && c < 6;
	}
}