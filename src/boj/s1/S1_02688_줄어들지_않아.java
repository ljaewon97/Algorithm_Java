package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_02688_줄어들지_않아 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long[][] dp = new long[65][10];
		long[] count = new long[65];
		
		for(int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		count[1] = 10;
		
		for(int i = 2; i < 65; i++) {
			dp[i][0] = dp[i-1][0];
			long sum = dp[i][0];
			for(int j = 1; j < 10; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
				sum += dp[i][j];
			}
			
			count[i] = sum;
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			sb.append(count[Integer.parseInt(br.readLine())]).append("\n");
		}
		
		System.out.println(sb);
	}
}
