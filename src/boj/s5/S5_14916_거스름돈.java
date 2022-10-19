package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S5_14916_거스름돈 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n+1];
		Arrays.fill(dp, 1000000);
		dp[0] = 0;
		
		for(int i = 2; i <= n; i++) {
			dp[i] = Math.min(dp[i], dp[i-2]+1);
		}
		
		for(int i = 5; i <= n; i++) {
			dp[i] = Math.min(dp[i], dp[i-5]+1);
		}

		System.out.println(dp[n] == 1000000 ? -1 : dp[n]);
	}
}
