package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class G5_23747_와드 {
	static char[][] map;
	static boolean[][] ward;
	static int R, C, hr, hc;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][];
		ward = new boolean[R][C];
		
		for(int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		st = new StringTokenizer(br.readLine());
		hr = Integer.parseInt(st.nextToken()) - 1;
		hc = Integer.parseInt(st.nextToken()) - 1;
		
		String cmds = br.readLine();
		
		for(int i = 0; i < cmds.length(); i++) {
			char c = cmds.charAt(i);
			
			if(c == 'W') {
				if(!ward[hr][hc]) putWard();
			}
			else {
				move(c);
			}
		}
		
		sight();
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				sb.append(ward[r][c] ? '.' : '#');
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void putWard() {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(hr, hc));
		ward[hr][hc] = true;
		
		while(!queue.isEmpty()) {
			Point p = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				
				if(isIn(nr, nc) && !ward[nr][nc] && map[nr][nc] == map[hr][hc]) {
					queue.add(new Point(nr, nc));
					ward[nr][nc] = true;
				}
			}
		}
	}
	
	static void move(char c) {
		int d = 0;
		if(c == 'D') d = 1;
		else if(c == 'L') d = 2;
		else if(c == 'R') d = 3;
		
		hr += dr[d];
		hc += dc[d];
	}
	
	static void sight() {
		ward[hr][hc] = true;
		
		for(int i = 0; i < 4; i++) {
			int nr = hr + dr[i];
			int nc = hc + dc[i];
			
			if(isIn(nr, nc)) ward[nr][nc] = true;
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
	static class Point {
		int r, c;
		
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
