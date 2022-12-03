package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_23083_꿀벌_승연이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 1000000007;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N+1][M+1];
		
		int K = Integer.parseInt(br.readLine());
		
		while(K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dp[r][c] = -1;
		}
		
		dp[0][1] = 1;
		
		for(int c = 1; c <= M; c++) {
			int d = c % 2 == 1 ? 0 : 1;
			for(int r = 1; r <= N; r++) {
				if(dp[r][c] == -1) continue;
				
				int u = dp[r-1][c] == -1 ? 0 : dp[r-1][c];
				int ul = dp[r-1+d][c-1] == -1 ? 0 : dp[r-1+d][c-1];
				int dl = r == N && d == 1 ? 0 : (dp[r+d][c-1] == -1 ? 0 : dp[r+d][c-1]);
				
				dp[r][c] = (int) (((long) u + ul + dl) % MOD);
			}
		}
		
		System.out.println(dp[N][M]);
	}
}
