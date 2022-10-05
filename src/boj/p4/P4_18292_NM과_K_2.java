package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class P4_18292_NM과_K_2 {
	static Map<Integer, Integer> map1 = new HashMap<>();
	static Map<Integer, Integer> map2 = new HashMap<>();
	static int[][][] temp, dp;
	static int[][] arr;
	static boolean[] result;
	static final int MIN = -1000001;
	static int N, M, K, C, code = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = (int) Math.pow(2, M) + 1;
		
		arr = new int[N][M];
		temp = new int[N][C][2];
		result = new boolean[M];
		
		map1.put(0, 0);
		map2.put(0, 0);
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		recur(0, 0, 0);
		
		dp = new int[N][code][K+1];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < code; j++) {
				Arrays.fill(dp[i][j], MIN);
			}
		}
		
		int ans = MIN;
		
		for(int j = 0; j < code; j++) {
			if(temp[0][j][1] > K) continue;
			dp[0][j][temp[0][j][1]] = temp[0][j][0];
			if(temp[0][j][1] == K) ans = Math.max(ans, dp[0][j][temp[0][j][1]]);
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < code; j++) {
				int jbit = map2.get(j);
				for(int k = 0; k < code; k++) {
					int kbit = map2.get(k);
					if((jbit & kbit) == 0) {
						int ea = temp[i][j][1];
						for(int l = 0; l <= K; l++) {
							if(ea + l > K) continue;
							dp[i][j][ea+l] = Math.max(dp[i][j][ea+l], dp[i-1][k][l] + temp[i][j][0]);
							if(ea + l == K) ans = Math.max(ans, dp[i][j][ea+l]);
						}
					}
				}
			}
		}

		System.out.println(ans);
	}
	
	static void recur(int nth, int idx, int cnt) {
		if(nth == M) {
			if(!map1.containsKey(idx)) {
				map1.put(idx, code);
				map2.put(code, idx);
			
				for(int r = 0; r < N; r++) {
					int sum = 0;
					
					for(int c = 0; c < M; c++) {
						if(result[c]) sum += arr[r][c];
					}
					
					temp[r][code][0] = sum;
					temp[r][code][1] = cnt;
				}
				
				code++;
			}
			
			return;
		}
		
		if(nth == 0 || !result[nth-1]) {
			result[nth] = true;
			recur(nth+1, idx+(1<<nth), cnt+1);
		}
		result[nth] = false;
		recur(nth+1, idx, cnt);
	}
}
