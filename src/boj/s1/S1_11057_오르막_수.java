package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_11057_오르막_수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int MOD = 10007;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][10];
		
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		for(int i = 2; i <= N; i++) {
			int sum = 0;
			
			for(int j = 0; j < 10; j++) {
				sum += dp[i-1][j];
				dp[i][j] = sum % MOD;
			}
		}
		
		int ans = 0;
		
		for(int i = 0; i < 10; i++) {
			ans += dp[N][i];
		}
		
		System.out.println(ans % MOD);
	}
}
