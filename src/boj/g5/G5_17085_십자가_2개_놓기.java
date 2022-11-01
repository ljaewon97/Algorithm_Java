package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G5_17085_십자가_2개_놓기 {
	static List<Cross> list = new ArrayList<>();
	static Cross[] choosed = new Cross[2];
	static char[][] map;
	static int N, M, ans = 1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		int spaceCnt = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == '#') {
					spaceCnt++;
					int min = 100;
					
					for(int i = 0; i < 4; i++) {
						int temp = 0;
						int nr = r;
						int nc = c;
						
						while(true) {
							nr += dr[i];
							nc += dc[i];
							
							if(!isIn(nr, nc) || map[nr][nc] == '.') break;
							temp++;
						}
						
						min = Math.min(min, temp);
					}
					
					if(min > 0) list.add(new Cross(r, c, min));
				}
			}
		}
		
		if(list.size() == 0) {
			System.out.println(1);
			return;
		}
		else if(list.size() == 1) {
			int area = 4*list.get(0).len + 1;
			
			if(area == spaceCnt) {
				System.out.println(area-4);
			}
			else {
				System.out.println(area);
			}
			
			return;
		}
		
		comb(0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int nth, int start) {
		if(nth == 2) {
			Cross cross1 = choosed[0];
			Cross cross2 = choosed[1];
			
			for(int l1 = cross1.len; l1 >= 0; l1--) {
				for(int l2 = cross2.len; l2 >= 0; l2--) {
					if(notCross(cross1.r, cross1.c, l1, cross2.r, cross2.c, l2)) {
						int a1 = 4*l1+1;
						int a2 = 4*l2+1;
						ans = Math.max(ans, a1*a2);
					}
				}
			}
			
			return;
		}
		
		for(int i = start; i < list.size(); i++) {
			choosed[nth] = list.get(i);
			comb(nth+1, i+1);
		}
	}
	
	static boolean notCross(int r1, int c1, int l1, int r2, int c2, int l2) {
		if(l1 == 0 || l2 == 0) return true;
		
		boolean[][] visited = new boolean[N][M];
		visited[r1][c1] = true;
		
		for(int i = 0; i < 4; i++) {
			int nr = r1;
			int nc = c1;
			
			for(int j = 0; j < l1; j++) {
				nr += dr[i];
				nc += dc[i];
				
				visited[nr][nc] = true;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			int nr = r2;
			int nc = c2;
			
			for(int j = 0; j < l2; j++) {
				nr += dr[i];
				nc += dc[i];
				
				if(visited[nr][nc]) return false;
			}
		}
		
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Cross {
		int r, c, len;
		
		public Cross(int r, int c, int len) {
			this.r = r;
			this.c = c;
			this.len = len;
		}
	}
}
