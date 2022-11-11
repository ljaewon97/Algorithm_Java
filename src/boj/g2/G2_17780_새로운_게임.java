package boj.g2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class G2_17780_새로운_게임 {
	static Horse[] info;
	static Stack<Integer>[][] map;
	static int[][] color;
	static int N, K;
	static boolean end;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		info = new Horse[K+1];
		map = new Stack[N+1][N+1];
		color = new int[N+1][N+1];
		
		for(int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				color[r][c] = Integer.parseInt(st.nextToken());
				map[r][c] = new Stack<>();
			}
		}
		
		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			map[r][c].push(i);
			info[i] = new Horse(r, c, d);
		}
		
		int turn = 0;
		
		while(true) {
			if(turn > 1000) {
				turn = -1;
				break;
			}
			
			move();
			turn++;
			if(end) break;
		}
		
		System.out.println(turn);
	}
	
	static void move() {
		for(int i = 1; i <= K; i++) {
			Horse cur = info[i];
			int nr = cur.r + dr[cur.d];
			int nc = cur.c + dc[cur.d];
			boolean fin = false;
			
			int nth = 0;
			for(int h: map[cur.r][cur.c]) {
				if(i == h) break;
				nth++;
			}
			
			if(nth != 0) continue;
			
			nth = map[cur.r][cur.c].size() - nth;
			
			if(!isIn(nr, nc) || color[nr][nc] == 2) {
				int nd = cur.d <= 1 ? 1-cur.d : 5-cur.d;
				nr = cur.r + dr[nd];
				nc = cur.c + dc[nd];
				cur.d = nd;
				
				if(!isIn(nr, nc) || color[nr][nc] == 2) fin = true;
			}
			
			if(fin) continue;
			
			if(color[nr][nc] == 0) {
				Stack<Integer> temp = new Stack<>();
				while(nth-- > 0) {
					temp.push(map[cur.r][cur.c].pop());
				}
				while(!temp.isEmpty()) {
					int h = temp.pop();
					info[h].r = nr;
					info[h].c = nc;
					map[nr][nc].add(h);
				}
			}
			else if(color[nr][nc] == 1) {
				Queue<Integer> temp = new LinkedList<>();
				while(nth-- > 0) {
					temp.add(map[cur.r][cur.c].pop());
				}
				while(!temp.isEmpty()) {
					int h = temp.poll();
					info[h].r = nr;
					info[h].c = nc;
					map[nr][nc].add(h);
				}
			}
			
			if(map[nr][nc].size() >= 4) {
				end = true;
				return;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return 1 <= r && r <= N && 1 <= c && c <= N;
	}
	
	static class Horse {
		int r, c, d;
		
		public Horse(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
