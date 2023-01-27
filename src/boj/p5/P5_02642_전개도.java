package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_02642_전개도 {
	static int[][] map = new int[6][6];
	static int[] side = new int[7];
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 6; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 6; j++) {
				if(map[i][j] != 0) {
					boolean[] path = new boolean[7];
					path[map[i][j]] = true;
					find(path, -1, 0, i, j, map[i][j]);
				}
			}
		}
		
		for(int i = 1; i <= 6; i++) {
			if(side[i] == 0) {
				System.out.println(0);
				return;
			}
		}
		
		System.out.println(side[1]);
	}
	
	static void find(boolean[] path, int dir, int dist, int r, int c, int dice) {
		for(int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isIn(nr, nc) && map[nr][nc] != 0 && !path[map[nr][nc]]) {
				path[map[nr][nc]] = true;
				
				if(dir == -1) find(path, i, 1, nr, nc, dice);
				else if(dir == i && dist == 1 && side[dice] == 0 && side[map[nr][nc]] == 0) {
					side[dice] = map[nr][nc];
					side[map[nr][nc]] = dice;
					find(path, dir, dist+1, nr, nc, dice);
				}
				else find(path, dir, dist, nr, nc, dice);
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 6 && 0 <= c && c < 6;
	}
}
