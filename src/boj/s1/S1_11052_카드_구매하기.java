package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_11052_카드_구매하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] prices = new int[N+1];
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			prices[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++) {
				dp[j] = Math.max(dp[j-i] + prices[i], dp[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}
