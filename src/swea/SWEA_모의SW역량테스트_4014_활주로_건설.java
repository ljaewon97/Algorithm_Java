package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_모의SW역량테스트_4014_활주로_건설 {
	static int N, X, ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			int[][] mapRotated = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					mapRotated[j][i] = map[i][j];
				}
			}
			
			ans = 0;
			
			check(map);
			check(mapRotated);
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static void check(int[][] map) {
		boolean[][] built = new boolean[N][N];
		
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
	}
}
