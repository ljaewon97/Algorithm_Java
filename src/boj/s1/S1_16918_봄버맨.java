package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_16918_봄버맨 {
	static char[][] map;
	static boolean[][] exploded;
	static int R, C, N;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		if(N == 1) {
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					sb.append(map[r][c]);
				}
				sb.append("\n");
			}
		}
		else if(N % 2 == 0) {
			StringBuilder line = new StringBuilder();
			
			for(int c = 0; c < C; c++) {
				line.append("O");
			}
			line.append("\n");
			
			for(int r = 0; r < R; r++) {
				sb.append(line);
			}
		}
		else if(N % 4 == 1) {
			explode();
			
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					map[r][c] = exploded[r][c] ? '.' : 'O';
				}
			}
			
			explode();
			
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					sb.append(exploded[r][c] ? "." : "O");
				}
				sb.append("\n");
			}
		}
		else {
			explode();
			
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					sb.append(exploded[r][c] ? "." : "O");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void explode() {
		exploded = new boolean[R][C];
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == 'O') {
					exploded[r][c] = true;
					
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc) && map[nr][nc] == '.') exploded[nr][nc] = true;
					}
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
