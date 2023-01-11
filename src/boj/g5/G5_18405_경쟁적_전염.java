package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_18405_경쟁적_전염 {
	static Queue<Point> queue = new LinkedList<>();
	static PriorityQueue<Point> virus = new PriorityQueue<>();
	static int[][] map;
	static int N, K, S, X, Y;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] != 0) virus.add(new Point(r, c, map[r][c]));
			}
		}
		
		while(!virus.isEmpty()) {
			Point p = virus.poll();
			
			queue.add(new Point(p.r, p.c, 0));
		}
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		Y = Integer.parseInt(st.nextToken())-1;
		
		System.out.println(bfs());
	}
	
	static int bfs() {
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			if(p.t == S) continue;
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(!isIn(nr, nc) || map[nr][nc] != 0) continue;
				
				queue.add(new Point(nr, nc, p.t+1));
				map[nr][nc] = map[p.r][p.c];
			}
		}
		
		return map[X][Y];
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
	
	static class Point implements Comparable<Point> {
		int r, c, t;
		
		public Point(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}

		@Override
		public int compareTo(Point o) {
			return this.t - o.t;
		}
	}
}
