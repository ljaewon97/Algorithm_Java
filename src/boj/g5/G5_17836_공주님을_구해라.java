package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_17836_공주님을_구해라 {
	static char[][] map;
	static int N, M, T, dist, gram;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		dist = gram = Integer.MAX_VALUE;
		
		for(int r = 0; r < N; r++) {
			String line = br.readLine();
			for(int c = 0; c < M; c++) {
				map[r][c] = line.charAt(2*c);
			}
		}
		
		bfs();
		
		int ans = dist == Integer.MAX_VALUE ? gram : dist;
		
		System.out.println(ans <= T ? ans : "Fail");
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(new Point(0, 0, 0));
		visited[0][0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.r == N-1 && p.c == M-1) {
				dist = p.t;
				return;
			}
			
			if(p.t > gram) return;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[nr][nc]) continue;
				
				if(map[nr][nc] == '0') {
					queue.add(new Point(nr, nc, p.t+1));
					visited[nr][nc] = true;
				}
				else if(map[nr][nc] == '2') {
					visited[nr][nc] = true;
					gram = p.t + 1 + (N-1-nr) + (M-1-nc);
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
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
