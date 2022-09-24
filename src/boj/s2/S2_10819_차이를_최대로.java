package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_10819_차이를_최대로 {
	static int[] arr, result;
	static boolean[] visited;
	static int N, ans = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		result = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(0);
		
		System.out.println(ans);
	}
	
	static void perm(int nth) {
		if(nth == N) {
			int temp = 0;
			
			for(int i = 0; i < N-1; i++) {
				temp += Math.abs(result[i] - result[i+1]);
			}
			
			ans = Math.max(ans, temp);
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(!visited[i]) {
				result[nth] = arr[i];
				visited[i] = true;
				perm(nth+1);
				visited[i] = false;
			}
		}
	}
}
