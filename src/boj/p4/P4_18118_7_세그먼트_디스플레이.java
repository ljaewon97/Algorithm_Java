package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P4_18118_7_세그먼트_디스플레이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			long[][] dp = new long[N+1][M];
			
			for(int i = 0; i <= N; i++) {
				Arrays.fill(dp[i], -1000000000);
			}
			
			dp[0][0] = 0;
			
			for(int i = 1; i <= N; i++) {
				for(int j = 0; j < M; j++) {
					for(int k = 0; k < 10; k++) {
						dp[i][(j*10+k)%M] = Math.max(dp[i][(j*10+k)%M], dp[i-1][j]*10+k);
					}
					
					dp[i][(j*100+11)%M] = Math.max(dp[i][(j*100+11)%M], dp[i-1][j]*100+11);
				}
			}
			
			sb.append(dp[N][0]).append("\n");
		}
		
		System.out.println(sb);
	}
}
