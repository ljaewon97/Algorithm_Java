package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_17484_진우의_달_여행_Small {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][][] dp = new int[N][M][3];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			int fuel = Integer.parseInt(st.nextToken());
			for(int j = 0; j < 3; j++) {
				dp[0][i][j] = fuel;
			}
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int fuel = Integer.parseInt(st.nextToken());
				dp[i][j][0] = j != M-1 ? Math.min(dp[i-1][j+1][1], dp[i-1][j+1][2]) + fuel : Integer.MAX_VALUE;
				dp[i][j][1] = Math.min(dp[i-1][j][0], dp[i-1][j][2]) + fuel;
				dp[i][j][2] = j != 0 ? Math.min(dp[i-1][j-1][0], dp[i-1][j-1][1]) + fuel : Integer.MAX_VALUE;
			}
		}
		
		int ans = Integer.MAX_VALUE;
		
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < 3; j++) {
				ans = Math.min(ans, dp[N-1][i][j]);
			}
		}
		
		System.out.println(ans);
	}
}
