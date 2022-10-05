package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_18290_NM과_K_1 {
	static int[][] map;
	static int[] result;
	static int N, M, K, ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		result = new int[K];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0, 0);
		
		System.out.println(ans);
	}
	
	static void comb(int nth, int start, int sum) {
		if(nth == K) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int i = start; i < N*M; i++) {
			int r = i / M;
			int c = i % M;
			boolean flag = true;
			
			for(int j = 0; j < nth; j++) {
				int rr = result[j] / M;
				int rc = result[j] % M;
				
				if(Math.abs(r - rr) + Math.abs(c - rc) == 1) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				result[nth] = i;
				comb(nth+1, i+1, sum+map[r][c]);
			}
		}
	}
}
