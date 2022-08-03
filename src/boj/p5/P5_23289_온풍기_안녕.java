package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class P5_23289_온풍기_안녕 {
	static int[][] map;
	static int[][] heaters;
	static int[][] targets;
	static int[][] walls;
	static boolean[][] visited;
	static int R, C, K, chocolate = 0;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};
	static int[][][] deltas = 	{{{0,1},{-1,0},{-1,1},{1,0},{1,1}},
								{{0,-1},{-1,0},{-1,-1},{1,0},{1,-1}},
								{{-1,0},{0,-1},{-1,-1},{0,1},{-1,1}},
								{{1,0},{0,-1},{1,-1},{0,1},{1,1}}};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[R+1][C+1];
		heaters = new int[400][3];
		targets = new int[400][2];
		int heaterCnt = 0, targetCnt = 0, temp = 0;
		for(int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= C; j++) {
				temp = Integer.parseInt(st.nextToken());
				if(temp == 0) {
					continue;
				}
				else if(temp == 5) {
					targets[targetCnt][0] = i;
					targets[targetCnt][1] = j;
					targetCnt++;
				}
				else {
					heaters[heaterCnt][0] = i;
					heaters[heaterCnt][1] = j;
					heaters[heaterCnt][2] = temp;
					heaterCnt++;
				}
			}
		}
		
		heaters = Arrays.copyOf(heaters, heaterCnt);
		targets = Arrays.copyOf(targets, targetCnt);
		
		walls = new int[R+1][C+1];
		int W = Integer.parseInt(br.readLine());
		for(int w = 0; w < W; w++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			if(t == 0) {
				walls[x][y] += 1 << 3;
				walls[x-1][y] += 1 << 4;
			}
			else {
				walls[x][y] += 1 << 1;
				walls[x][y+1] += 1 << 2;
			}
		}
		
		while(true) {
			for(int[] heater: heaters) {
				heaterOn(heater[0], heater[1], heater[2]-1);
			}
			
			controlTemp();
			controlBorder();
			chocolate++;
			
			if(investigate() || chocolate == 101) {
				break;
			}
		}
		
		System.out.println(chocolate);
	}
	
	static void heaterOn(int r, int c, int dir) {
		visited = new boolean[R+1][C+1];
		int sr = r + dr[dir];
		int sc = c + dc[dir];
		Deque<Integer[]> deque = new LinkedList<>();
		deque.add(new Integer[] {sr, sc, 5});
		visited[sr][sc] = true;
		
		while(!deque.isEmpty()) {
			Integer[] cur = deque.poll();
			int cr = cur[0];
			int cc = cur[1];
			int power = cur[2];
			map[cr][cc] += power;
			
			if(power == 1) {
				continue;
			}
			
			int nr = cr + deltas[dir][0][0];
			int nc = cc + deltas[dir][0][1];
			if(isIn(nr, nc) && !visited[nr][nc] && canMove(cr, cc, nr, nc)) {
				deque.add(new Integer[] {nr, nc, power-1});
				visited[nr][nc] = true;
			}
			for(int i = 0; i < 2; i++) {
				int tr = cr + deltas[dir][2*i+1][0];
				int tc = cc + deltas[dir][2*i+1][1];
				nr = cr + deltas[dir][2*i+2][0];
				nc = cc + deltas[dir][2*i+2][1];
				if(isIn(nr, nc) && !visited[nr][nc] && canMove(cr, cc, tr, tc) && canMove(tr, tc, nr, nc)) {
					deque.add(new Integer[] {nr, nc, power-1});
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= R && 1 <= c && c <= C;
	}
	
	static boolean canMove(int r1, int c1, int r2, int c2) {
		if(walls[r1][c1] == 0) {
			return true;
		}
		
		int dir = 0, rdiff = r2 - r1, cdiff = c2 - c1;
		for(int i = 0; i < 4; i++) {
			if(rdiff == dr[i] && cdiff == dc[i]) {
				dir = i + 1;
				break;
			}
		}
		
		if((walls[r1][c1] & (1 << dir)) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	static void controlTemp() {
		Deque<Integer[]> deque = new LinkedList<>();
		int nr, nc, diff;
		
		for(int r = 1; r <= R; r++) {
			for(int c = 1; c <= C; c++) {
				for(int i = 0; i < 4; i++) {
					nr = r + dr[i];
					nc = c + dc[i];
					if(isIn(nr, nc) && canMove(r, c, nr, nc) && map[nr][nc] > map[r][c]) {
						diff = (map[nr][nc] - map[r][c]) / 4;
						deque.add(new Integer[] {r, c, diff});
						deque.add(new Integer[] {nr, nc, -diff});
					}
				}
			}
		}
		
		Integer[] cur;
		while(!deque.isEmpty()) {
			cur = deque.poll();
			map[cur[0]][cur[1]] += cur[2];
		}
	}
	
	static void controlBorder() {
		for(int c = 1; c <= C; c++) {
			if(map[1][c] >= 1) map[1][c]--;
			if(map[R][c] >= 1) map[R][c]--;
		}
		
		for(int r = 2; r <= R-1; r++) {
			if(map[r][1] >= 1) map[r][1]--;
			if(map[r][C] >= 1) map[r][C]--;
		}
	}
	
	static boolean investigate() {
		for(int[] target: targets) {
			if(map[target[0]][target[1]] < K) {
				return false;
			}
		}
		return true;
	}
}
