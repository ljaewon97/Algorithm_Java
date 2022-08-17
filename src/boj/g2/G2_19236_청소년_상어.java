package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class G2_19236_청소년_상어 {
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,-1,-1,-1,0,1,1,1};
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][][] map = new int[4][4][2];
		
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				map[i][j][0] = Integer.parseInt(st.nextToken());
				map[i][j][1] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		
		int first = map[0][0][0];
		map[0][0][0] = 20;
				
		move(map, first);
		
		System.out.println(ans);
	}
	
	static void move(int[][][] map, int score) {
		int[][][] temp = fishMove(map);
		List<int[]> cand = search(temp);
		
		if(cand.isEmpty()) {
			ans = Math.max(ans, score);
			return;
		}
		
		for(int[] fish: cand) {
			int s = temp[fish[0]][fish[1]][0];
			move(eat(temp, fish[0], fish[1]), score + s);
		}
	}
	
	static int[][][] eat(int[][][] map, int r, int c) {
		int[][][] res = new int[4][4][2];
		int sr = -1, sc = -1;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				res[i][j][0] = map[i][j][0];
				res[i][j][1] = map[i][j][1];
				
				if(res[i][j][0] == 20) {
					sr = i;
					sc = j;
				}
			}
		}
		
		res[sr][sc][0] = 0;
		res[sr][sc][1] = 0;
		res[r][c][0] = 20;
		
		return res;
	}
	
	static List<int[]> search(int[][][] map) {
		List<int[]> cand = new ArrayList<>();
		int r = -1, c = -1;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(map[i][j][0] == 20) {
					r = i;
					c = j;
					break;
				}
			}
		}
		
		int d = map[r][c][1], step = 1;
		
		while(true) {
			int nr = r + dr[d] * step;
			int nc = c + dc[d] * step;
			
			if(!isIn(nr, nc)) {
				break;
			}
			
			if(map[nr][nc][0] != 0) {
				cand.add(new int[] {nr, nc});
			}
			
			step++;
		}
		
		return cand;
	}
	
	static int[][][] fishMove(int[][][] map) {
		int[][][] res = new int[4][4][2];
		int cur = 1;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				res[i][j][0] = map[i][j][0];
				res[i][j][1] = map[i][j][1];
			}
		}
		
		while(cur <= 16) {
			int r = -1, c = -1;
			
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 4; j++) {
					if(res[i][j][0] == cur) {
						r = i;
						c = j;
						break;
					}
				}
			}
			
			if(r == -1 && c == -1) {
				cur++;
				continue;
			}
			
			int d = res[r][c][1];
			
			for(int i = 0; i < 8; i++) {
				int nd = (d + i) % 8;
				int nr = r + dr[nd];
				int nc = c + dc[nd];
				
				if(isIn(nr, nc) && res[nr][nc][0] <= 16) {
					res[r][c][1] = nd;
					
					int temp = res[nr][nc][0];
					res[nr][nc][0] = res[r][c][0];
					res[r][c][0] = temp;
					
					temp = res[nr][nc][1];
					res[nr][nc][1] = res[r][c][1];
					res[r][c][1] = temp;
					
					break;
				}
			}
			
			cur++;
		}
		
		return res;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 4 && 0 <= c && c < 4;
	}
}