package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_25793_초콜릿_피라미드 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		long[][] dp = new long[1000001][2];
		dp[1][0] = 1;
		
		for(int i = 2; i <= 1000000; i++) {
			dp[i][0] = dp[i-1][0] + (long)i*i + (long)(i-1)*(i-1);
			dp[i][1] = dp[i-1][1] + (long)2*i*(i-1);
		}

		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int diff = Math.abs(R-C);
			int min = Math.min(R, C);
			
			long white = dp[min][0] + (long)diff*min*min;
			long dark = dp[min][1] + (long)diff*min*min;
			
			sb.append(white).append(" ").append(dark).append("\n");
		}
		
		System.out.println(sb);
	}
}
