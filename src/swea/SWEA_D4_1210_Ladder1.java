package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_D4_1210_Ladder1 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int[] dr = {0,0,-1,1};
		int[] dc = {-1,1,0,0};
		
		for(int t = 1; t <= 10; t++) {
			int T = Integer.parseInt(br.readLine());
			int[][] map = new int[100][100];
			int r = 0, c = 0;
			
			for(int i = 0; i < 100; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 2) {
						r = i;
						c = j;
					}
				}
			}
			
			boolean[][] visited = new boolean[100][100];
			visited[r][c] = true;
			
			while(true) {
				if(r == 0) {
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(0 <= nr && nr < 100 && 0 <= nc && nc < 100 && visited[nr][nc] == false && map[nr][nc] == 1) {
						r = nr;
						c = nc;
						visited[r][c] = true;
						break;
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", T, c));
		}
		
		System.out.println(sb);
	}
}
