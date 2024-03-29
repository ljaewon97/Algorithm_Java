package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_08394_악수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n+1][2];
		
		dp[1][0] = 1;
		
		for(int i = 2; i <= n; i++) {
			dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 10;
			dp[i][1] = dp[i-1][0];
		}
		
		System.out.println((dp[n][0] + dp[n][1]) % 10);
	}
}
