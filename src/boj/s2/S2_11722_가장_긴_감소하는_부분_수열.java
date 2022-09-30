package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_11722_가장_긴_감소하는_부분_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		dp[0] = 1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i < N; i++) {
			int max = 0;
			
			for(int j = i-1; j >= 0; j--) {
				if(arr[j] > arr[i] && dp[j] > max) {
					max = dp[j];
				}
			}
			
			dp[i] = max + 1;
		}
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
