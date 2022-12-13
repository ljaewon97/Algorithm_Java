package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_21938_영상처리 {
	static int[][] color;
	static boolean[][] visited;
	static int N, M, T;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		color = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				color[i][j] = (Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())) / 3;
			}
		}
		
		T = Integer.parseInt(br.readLine());
		
		int ans = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(!visited[r][c] && color[r][c] >= T) {
					bfs(r, c);
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int r, int c) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && color[nr][nc] >= T) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
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
