package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1_17129_윌리암슨수액빨이딱따구리가_정보섬에_올라온_이유 {
	static char[][] map;
	static boolean[][] visited;
	static int n, m;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		visited = new boolean[n][m];
		
		int sr = -1, sc = -1;
		
		for(int r = 0; r < n; r++) {
			String line = br.readLine();
			for(int c = 0; c < m; c++) {
				map[r][c] = line.charAt(c);
				if(map[r][c] == '2') {
					sr = r;
					sc = c;
				}
			}
		}
		
		int dist = bfs(sr, sc);
		
		if(dist != 0) {
			System.out.println("TAK");
			System.out.println(dist);
		}
		else {
			System.out.println("NIE");
		}
	}
	
	static int bfs(int sr, int sc) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0));
		visited[sr][sc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[nr][nc]) continue;
				
				if(map[nr][nc] == '0') {
					queue.add(new Point(nr, nc, p.d+1));
					visited[nr][nc] = true;
				}
				else if(map[nr][nc] != '1') {
					return p.d + 1;
				}
			}
		}
		
		return 0;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < m;
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
