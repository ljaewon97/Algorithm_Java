package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S2_01699_제곱수의_합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		
		Arrays.fill(dp, 100001);
		int lim = (int) Math.sqrt(N + 0.1);
		dp[0] = 0;
		
		for(int i = 1; i <= lim; i++) {
			int num = i * i;
			
			for(int j = num; j <= N; j++) {
				dp[j] = Math.min(dp[j-num]+1, dp[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}