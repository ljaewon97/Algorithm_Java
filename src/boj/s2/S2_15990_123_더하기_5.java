package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_15990_123_더하기_5 {
	static final int MOD = 1000000009;
	static int[][] dp = new int[100001][4];
	static int len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		dp[1][1] = 1;
		dp[2][2] = 1;
		dp[3][1] = 1;
		dp[3][2] = 1;
		dp[3][3] = 1;
		len = 3;
			
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			
			if(n <= len) {
				sb.append(((dp[n][1] + dp[n][2]) % MOD + dp[n][3]) % MOD).append("\n");
			}
			else {
				calc(n);
				sb.append(((dp[n][1] + dp[n][2]) % MOD + dp[n][3]) % MOD).append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	static void calc(int n) {
		for(int i = len+1; i <= n; i++) {
			dp[i][1] = (dp[i-1][2] + dp[i-1][3]) % MOD;
			dp[i][2] = (dp[i-2][1] + dp[i-2][3]) % MOD;
			dp[i][3] = (dp[i-3][1] + dp[i-3][2]) % MOD;
		}	
		
		len = n;
	}
}
