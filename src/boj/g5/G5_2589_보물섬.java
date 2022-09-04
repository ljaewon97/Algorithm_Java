package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_2589_보물섬 {
	static char[][] map;
	static boolean[][] visited;
	static int N, M, ans = -1;
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
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < M; c++) {
				if(map[r][c] == 'L') bfs(r, c);
			}
		}
		
		System.out.println(ans);
	}
	
	static void bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		visited = new boolean[N][M];
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;		
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			ans = Math.max(ans, cur.d);
			
			for(int i = 0; i < 4; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == 'L') {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc, cur.d+1));
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static private class Point {
		int r;
		int c;
		int d;
		
		private Point(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
