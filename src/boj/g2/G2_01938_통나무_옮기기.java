package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class G2_01938_통나무_옮기기 {
	static char[][] map;
	static boolean[][] visitedH, visitedV , rotated;
	static int N, sr, sc, er, ec, dir, endDir, ans = 0;
	static int[] dr = {0,0,-1,1,-1,-1,1,1};
	static int[] dc = {-1,1,0,0,-1,1,-1,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visitedH = new boolean[N][N];
		visitedV = new boolean[N][N];
		rotated = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == 'B') {
					if(isIn(i+1, j) && map[i+1][j] == 'B' && isIn(i-1, j) && map[i-1][j] == 'B') {
						sr = i;
						sc = j;
						dir = 1;
						map[i+1][j] = '0'; map[i][j] = '0'; map[i-1][j] = '0';
					}
					else if(isIn(i, j+1) && map[i][j+1] == 'B' && isIn(i, j-1) && map[i][j-1] == 'B') {
						sr = i;
						sc = j;
						dir = 0;
						map[i][j+1] = '0'; map[i][j] = '0'; map[i][j-1] = '0';
					}
				}
				
				if(map[i][j] == 'E') {
					if(isIn(i+1, j) && map[i+1][j] == 'E' && isIn(i-1, j) && map[i-1][j] == 'E') {
						er = i;
						ec = j;
						endDir = 1;
						map[i+1][j] = '0'; map[i][j] = '0'; map[i-1][j] = '0';
					}
					else if(isIn(i, j+1) && map[i][j+1] == 'E' && isIn(i, j-1) && map[i][j-1] == 'E') {
						er = i;
						ec = j;
						endDir = 0;
						map[i][j+1] = '0'; map[i][j] = '0'; map[i][j-1] = '0';
					}
				}
			}
		}

		bfs(sr, sc);
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Deque<int[]> deque = new LinkedList<>();
		deque.add(new int[] {sr, sc, 0, dir});
		if(dir == 0) {
			visitedH[sr][sc] = true;
		}
		else {
			visitedV[sr][sc] = true;
		}
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int cdir = cur[3];
			
			if(r == er && c == ec && cdir == endDir) {
				ans = d;
				break;
			}
			
			if(canRotate(r, c) && !rotated[r][c]) {
				deque.add(new int[] {r, c, d+1, 1-cdir});
				rotated[r][c] = true;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(cdir == 0) {
					if(isIn(nr, nc) && !visitedH[nr][nc] && map[nr][nc] == '0') {
						if(canMove(nr, nc, cdir)) {
							deque.add(new int[] {nr, nc, d+1, cdir});
							visitedH[nr][nc] = true;
						}
					}
				}
				else {
					if(isIn(nr, nc) && !visitedV[nr][nc] && map[nr][nc] == '0') {
						if(canMove(nr, nc, cdir)) {
							deque.add(new int[] {nr, nc, d+1, cdir});
							visitedV[nr][nc] = true;
						}
					}
				}
			}
		}
	}
	
	static boolean canMove(int r, int c, int cdir) {
		for(int i = 0; i < 2; i++) {
			int nr = r + dr[2*cdir+i];
			int nc = c + dc[2*cdir+i];
			
			if(!(isIn(nr, nc) && map[nr][nc] == '0')) {
				return false;
			}
		}
		return true;
	}
	
	static boolean canRotate(int r, int c) {
		for(int i = 0; i < 8; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr, nc) || (isIn(nr, nc) && map[nr][nc] == '1')) {
				return false;
			}
		}
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
