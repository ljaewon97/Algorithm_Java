package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_02098_외판원_순회 {
	static final int INF = Integer.MAX_VALUE - 1000001;
	static int[][] W, dp;
	static int N, visitedAll;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		W = new int[N][N];
		visitedAll = (1 << N) - 1;
		dp = new int[N][visitedAll];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.fill(dp[i], -1);
		}
		
		System.out.println(travel(0, 1));
	}
	
	static int travel(int x, int bit) {
		if(bit == visitedAll) {
			if(W[x][0] == 0) return INF;
			else return W[x][0];
		}
		
		if(dp[x][bit] != -1) return dp[x][bit];
		
		dp[x][bit] = INF;
		
		for(int i = 0; i < N; i++) {
			if(W[x][i] == 0 || (bit & (1 << i)) != 0) continue;
			
			int nbit = bit | (1 << i);
			dp[x][bit] = Math.min(dp[x][bit], travel(i, nbit) + W[x][i]);
		}
		
		return dp[x][bit];
	}
}
