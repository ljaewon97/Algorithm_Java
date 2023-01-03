package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class G2_04991_로봇_청소기 {
	static Map<Integer, Integer> dLoc;
	static char[][] map;
	static boolean[][][] visited;
	static int N, M, sr, sc, dirty, target;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			dLoc = new HashMap<>();
			dirty = 0;
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N+M == 0) break;
			
			map = new char[N][M];
			
			for(int r = 0; r < N; r++) {
				String line = br.readLine();
				for(int c = 0; c < M; c++) {
					map[r][c] = line.charAt(c);
					if(map[r][c] == '*') dLoc.put(r*M+c, dirty++);
					else if(map[r][c] == 'o') {
						sr = r;
						sc = c;
						map[r][c] = '.';
					}
				}
			}
			
			visited = new boolean[N][M][1<<dirty];
			target = (1<<dirty) - 1;
			
			sb.append(bfs()).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static int bfs() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(sr, sc, 0, 0));
		visited[sr][sc][0] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.b == target) return p.d;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || visited[nr][nc][p.b]) continue;
				
				if(map[nr][nc] == '.') {
					queue.add(new Point(nr, nc, p.d+1, p.b));
					visited[nr][nc][p.b] = true;
				}
				else if(map[nr][nc] == '*') {
					int dNum = dLoc.get(nr*M+nc);
					int nb = p.b | (1<<dNum);
					queue.add(new Point(nr, nc, p.d+1, nb));
					visited[nr][nc][p.b] = true;
					visited[nr][nc][nb] = true;
				}
			}
		}
		
		return -1;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static class Point {
		int r, c, d, b;
		
		public Point(int r, int c, int d, int b) {
			this.r = r;
			this.c = c;
			this.d = d;
			this.b = b;
		}
	}
}
