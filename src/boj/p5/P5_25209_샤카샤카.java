package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_25209_샤카샤카 {
	static char[][] temp;
	static int[][] map;
	static boolean[][] checked;
	static int N, M;
	static boolean ans = true;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		checked = new boolean[N][M];
		temp = new char[3*N][];
		
		for(int i = 0; i < 3*N; i++) {
			temp[i] = br.readLine().toCharArray();
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(temp[3*i+1][3*j+1] >= '0') map[i][j] = temp[3*i+1][3*j+1] - '0';
				else if(temp[3*i+1][3*j+1] == '.') map[i][j] = 5;
				else {
					if(temp[3*i][3*j+2] == '.') map[i][j] = 7;
					else if(temp[3*i+2][3*j] == '.') map[i][j] = 8;
					else if(temp[3*i+2][3*j+2] == '.') map[i][j] = 9;
					else if(temp[3*i][3*j] == '.') map[i][j] = 10;
					else map[i][j] = 6;
				}
			}
		}
		
		outer: for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] <= 4) {
					checkNums(r, c);
				}
				else if(map[r][c] == 5 && !checked[r][c]) {
					checkRect(r, c);
				}
				else if(map[r][c] == 9 && !checked[r][c]) {
					checkSlanted(r, c);
				}
				else if((map[r][c] == 7 || map[r][c] == 8 || map[r][c] == 10) && !checked[r][c]) ans = false;
				
				if(!ans) break outer;
			}
		}
		
		System.out.println(ans ? "YES" : "NO");
	}
	
	static void checkNums(int r, int c) {
		int cnt = 0;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && map[nr][nc] >= 7) cnt++;
		}
		
		if(cnt != map[r][c]) ans = false;
	}
	
	static void checkRect(int r, int c) {
		int R = 1, C = 1;
		
		for(int i = r+1; i < N; i++) {
			if(map[i][c] == 5) R++;
			else break;
		}
		
		for(int i = c+1; i < M; i++) {
			if(map[r][i] == 5) C++;
			else break;
		}
		
		for(int i = r; i < r+R; i++) {
			for(int j = c; j < c+C; j++) {
				checked[i][j] = true;
				if(map[i][j] != 5) {
					ans = false;
					return;
				}
			}
		}
		
		for(int i = r; i < r+R; i++) {
			if(isIn(i, c-1)) {
				if(temp[3*i][3*c-1] != '#' || temp[3*i+1][3*c-1] != '#' || temp[3*i+2][3*c-1] != '#') {
					ans = false;
					return;
				}
			}
			
			if(isIn(i, c+C)) {
				if(temp[3*i][3*(c+C)] != '#' || temp[3*i+1][3*(c+C)] != '#' || temp[3*i+2][3*(c+C)] != '#') {
					ans = false;
					return;
				}
			}
		}
		
		for(int i = c; i < c+C; i++) {
			if(isIn(r-1, i)) {
				if(temp[3*r-1][3*i] != '#' || temp[3*r-1][3*i+1] != '#' || temp[3*r-1][3*i+2] != '#') {
					ans = false;
					return;
				}
			}
			
			if(isIn(r+R, i)) {
				if(temp[3*(r+R)][3*i] != '#' || temp[3*(r+R)][3*i+1] != '#' || temp[3*(r+R)][3*i+2] != '#') {
					ans = false;
					return;
				}
			}
		}
	}
	
	static void checkSlanted(int r, int c) {
		int L = 1, R = 1;
		
		if(c == M-1 || map[r][c+1] != 8) {
			ans = false;
			return;
		}
		
		checked[r][c] = checked[r][c+1] = true;
		
		while(true) {
			int nr = r + L;
			int nc = c - L;
			
			if(!isIn(nr, nc) || map[nr][nc] != 9) break;
			
			checked[nr][nc] = true;
			L++;
		}
		
		while(true) {
			int nr = r + R;
			int nc = c + 1 + R;
			
			if(!isIn(nr, nc) || map[nr][nc] != 8) break;
			
			checked[nr][nc] = true;
			R++;
		}
		
		int tr = r + L, tc = c - L + 1;
		if(!isIn(tr, tc) || map[tr][tc] != 7) {
			ans = false;
			return;
		}
		checked[tr][tc] = true;
		
		for(int i = 1; i < R; i++) {
			int nr = tr + i;
			int nc = tc + i;
			
			if(!isIn(nr, nc) || map[nr][nc] != 7) {
				ans = false;
				return;
			}
			
			checked[nr][nc] = true;
		}
		
		tr = r + R; tc = c + R;
		if(!isIn(tr, tc) || map[tr][tc] != 10) {
			ans = false;
			return;
		}
		checked[tr][tc] = true;
		
		for(int i = 1; i < L; i++) {
			int nr = tr + i;
			int nc = tc - i;
			
			if(!isIn(nr, nc) || map[nr][nc] != 10) {
				ans = false;
				return;
			}
			
			checked[nr][nc] = true;
		}
		
		for(int i = 0; i < L; i++) {
			int nr = r + i;
			int nc = c - i;
			
			while(true) {
				nr++;
				
				if(!isIn(nr, nc)) {
					ans = false;
					return;
				}
				
				if(checked[nr][nc]) break;
				
				if(map[nr][nc] == 5) checked[nr][nc] = true;
				else {
					ans = false;
					return;
				}
			}
		}
		
		for(int i = 0; i < R; i++) {
			int nr = r + i;
			int nc = c + 1 + i;
			
			while(true) {
				nr++;
				
				if(!isIn(nr, nc)) {
					ans = false;
					return;
				}
				
				if(checked[nr][nc]) break;
				
				if(map[nr][nc] == 5) checked[nr][nc] = true;
				else {
					ans = false;
					return;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
