package boj.g3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G3_02638_치즈 {
	static Queue<Point> border = new LinkedList<>();
	static Queue<Point> temp = new LinkedList<>();
	static int[][] map;
	static boolean[][] outAir;
	static int N, M, cheese, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		outAir = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) cheese++;
			}
		}
		
		init();
		
		while(true) {
			melt();
			expandOutAir();
			ans++;
			if(cheese == 0) break;
		}
		
		System.out.println(ans);
	}
	
	static void init() {
		Queue<Point> queue = new LinkedList<>();
		
		for(int r = 1; r < N-1; r++) {
			queue.add(new Point(r, 0));
			outAir[r][0] = true;
			queue.add(new Point(r, M-1));
			outAir[r][M-1] = true;
		}
		
		for(int c = 1; c < M-1; c++) {
			queue.add(new Point(0, c));
			outAir[0][c] = true;
			queue.add(new Point(N-1, c));
			outAir[N-1][c] = true;
		}
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || outAir[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					queue.add(new Point(nr, nc));
					outAir[nr][nc] = true;
				}
				else {
					outAir[nr][nc] = true;
					border.add(new Point(nr, nc));
				}
			}
		}
	}
	
	static void melt() {
		int size = border.size();
		Queue<Point> pend = new LinkedList<>();
		
		while(size-- > 0) {
			Point p = border.poll();
			int cnt = 0;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && outAir[nr][nc] && map[nr][nc] == 0) cnt++;
			}
			
			if(cnt >= 2) {
				pend.add(p);
				
				for(int i = 0; i < 4; i++) {
					int nr = p.r + dr[i];
					int nc = p.c + dc[i];
					
					if(isIn(nr, nc) && !outAir[nr][nc] && map[nr][nc] == 1) {
						border.add(new Point(nr, nc));
						outAir[nr][nc] = true;
					}
				}
			}
			else {
				border.add(p);
			}
		}
		
		while(!pend.isEmpty()) {
			Point p = pend.poll();
			
			map[p.r][p.c] = 0;
			cheese--;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !outAir[nr][nc] && map[nr][nc] == 0) {
					temp.add(new Point(nr, nc));
					outAir[nr][nc] = true;
				}
			}
		}
	}
	
	static void expandOutAir() {
		while(!temp.isEmpty()) {
			Point p = temp.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || outAir[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					temp.add(new Point(nr, nc));
					outAir[nr][nc] = true;
				}
				else {
					outAir[nr][nc] = true;
					border.add(new Point(nr, nc));
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
