package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_23291_어항_정리 {
	static int[][] fb;
	static int N, K, floor;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		fb = new int[N][N];
		floor = N - 1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			fb[floor][i] = Integer.parseInt(st.nextToken());
		}
		
		int ans = 0;
		
		while(true) {
			int min = findFloorMin();
			addFish(min);
			putLeftFb();
			floatRotate();
			ctrlFishNum();
			putDownFb();
			floatHalfRotate180();
			floatHalfRotate180();
			ctrlFishNum();
			putDownFb();
			
			ans++;
			
			if(check()) break;
		}
		
		System.out.println(ans);
	}
	
	static int findFloorMin() {
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			min = Math.min(min, fb[floor][i]);
		}
		
		return min;
	}
	
	static void addFish(int min) {
		for(int i = 0; i < N; i++) {
			if(fb[floor][i] == min) {
				fb[floor][i]++;
			}
		}
	}
	
	static void putLeftFb() {
		fb[floor-1][1] = fb[floor][0];
		fb[floor][0] = 0;
	}
	
	static void floatRotate() {
		while(true) {
			int R = 0, C = 0, idx = -1;
			
			for(int i = 0; i < N; i++) {
				if(fb[floor-1][i] != 0) {
					if(idx == -1) idx = i;
					R++;
				}
			}
			
			for(int i = floor; i >= 0; i--) {
				if(fb[i][idx] != 0) C++;
				else break;
			}
			
			if(N - R - idx < C) break;
			
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++) {
					fb[floor-R+i][R+idx+j] = fb[floor-j][idx+i];
					fb[floor-j][idx+i] = 0;
				}
			}
		}
	}
	
	static void ctrlFishNum() {
		int[][] temp = new int[N][N];
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(fb[r][c] != 0) {
					for(int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if(isIn(nr, nc) && fb[nr][nc] != 0 && fb[r][c] > fb[nr][nc]) {
							int diff = (fb[r][c] - fb[nr][nc]) / 5;
							if(diff > 0) {
								temp[r][c] -= diff;
								temp[nr][nc] += diff;
							}
						}
					}
				}
			}
		}
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				fb[r][c] += temp[r][c];
			}
		}
	}
	
	static void putDownFb() {
		int R = 0, C = 0, idx = -1;
		
		for(int i = 0; i < N; i++) {
			if(fb[floor-1][i] != 0) {
				if(idx == -1) idx = i;
				R++;
			}
		}
		
		for(int i = floor; i >= 0; i--) {
			if(fb[i][idx] != 0) C++;
			else break;
		}

		for(int i = 0; i < R*C; i++) {
			int temp = fb[floor-i%C][idx+i/C];
			fb[floor-i%C][idx+i/C] = 0;
			fb[floor][i] = temp;
		}
	}
	
	static void floatHalfRotate180() {
		int R = 0, C = 0;
		
		for(int i = 0; i < N; i++) {
			if(fb[floor][i] != 0) C++;
		}
		
		for(int i = floor; i >= 0; i--) {
			if(fb[i][N-1] != 0) R++;
			else break;
		}
		
		C /= 2;
		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				fb[floor+1-2*R+r][N-C+c] = fb[floor-r][N-1-C-c];
				fb[floor-r][N-1-C-c] = 0;
			}
		}
	}
	
	static boolean check() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(fb[i][j] != 0) {
					if(fb[i][j] > max) {
						max = fb[i][j];
					}
					if(fb[i][j] < min) {
						min = fb[i][j];
					}
				}
			}
		}
		
		return (max - min) <= K ? true : false;
	}
	
	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}
