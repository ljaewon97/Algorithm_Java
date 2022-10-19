package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S3_17212_달나라_토끼를_위한_구매대금_지불_도우미 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		int[] coins = {1,2,5,7};
		
		Arrays.fill(dp, 200000);
		dp[0] = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = coins[i]; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j-coins[i]]+1);
			}
		}
		
		System.out.println(dp[N]);
	}
}
