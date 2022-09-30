package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_02133_타일_채우기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N % 2 == 1) {
			System.out.println(0);
			return;
		}
		
		long[] dp = new long[N+1];
		dp[0] = 1;
		dp[2] = 3;
		
		for(int i = 4; i <= N; i += 2) {
			dp[i] = 4 * dp[i-2] - dp[i-4];
		}
		
		System.out.println(dp[N]);
	}
}
