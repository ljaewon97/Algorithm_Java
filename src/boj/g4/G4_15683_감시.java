package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_15683_감시 {
	static int[][] map;
	static boolean[][] watched;
	static int N, M, wallCnt, ans = Integer.MAX_VALUE;
	static List<int[]> cctvs = new ArrayList<>();
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		watched = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(0 < map[i][j] && map[i][j] < 6) {
					cctvs.add(new int[] {map[i][j], i, j});
				}
				if(map[i][j] == 6) wallCnt++;
			}
		}
		
		recur(0);
		
		System.out.println(ans);
	}
	
	static void recur(int nth) {
		if(nth == cctvs.size()) {
			int cnt = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(!watched[i][j]) cnt++;
				}
			}
			
			cnt -= wallCnt;
			ans = Math.min(ans, cnt);
			
			return;
		}
		
		int[] cur = cctvs.get(nth);
		int type = cur[0];
		int r = cur[1];
		int c = cur[2];
		
		if(type == 1) {
			for(int i = 0; i < 4; i++) {
				List<int[]> temp = cctv(r, c, i);
				recur(nth + 1);
				undo(temp);
			}
		}
		else if(type == 2) {
			for(int i = 0; i < 4; i++) {
				List<int[]> temp1 = cctv(r, c, i);
				List<int[]> temp2 = cctv(r, c, (i+2)%4);
				recur(nth + 1);
				undo(temp1);
				undo(temp2);
			}
		}
		else if(type == 3) {
			for(int i = 0; i < 4; i++) {
				List<int[]> temp1 = cctv(r, c, i);
				List<int[]> temp2 = cctv(r, c, (i+1)%4);
				recur(nth + 1);
				undo(temp1);
				undo(temp2);
			}
		}
		else if(type == 4) {
			for(int i = 0; i < 4; i++) {
				List<int[]> temp1 = cctv(r, c, i);
				List<int[]> temp2 = cctv(r, c, (i+1)%4);
				List<int[]> temp3 = cctv(r, c, (i+2)%4);
				recur(nth + 1);
				undo(temp1);
				undo(temp2);
				undo(temp3);
			}
		}
		else {
			for(int i = 0; i < 4; i++) {
				cctv(r, c, i);
			}
			recur(nth + 1);
		}
	}
	
	static List<int[]> cctv(int r, int c, int dir) {
		int step = 0;
		List<int[]> route = new ArrayList<>();
		
		while(true) {
			int nr = r + dr[dir] * step;
			int nc = c + dc[dir] * step;
			
			if(!isIn(nr, nc) || map[nr][nc] == 6) {
				break;
			}
			
			if(!watched[nr][nc]) {
				watched[nr][nc] = true;
				route.add(new int[] {nr, nc});
			}
			
			step++;
		}
		
		return route;
	}
	
	static void undo(List<int[]> route) {
		for(int[] cur: route) {
			watched[cur[0]][cur[1]] = false; 
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
}
