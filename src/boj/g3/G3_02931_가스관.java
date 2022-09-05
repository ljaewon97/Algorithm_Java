package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G3_02931_가스관 {
	static char[][] map;
	static boolean up, down, left, right;
	static int R, C;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		outer: for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '.' && check(r, c)) {
					int ansr = r + 1;
					int ansc = c + 1;
					sb.append(ansr + " " + ansc + " ");
					
					if(up && down && left && right) sb.append("+");
					else if(up && down) sb.append("|");
					else if(left && right) sb.append("-");
					else if(down && right) sb.append("1");
					else if(up && right) sb.append("2");
					else if(up && left) sb.append("3");
					else if(down && left) sb.append("4");
					
					break outer;
				}
			}
		}
		
		System.out.println(sb);
	}
		
	static boolean check(int r, int c) {
		int cnt = 0;
		
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isIn(nr, nc)) continue;
			
			if(i == 0) {
				if(map[nr][nc] == '|' || map[nr][nc] == '+' || map[nr][nc] == '1' || map[nr][nc] == '4') cnt++;
			}
			else if(i == 1) {
				if(map[nr][nc] == '|' || map[nr][nc] == '+' || map[nr][nc] == '2' || map[nr][nc] == '3') cnt++;
			}
			else if(i == 2) {
				if(map[nr][nc] == '-' || map[nr][nc] == '+' || map[nr][nc] == '1' || map[nr][nc] == '2') cnt++;
			}
			else if(i == 3) {
				if(map[nr][nc] == '-' || map[nr][nc] == '+' || map[nr][nc] == '3' || map[nr][nc] == '4') cnt++;
			}
		}
		
		if(cnt == 0) return false;
		else {
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				if(i == 0) {
					if(map[nr][nc] == '|' || map[nr][nc] == '+' || map[nr][nc] == '1' || map[nr][nc] == '4') up = true;
				}
				else if(i == 1) {
					if(map[nr][nc] == '|' || map[nr][nc] == '+' || map[nr][nc] == '2' || map[nr][nc] == '3') down = true;
				}
				else if(i == 2) {
					if(map[nr][nc] == '-' || map[nr][nc] == '+' || map[nr][nc] == '1' || map[nr][nc] == '2') left = true;
				}
				else if(i == 3) {
					if(map[nr][nc] == '-' || map[nr][nc] == '+' || map[nr][nc] == '3' || map[nr][nc] == '4') right = true;
				}
			}
			
			return true;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
