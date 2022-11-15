package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW역량테스트_2382_미생물_격리 {
	static int[][][] map, temp, swap;
	static int N, M, K;
	static int[] dr = {0,-1,1,0,0};
	static int[] dc = {0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N][3];
			temp = new int[N][N][3];
			swap = new int[N][N][3];
			
			while(K-- > 0) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				map[r][c][0] = n;
				map[r][c][1] = d;
			}
			
			while(M-- > 0) {
				move();
			}
			
			int ans = count();
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void move() {
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				if(map[r][c][0] != 0) {
					int n = map[r][c][0];
					int d = map[r][c][1];
					map[r][c][0] = map[r][c][1] = 0;
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if(inBoundary(nr, nc)) {
						n /= 2;
						d = d <= 2 ? 3-d : 7-d;
					}
					
					if(temp[nr][nc][0] == 0) {
						temp[nr][nc][0] = n;
						temp[nr][nc][1] = d;
						temp[nr][nc][2] = n;
					}
					else {
						temp[nr][nc][0] += n;
						if(n > temp[nr][nc][2]) {
							temp[nr][nc][1] = d;
							temp[nr][nc][2] = n;
						}
					}
				}
			}
		}
		
		swap = map;
		map = temp;
		temp = swap;
	}
	
	static int count() {
		int ret = 0;
		
		for(int r = 0; r < N; r++) {
			for(int c = 0; c < N; c++) {
				ret += map[r][c][0];
			}
		}
		
		return ret;
	}
	
	static boolean inBoundary(int r, int c) {
		return r == 0 || r == N-1 || c == 0 || c == N-1;
	}
}
