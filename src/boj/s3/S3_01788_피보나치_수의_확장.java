package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_01788_피보나치_수의_확장 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		final int MOD = 1000000000;
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[2000001];
		
		dp[1000000] = 0;
		dp[1000001] = 1;
		
		if(n > 1) {
			for(int i = 1000002; i <= 1000000+n; i++) {
				dp[i] = (dp[i-2] + dp[i-1]) % MOD;
			}
		}
		else if(n < 0) {
			for(int i = 999999; i >= 1000000+n; i--) {
				dp[i] = (dp[i+2] - dp[i+1]) % MOD;
			}
		}
		
		if(dp[1000000+n] > 0) System.out.println(1);
		else if(dp[1000000+n] == 0) System.out.println(0);
		else System.out.println(-1);
		
		System.out.println(Math.abs(dp[1000000+n]));
	}
}
