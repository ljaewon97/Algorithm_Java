package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S2_15663_N과M_9 {
	static int N, M;
	static int[] nums, result;
	static boolean[] visited;
	static StringBuilder temp;
	static Set<String> set = new LinkedHashSet<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		result = new int[M];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		perm(0);
		
		StringBuilder sb = new StringBuilder();
		for(String s: set) {
			sb.append(s).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void perm(int nth) {
		if(nth == M) {
			temp = new StringBuilder();
			for(int i = 0; i < M; i++) {
				temp.append(result[i] + " ");
			}
			set.add(temp.toString().trim());
			return;
		}
		
		for(int i = 0; i < N; i++) {
			if(visited[i]) continue;
			
			result[nth] = nums[i];
			visited[i] = true;
			perm(nth+1);
			visited[i] = false;
		}
	}
}
