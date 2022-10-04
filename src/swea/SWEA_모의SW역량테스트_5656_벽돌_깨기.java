package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_모의SW역량테스트_5656_벽돌_깨기 {
	static int[][] map;
	static int[][][] temp;
	static int N, W, H, ans;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			ans = 0;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			int bricks = 0;
			
			for(int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] > 0) bricks++;
				}
			}
			
			solve(0, 0);
			
			sb.append(String.format("#%d %d\n", t, bricks-ans));
		}
		
		System.out.println(sb);
	}
	
	static void solve(int nth, int cnt) {
		if(nth == N) {
			ans = Math.max(ans, cnt);
			return;
		}
		
		for(int c = 0; c < W; c++) {
			if(nth == 0) {
				temp = new int[N][H][W];
				for(int i = 0; i < H; i++) {
					for(int j = 0; j < W; j++) {
						temp[0][i][j] = map[i][j];
					}
				}
			}
			else {
				for(int i = 0; i < H; i++) {
					for(int j = 0; j < W; j++) {
						temp[nth][i][j] = temp[nth-1][i][j];
					}
				}
			}
			
			int r = 0;
			while(r < H && temp[nth][r][c] == 0) r++;
			
			if(r == H) {
				solve(nth+1, cnt);
			}
			else {
				int broken = hit(nth, r, c);
				fall(nth);
				
				solve(nth+1, cnt+broken);
			}
		}
	}
	
	static void fall(int nth) {
		for(int c = 0; c < W; c++) {
			for(int r = H-2; r >= 0; r--) {
				for(int i = r; i < H-1; i++) {
					if(temp[nth][i][c] != 0 && temp[nth][i+1][c] == 0) {
						temp[nth][i+1][c] = temp[nth][i][c];
						temp[nth][i][c] = 0;
					}
				}
			}
		}
	}
	
	static int hit(int nth, int r, int c) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cr = cur[0];
			int cc = cur[1];
			int step = temp[nth][cr][cc];
			
			if(temp[nth][cr][cc] > 0) {
				temp[nth][cr][cc] = 0;
				cnt++;
			}
			else continue;
			
			for(int i = 0; i < 4; i++) {
				for(int j = 1; j < step; j++) {
					int nr = cr + dr[i] * j;
					int nc = cc + dc[i] * j;
					
					if(!isIn(nr, nc)) break;
					
					if(temp[nth][nr][nc] > 0) {
						queue.add(new int[] {nr, nc});
					}
				}
			}
		}
		
		return cnt;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < H && 0 <= c && c < W;
	}
}
