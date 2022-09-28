package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_16194_카드_구매하기_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++) {
				dp[j] = Math.min(dp[j-i] + dp[i], dp[j]);
			}
		}
		
		System.out.println(dp[N]);
	}
}
