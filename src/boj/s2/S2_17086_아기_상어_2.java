package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S2_17086_아기_상어_2 {
	static Queue<Point> queue;
	static boolean[][] map, visited;
	static int[][] dist;
	static int N, M;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		queue = new LinkedList<>();
		map = new boolean[N][M];
		visited = new boolean[N][M];
		dist = new int[N][M];
		
		for(int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
				if(map[i][j]) queue.add(new Point(i, j, 0));
			}
		}
		
		bfs();
		
		int max = 0;
		
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				max = Math.max(max, dist[i][j]);
			}
		}
		
		System.out.println(max);
	}
	
	static void bfs() {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			dist[p.r][p.c] = p.d;
			
			for(int i = 0; i < 8; ++i) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && !map[nr][nc]) {
					queue.add(new Point(nr, nc, p.d+1));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
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
