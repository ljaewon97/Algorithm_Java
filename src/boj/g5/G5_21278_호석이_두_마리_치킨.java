package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G5_21278_호석이_두_마리_치킨 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int INF = 1000000000;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] fw = new int[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(i != j) fw[i][j] = INF;
			}
		}
		
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			fw[A][B] = fw[B][A] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k]+fw[k][j]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int b1 = -1, b2 = -1;
		
		for(int i = 1; i <= N; i++) {
			for(int j = i+1; j <= N; j++) {
				int sum = 0;
				for(int k = 1; k <= N; k++) {
					sum += 2 * Math.min(fw[i][k], fw[j][k]);
				}
				
				if(sum < min) {
					min = sum;
					b1 = i;
					b2 = j;
				}
			}
		}
		
		System.out.printf("%d %d %d\n", b1, b2, min);
	}
}
