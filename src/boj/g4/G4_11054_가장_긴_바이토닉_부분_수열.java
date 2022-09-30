package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G4_11054_가장_긴_바이토닉_부분_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[][] dp = new int[N][2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0][0] = 1;
		dp[N-1][1] = 1;
		
		for(int i = 1; i < N; i++) {
			int max = 0;
			
			for(int j = i-1; j >= 0; j--) {
				if(arr[j] < arr[i] && dp[j][0] > max) max = dp[j][0];
			}
			
			dp[i][0] = max + 1;
		}
		
		for(int i = N-2; i >= 0; i--) {
			int max = 0;
			
			for(int j = i+1; j < N; j++) {
				if(arr[j] < arr[i] && dp[j][1] > max) max = dp[j][1];
			}
			
			dp[i][1] = max + 1;
		}
		
		int ans = 0;
		
		for(int i = 0; i < N; i++) {
			ans = Math.max(ans, dp[i][0] + dp[i][1] - 1);
		}
		
		System.out.println(ans);
	}
}
