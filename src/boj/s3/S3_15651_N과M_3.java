package boj.s3;

import java.util.Scanner;

public class S3_15651_N과M_3 {
	static int[] result;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		result = new int[M];
		
		perm(0);
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			
			result[cnt] = i;
			perm(cnt + 1);
		}
	}
}
