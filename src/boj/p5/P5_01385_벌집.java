package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class P5_01385_벌집 {
	static StringBuilder sb = new StringBuilder();
	static int[][] map = new int[1200][1200];
	static int[][] visited = new int[1200][1200];
	static int[] dr = {-1,1,-1,-1,0,0};
	static int[] dc = {0,0,-1,1,-1,1};
	static int sr, sc, a, b;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		init(a, b);
		bfs();
		
		System.out.println(sb);
	}
	
	static void bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc));
		visited[sr][sc] = -1;
		int er = -1, ec = -1;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if(map[cur.r][cur.c] == b) {
				er = cur.r;
				ec = cur.c;
				break;
			}
			
			for(int i = 0; i < 6; i++) {
				int nr = cur.r + dr[i] + (i > 1 ? (cur.c + 1) % 2 : 0);
				int nc = cur.c + dc[i];
				
				if(map[nr][nc] == 0 || visited[nr][nc] != 0) continue;
				
				visited[nr][nc] = 1200 * cur.r + cur.c;
				queue.add(new Point(nr, nc));
			}
		}
		
		Stack<Integer> route = new Stack<>();
		route.add(b);
		
		while(true) {
			if(visited[er][ec] == -1) break;
			
			int r = visited[er][ec] / 1200;
			int c = visited[er][ec] % 1200;
			route.add(map[r][c]);
			er = r;
			ec = c;
		}
		
		while(!route.isEmpty()) {
			sb.append(route.pop()).append(" ");
		}
	}
	
	static void init(int a, int b) {
		int na = 0;
		
		while(true) {
			if(a <= 3*na*(na+1)+1) break;
			na++;
		}
		
		int nb = 0;
		
		while(true) {
			if(b <= 3*nb*(nb+1)+1) break;
			nb++;
		}
		
		int nmax = Math.max(na, nb);
		
		for(int n = nmax; n >= 0; n--) {
			int temp = 3*n*(n+1)+1;
			int r = -1 * (n / 2) + 600;
			int c = -n + 600;
			
			if(temp == a) {
				sr = r;
				sc = c;
			}
			map[r][c] = temp--;
			
			for(int i = 0; i < n; i++) {
				r += dr[1];
				c += dc[1];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
			
			for(int i = 0; i < n; i++) {
				r += dr[5] + (c + 1) % 2;
				c += dc[5];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
			
			for(int i = 0; i < n; i++) {
				r += dr[3] + (c + 1) % 2;
				c += dc[3];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
			
			for(int i = 0; i < n; i++) {
				r += dr[0];
				c += dc[0];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
			
			for(int i = 0; i < n; i++) {
				r += dr[2] + (c + 1) % 2;
				c += dc[2];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
			
			for(int i = 0; i < n-1; i++) {
				r += dr[4] + (c + 1) % 2;
				c += dc[4];
				if(temp == a) {
					sr = r;
					sc = c;
				}
				map[r][c] = temp--;
			}
		}
	}
	
	static private class Point {
		int r;
		int c;
		
		private Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
