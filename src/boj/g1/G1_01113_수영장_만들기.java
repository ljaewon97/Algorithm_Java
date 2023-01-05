package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G1_01113_수영장_만들기 {
	static int[][] map, water;
	static boolean[][] visited;
	static int N, M, maxH, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		water = new int[N][M];
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(c)-'0';
				maxH = Math.max(maxH, map[r][c]);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				water[r][c] = maxH - map[r][c];
			}
		}
		
		for(int h = maxH; h > 1; h--) {
			visited = new boolean[N][M];
			
			for(int r = 0; r < N; r++) {
				if(map[r][0] < h && !visited[r][0]) bfs(r, 0, h);
				if(map[r][M-1] < h && !visited[r][M-1]) bfs(r, M-1, h);
			}
			
			for(int c = 0; c < M; c++) {
				if(map[0][c] < h && !visited[0][c]) bfs(0, c, h);
				if(map[N-1][c] < h && !visited[N-1][c]) bfs(N-1, c, h);
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				ans += water[r][c];
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int r, int c, int h) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		visited[r][c] = true;
		water[r][c]--;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[nr][nc]) continue;
				
				if(map[nr][nc] < h) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
					water[r][c]--;
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
