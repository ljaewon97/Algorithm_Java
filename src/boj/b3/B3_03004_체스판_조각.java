package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_03004_체스판_조각 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N+1];
		
		dp[0] = 1;
		
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i-1] + (i/2 + 1);
		}
		
		System.out.println(dp[N]);
	}
}
