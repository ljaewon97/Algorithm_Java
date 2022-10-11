package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW역량테스트_4014_활주로_건설 {
	static int[][] map;
	static boolean[][] built;
	static int N, X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int ans = 0;
			
			built = new boolean[N][N];
			
			for(int r = 0; r < N; r++) {
				boolean ok = true;
				
				for(int c = 1; c < N; c++) {
					if(Math.abs(map[r][c-1] - map[r][c]) > 1) {
						ok = false;
						break;
					}
					
					if(map[r][c-1] == map[r][c]+1) {
						for(int k = c; k < c+X; k++) {
							if(k > N-1 || map[r][k] != map[r][c]) {
								ok = false;
								break;
							}
							
							built[r][k] = true;
						}
					}
					
					if(!ok) break;
				}
				
				if(!ok) continue;
				
				for(int c = N-1; c > 0; c--) {
					if(map[r][c-1]+1 == map[r][c]) {
						for(int k = c-1; k >= c-X; k--) {
							if(k < 0 || map[r][k] != map[r][c-1] || built[r][k]) {
								ok = false;
								break;
							}
						}
					}
					
					if(!ok) break;
				}
				
				if(ok) ans++;
			}
			
			built = new boolean[N][N];
			
			for(int c = 0; c < N; c++) {
				boolean ok = true;
				
				for(int r = 1; r < N; r++) {
					if(Math.abs(map[r-1][c] - map[r][c]) > 1) {
						ok = false;
						break;
					}
					
					if(map[r-1][c] == map[r][c]+1) {
						for(int k = r; k < r+X; k++) {
							if(k > N-1 || map[k][c] != map[r][c]) {
								ok = false;
								break;
							}
							
							built[k][c] = true;
						}
					}
					
					if(!ok) break;
				}
				
				if(!ok) continue;
				
				for(int r = N-1; r > 0; r--) {
					if(map[r-1][c]+1 == map[r][c]) {
						for(int k = r-1; k >= r-X; k--) {
							if(k < 0 || map[k][c] != map[r-1][c] || built[k][c]) {
								ok = false;
								break;
							}
						}
					}
					
					if(!ok) break;
				}
				
				if(ok) ans++;
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
}
