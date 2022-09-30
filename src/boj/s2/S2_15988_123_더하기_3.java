package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_15988_123_더하기_3 {
	static int[][] dp = new int[1000001][3];
	static int len;
	static final int MOD = 1000000009;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		dp[1][0] = 1;
		dp[2][0] = 1;
		dp[2][1] = 1;
		dp[3][0] = 2;
		dp[3][1] = 1;
		dp[3][2] = 1;
		len = 3;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			if(N > len) calc(N);
			
			int res = ((dp[N][0] + dp[N][1]) % MOD + dp[N][2]) % MOD;
			sb.append(res).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void calc(int n) {
		for(int i = len+1; i <= n; i++) {
			dp[i][0] = ((dp[i-1][0] + dp[i-1][1]) % MOD + dp[i-1][2]) % MOD;
			dp[i][1] = ((dp[i-2][0] + dp[i-2][1]) % MOD + dp[i-2][2]) % MOD;
			dp[i][2] = ((dp[i-3][0] + dp[i-3][1]) % MOD + dp[i-3][2]) % MOD;
		}
		
		len = n;
	}
}
