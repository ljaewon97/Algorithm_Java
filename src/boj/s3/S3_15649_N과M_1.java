package boj.s3;

import java.util.Scanner;

public class S3_15649_N과M_1 {
	static int[] result;
	static boolean[] visited;
	static int N, M;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		result = new int[M];
		visited = new boolean[N+1];
		
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
			if(visited[i]) continue;
			
			result[cnt] = i;
			visited[i] = true;
			perm(cnt + 1);
			visited[i] = false;
		}
	}
}
