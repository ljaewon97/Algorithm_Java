package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_16174_점프왕_쩰리_Large {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] jump = new int[N][N];
		boolean[][] dp = new boolean[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				jump[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0] = true;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dp[i][j]) {
					int x = jump[i][j];
					if(x == -1) continue;
					if(i+x < N) dp[i+x][j] = true;
					if(j+x < N) dp[i][j+x] = true;
				}
			}
		}
		
		System.out.println(dp[N-1][N-1] ? "HaruHaru" : "Hing");
	}
}
