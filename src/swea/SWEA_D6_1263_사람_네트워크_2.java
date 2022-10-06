package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D6_1263_사람_네트워크_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		final int INF = 1000000000;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] graph = new int[N][N];
			int[][] dp = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					graph[i][j] = st.nextToken().charAt(0) - '0';
					if(i == j) dp[i][j] = 0;
					else dp[i][j] = graph[i][j] == 0 ? INF : graph[i][j];
				}
			}
			
			for(int k = 0; k < N; k++) {
				for(int i = 0; i < N; i++) {
					if(i == k) continue;
					for(int j = 0; j < N; j++) {
						if(i == j || j == k) continue;
						dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
					}
				}
			}
			
			int ans = Integer.MAX_VALUE;
			
			for(int i = 0; i < N; i++) {
				int temp = 0;
				
				for(int j = 0; j < N; j++) {
					temp += dp[i][j];
				}
				
				ans = Math.min(ans, temp);
			}
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
}
