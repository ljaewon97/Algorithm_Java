package boj.s3;

import java.util.Scanner;

public class S3_15652_N과M_4 {
	static int[] result;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		result = new int[M];
		
		comb(0, 1);
		System.out.println(sb);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = start; i <= N; i++) {
			result[cnt] = i;
			comb(cnt + 1, i);
		}
	}
}
