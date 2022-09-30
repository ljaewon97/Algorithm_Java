package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_01309_동물원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 9901;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][3];
		
		dp[0][0] = 1;
		dp[0][1] = 1;
		dp[0][2] = 1;
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;
			dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;
			dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;
		}
		
		System.out.println((dp[N-1][0] + dp[N-1][1] + dp[N-1][2]) % MOD);
	}
}
