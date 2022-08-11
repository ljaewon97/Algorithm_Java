package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_5215_햄버거_다이어트 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] items = new int[N+1][2];
			
			for(int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				items[i][0] = c;
				items[i][1] = s;
			}
			
			int[][] dp = new int[N+1][L+1];
			
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= L; j++) {
					int c = items[i][0];
					int s = items[i][1];
					
					if(j < c) {
						dp[i][j] = dp[i-1][j];
					}
					else {
						dp[i][j] = Math.max(s + dp[i-1][j-c], dp[i-1][j]);
					}
				}
			}
			
			sb.append(String.format("#%d %d\n", t, dp[N][L]));
		}
		
		System.out.println(sb);
	}
}
