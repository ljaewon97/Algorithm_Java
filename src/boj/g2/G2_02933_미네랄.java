package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class G2_02933_미네랄 {
	static char[][] cave;
	static boolean[][] visited;
	static int R, C, N;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cave = new char[R][];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			cave[i] = br.readLine().toCharArray();
		}
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int stick = Integer.parseInt(st.nextToken());
			
			throwStick(i, stick);
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(cave[i][j]);
			}
			if(i != R-1) sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void throwStick(int turn, int stick) {
		int h = R - stick;
		
		if(turn % 2 == 0) {
			for(int c = 0; c < C; c++) {
				if(cave[h][c] == 'x') {
					cave[h][c] = '.';
					
					int nr1 = h-1, nc1 = c;
					int nr2 = h, nc2 = c+1;
					int nr3 = h+1, nc3 = c;
					
					if(isIn(nr1, nc1) && cave[nr1][nc1] == 'x') {
						gravity(nr1, nc1);
					}
					if(isIn(nr2, nc2) && cave[nr2][nc2] == 'x') {
						gravity(nr2, nc2);
					}
					if(isIn(nr3, nc3) && cave[nr3][nc3] == 'x') {
						gravity(nr3, nc3);
					}
					
					break;
				}
			}
		}
		else {
			for(int c = C-1; c >= 0; c--) {
				if(cave[h][c] == 'x') {
					cave[h][c] = '.';
					
					int nr1 = h-1, nc1 = c;
					int nr2 = h, nc2 = c-1;
					int nr3 = h+1, nc3 = c;
					
					if(isIn(nr1, nc1) && cave[nr1][nc1] == 'x') {
						gravity(nr1, nc1);
					}
					if(isIn(nr2, nc2) && cave[nr2][nc2] == 'x') {
						gravity(nr2, nc2);
					}
					if(isIn(nr3, nc3) && cave[nr3][nc3] == 'x') {
						gravity(nr3, nc3);
					}
					
					break;
				}
			}
		}
	}
	
	static void gravity(int sr, int sc) {
		visited = new boolean[R][C];
		boolean onGround = false;
		Deque<int[]> deque = new LinkedList<>();
		Deque<int[]> bottom = new LinkedList<>();
		List<int[]> cluster = new ArrayList<>();
		
		int[] start = {sr, sc};
		deque.add(start);
		cluster.add(start);
		visited[sr][sc] = true;
		
		while(!deque.isEmpty()) {
			int[] cur = deque.poll();
			int r = cur[0];
			int c = cur[1];
			
			if(onGround) break;
			
			for(int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(i == 0 && isIn(nr, nc) && cave[nr][nc] == '.') {
					bottom.add(new int[] {r, c});
					continue;
				}
				
				if(i == 0 && !isIn(nr, nc)) {
					onGround = true;
					break;
				}
				
				
				if(isIn(nr, nc) && !visited[nr][nc] && cave[nr][nc] == 'x') {
					visited[nr][nc] = true;
					deque.add(new int[] {nr, nc});
					cluster.add(new int[] {nr, nc});
				}
			}
		}
		
		if(onGround) return;
		
		Collections.sort(cluster, (o1, o2) -> o2[0] - o1[0]);
		int fall = Integer.MAX_VALUE;
		
		while(!bottom.isEmpty()) {
			int[] cur = bottom.poll();
			int r = cur[0];
			int c = cur[1];
			int step = 1;
			boolean meetSelf = false;
			
			while(true) {
				int nr = r + step;
				
				if(isIn(nr, c) && cave[nr][c] == 'x' && visited[nr][c]) {
					meetSelf = true;
					break;
				}
				if(!isIn(nr, c) || cave[nr][c] != '.') break;
				
				step++;
			}
			
			if(!meetSelf && step - 1 < fall) {
				fall = step - 1;
			}
		}
		
		if(fall == 0) return;
		
		for(int[] mineral: cluster) {
			int r = mineral[0];
			int c = mineral[1];
			
			cave[r][c] = '.';
			cave[r+fall][c] = 'x';
		}
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
}
