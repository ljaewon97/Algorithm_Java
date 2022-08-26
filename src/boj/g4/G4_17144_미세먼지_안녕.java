package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_17144_미세먼지_안녕 {
	static int[][] map, temp;
	static int R, C, T, heater;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		temp = new int[R][C];
		
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if(map[i][j] == -1) heater = i;
			}
		}
		
		while(T-- > 0) {
			spread();
			heaterOn();
		}
		
		int ans = 2;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				ans += map[r][c];
			}
		}
		
		System.out.println(ans);
	}
	
	static void spread() {
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] > 0) {
					int amount = map[r][c] / 5;
					int cnt = 0;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc) && map[nr][nc] != -1) {
							cnt++;
							temp[nr][nc] += amount;
						}
					}
					
					temp[r][c] -= amount * cnt;
				}
			}
		}
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				map[r][c] += temp[r][c];
				temp[r][c] = 0;
			}
		}
	}
	
	static void heaterOn() {
		int up = heater - 1;
		int down = heater;
		
		for(int r = up-1; r > 0; r--) {
			map[r][0] = map[r-1][0];
		}
		for(int c = 0; c < C-1; c++) {
			map[0][c] = map[0][c+1];
		}
		for(int r = 0; r < up; r++) {
			map[r][C-1] = map[r+1][C-1];
		}
		for(int c = C-1; c > 1; c--) {
			map[up][c] = map[up][c-1];
		}
		map[up][1] = 0;
		
		for(int r = down+1; r < R-1; r++) {
			map[r][0] = map[r+1][0];
		}
		for(int c = 0; c < C-1; c++) {
			map[R-1][c] = map[R-1][c+1];
		}
		for(int r = R-1; r > down; r--) {
			map[r][C-1] = map[r-1][C-1];
		}
		for(int c = C-1; c > 1; c--) {
			map[down][c] = map[down][c-1];
		}
		map[down][1] = 0;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
