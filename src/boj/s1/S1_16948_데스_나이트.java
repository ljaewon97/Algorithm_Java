package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_16948_데스_나이트 {
	static boolean[][] visited;
	static int N, sr, sc, er, ec;
	static int[] dr = {-2,-2,0,0,2,2};
	static int[] dc = {-1,1,-2,2,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		int ret = -1;
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.r == er && p.c == ec) {
				ret = p.t;
				break;
			}
			
			for(int i = 0; i < 6; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc, p.t+1));
				}
			}
		}
		
		return ret;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Point {
		int r, c, t;
		
		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}
}
