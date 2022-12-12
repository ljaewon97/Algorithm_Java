package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_25416_빠른_숫자_탐색 {
	static int[][] map = new int[5][5];
	static boolean[][] visited = new boolean[5][5];
	static int sr, sc, er, ec, ans = -1;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					er = i;
					ec = j;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		
		bfs();
		
		System.out.println(ans);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.r == er && p.c == ec) {
				ans = p.d;
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != -1) {
					queue.add(new Point(nr, nc, p.d+1));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < 5 && 0 <= c && c < 5;
	}
	
	static class Point {
		int r, c, d;
		
		public Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
