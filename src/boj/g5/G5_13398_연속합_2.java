package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_13398_연속합_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][2];
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = arr[0];
		dp[0][1] = arr[0];
		
		for(int i = 1; i < N; i++) {
			dp[i][0] = Math.max(dp[i-1][0] + arr[i], arr[i]);
			dp[i][1] = arr[i] > 0 ? Math.max(dp[i-1][0], dp[i-1][1]) : dp[i-1][0];
		}
		
		for(int[] row: dp) {
			System.out.println(Arrays.toString(row));
		}
		
		
	}
}
