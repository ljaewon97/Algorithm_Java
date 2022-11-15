package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_D4_4193_수영대회_결승전 {
	static char[][] map;
	static boolean[][] visited;
	static int N, sr, sc, er, ec;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			map = new char[N][N];
			visited = new boolean[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
				}
			}
			
			st = new StringTokenizer(br.readLine());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			er = Integer.parseInt(st.nextToken());
			ec = Integer.parseInt(st.nextToken());
			
			int ans = bfs();
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.r == er && p.c == ec) return p.d;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc)) continue;
				
				if(p.d % 3 == 2) {
					if(!visited[nr][nc] && map[nr][nc] != '1') {
						queue.add(new Point(nr, nc, p.d+1));
						visited[nr][nc] = true;
					}
				}
				else {
					if(!visited[nr][nc] && map[nr][nc] == '0') {
						queue.add(new Point(nr, nc, p.d+1));
						visited[nr][nc] = true;
					}
				}
			}
			
			if(p.d % 3 != 2) queue.add(new Point(p.r, p.c, p.d+1));
		}
		
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
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
