package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_02624_동전_바꿔주기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] dp = new int[T+1];
		int[][] coins = new int[K][2];
		
		dp[0] = 1;
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			coins[i][0] = Integer.parseInt(st.nextToken());
			coins[i][1] = Integer.parseInt(st.nextToken());
			
			for(int j = T; j >= 0; j--) {
				for(int k = 1; k <= coins[i][1]; k++) {
					if(j-coins[i][0]*k < 0) break;
					dp[j] += dp[j-coins[i][0]*k];
				}
			}
		}
		
		System.out.println(dp[T]);
	}
}
