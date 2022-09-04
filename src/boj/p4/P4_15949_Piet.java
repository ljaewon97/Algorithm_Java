package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class P4_15949_Piet {
	static StringBuilder sb = new StringBuilder();
	static Map<Integer, List<Point>> borderMap = new HashMap<>();
	static char[][] map;
	static int[][] codes;
	static boolean[][] visited;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	static int N, M, DP, CC = 3, cr, cc, code = 1, cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][];
		codes = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		sb.append(map[0][0]);
		
		while(true) {
			List<Point> points = null;
			
			if(codes[cr][cc] == 0) {
				points = getPoints();
				borderMap.put(codes[cr][cc], points);
			}
			else points = borderMap.get(codes[cr][cc]);
			
			List<Point> DPPoints = getSubPoints(points, DP);
			Point cur = getSubPoints(DPPoints, CC).get(0);
			
			int nr = cur.r + dr[DP];
			int nc = cur.c + dc[DP];
			
			if(!isIn(nr, nc) || map[nr][nc] == 'X') {
				cnt++;
				
				if((cnt & 1) != 0) CC += 2;
				else {
					DP++;
					CC++;
				}
				
				DP %= 4;
				CC %= 4;
			}
			else {
				sb.append(map[nr][nc]);
				cr = nr;
				cc = nc;
				cnt = 0;
			}
			
			code++;
			if(cnt == 8) break;
		}
		
		System.out.println(sb);
	}
	
	static List<Point> getSubPoints(List<Point> points, int dir) {
		List<Point> res = new ArrayList<>();
		
		if(dir == 0) Collections.sort(points, sort0);
		if(dir == 1) Collections.sort(points, sort1);
		if(dir == 2) Collections.sort(points, sort2);
		if(dir == 3) Collections.sort(points, sort3);
		
		int val = -1;
		
		if(dir == 0 || dir == 2) {
			val = points.get(0).c;
			
			for(Point cur: points) {
				if(cur.c == val) res.add(cur);
				else break;
			}
		}
		else {
			val = points.get(0).r;
			
			for(Point cur: points) {
				if(cur.r == val) res.add(cur);
				else break;
			}
		}
		
		return res;
	}
	
	static List<Point> getPoints() {
		List<Point> points = new ArrayList<>();
		Queue<Point> queue = new LinkedList<>();
		
		queue.add(new Point(cr, cc));
		visited[cr][cc] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int r = cur.r;
			int c = cur.c;
			
			codes[r][c] = code;
			
			points.add(cur);
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] == map[r][c]) {
					visited[nr][nc] = true;
					queue.add(new Point(nr, nc));
				}
			}
		}
		
		return points;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < M;
	}
	
	static private class Point {
		int r;
		int c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static Comparator<Point> sort0 = new Comparator<Point>() {
		public int compare(Point o1, Point o2) {
			return o2.c - o1.c;
		}
	};
	
	static Comparator<Point> sort1 = new Comparator<Point>() {
		public int compare(Point o1, Point o2) {
			return o2.r - o1.r;
		}
	};
	
	static Comparator<Point> sort2 = new Comparator<Point>() {
		public int compare(Point o1, Point o2) {
			return o1.c - o2.c;
		}
	};
	
	static Comparator<Point> sort3 = new Comparator<Point>() {
		public int compare(Point o1, Point o2) {
			return o1.r - o2.r;
		}
	};
}
