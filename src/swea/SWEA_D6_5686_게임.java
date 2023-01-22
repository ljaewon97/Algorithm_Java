package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D6_5686_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int[][] ans = new int[N][2];
			
			for(int i = 0; i < N; i++) {
				int[][] dp = new int[M][2];
				st = new StringTokenizer(br.readLine());
				
				dp[0][0] = Integer.parseInt(st.nextToken());
				
				for(int j = 1; j < M; j++) {
					dp[j][0] = dp[j-1][1] + Integer.parseInt(st.nextToken());
					dp[j][1] = Math.max(dp[j-1][0], dp[j-1][1]);
				}
				
				if(i == 0) ans[0][0] = Math.max(dp[M-1][0], dp[M-1][1]);
				else {
					ans[i][0] = ans[i-1][1] + Math.max(dp[M-1][0], dp[M-1][1]);
					ans[i][1] = Math.max(ans[i-1][0], ans[i-1][1]);
				}
			}
			
			sb.append(String.format("#%d %d\n", t, Math.max(ans[N-1][0], ans[N-1][1])));
		}
		
		System.out.println(sb);
	}
}
