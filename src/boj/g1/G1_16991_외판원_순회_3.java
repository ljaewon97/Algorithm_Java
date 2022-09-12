package boj.g1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G1_16991_외판원_순회_3 {
	static final double INF = 100000;
	static double[][] W, dp;
	static int[][] coords;
	static int N, visitedAll;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		W = new double[N][N];
		visitedAll = (1 << N) - 1;
		dp = new double[N][visitedAll];
		coords = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coords[i][0] = Integer.parseInt(st.nextToken());
			coords[i][1] = Integer.parseInt(st.nextToken());
			
			Arrays.fill(dp[i], -1);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j) continue;
				W[i][j] = calcDist(coords[i][0], coords[i][1], coords[j][0], coords[j][1]);
			}
		}
		
		System.out.println(travel(0, 1));
	}
	
	static double travel(int x, int bit) {
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
	
	static double calcDist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2-x1) * (x2-x1) + (y2-y1) * (y2-y1));
	}
}
