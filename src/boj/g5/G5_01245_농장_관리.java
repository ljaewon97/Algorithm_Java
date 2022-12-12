package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_01245_농장_관리 {
	static int[][] map;
	static boolean[][] visited;
	static int N, M, ans;
	static int[] dr = {-1,1,0,0,-1,-1,1,1};
	static int[] dc = {0,0,-1,1,-1,1,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(!visited[r][c] && check(r, c)) ans++;
			}
		}
		
		System.out.println(ans);
	}
	
	static boolean check(int r, int c) {
		boolean ret = true;
		
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(r, c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 8; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				if(!visited[nr][nc] && map[nr][nc] == map[p.r][p.c]) {
					queue.add(new Point(nr, nc));
					visited[nr][nc] = true;
				}
				else if(map[nr][nc] > map[p.r][p.c]) ret = false;
			}
		}
		
		return ret;
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
