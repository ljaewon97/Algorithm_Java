package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_01058_친구 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int INF = 1000000;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] fw = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < N; j++) {
				if(line.charAt(j) == 'Y') fw[i][j] = 1;
				else fw[i][j] = INF;
			}
		}
		
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					fw[i][j] = Math.min(fw[i][j], fw[i][k] + fw[k][j]);
				}
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < N; i++) {
			int sum = 0;
			
			for(int j = 0; j < N; j++) {
				if(i != j && fw[i][j] <= 2) sum++;
			}
			
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}
}
