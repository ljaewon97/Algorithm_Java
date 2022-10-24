package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S3_17626_Four_Squares {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n+1];
		Arrays.fill(dp, 100000);
		dp[0] = 0;
		
		for(int i = 1; i*i <= n; i++) {
			int s = i*i;
			for(int j = s; j <= n; j++) {
				dp[j] = Math.min(dp[j], dp[j-s]+1);
			}
		}
		
		System.out.println(dp[n]);
	}
}
