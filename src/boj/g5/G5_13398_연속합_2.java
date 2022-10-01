package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_13398_연속합_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][2];
		
		st = new StringTokenizer(br.readLine());
		int max = dp[1][0] = Integer.parseInt(st.nextToken());
		
		for(int i = 2; i <= N; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			dp[i][0] = Math.max(dp[i-1][0] + num, num);
			dp[i][1] = Math.max(dp[i-2][0], dp[i-1][1]) + num;
			
			max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
		}
		
		System.out.println(max);
	}
}
