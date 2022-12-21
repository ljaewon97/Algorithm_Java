package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S1_01660_캡틴_이다솜 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[150];
		int sum = 0, cnt = 0;
		
		for(int i = 1; i < 150; i++) {
			sum += i;
			arr[i] = arr[i-1] + sum;
			if(arr[i] > N) {
				cnt = i;
				break;
			}
		}
		
		int[] dp = new int[N+1];
		
		Arrays.fill(dp, 1000000);
		dp[0] = 0;
		
		for(int i = 1; i < cnt; i++) {
			for(int j = arr[i]; j <= N; j++) {
				dp[j] = Math.min(dp[j], dp[j-arr[i]] + 1);
			}
		}
		
		System.out.println(dp[N]);
	}
}
