package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S2_18353_병사_배치하기_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		int[] dp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, 1);
		int ans = 1;
		
		for(int i = 1; i < N; i++) {
			for(int j = i-1; j >= 0; j--) {
				if(arr[j] > arr[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
					ans = Math.max(ans, dp[i]);
				}
			}
		}
		
		System.out.println(N-ans);
	}
}
