package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class S2_15664_N과M_10 {
	static int N, M;
	static int[] nums, result;
	static StringBuilder sb = new StringBuilder();;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(nums);
		
		comb(0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start) {
		if(nth == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		int temp = 0;
		for(int i = start; i < N; i++) {
			if(temp != nums[i]) {
				result[nth] = nums[i];
				temp = nums[i];
				comb(nth+1, i+1);
			}
		}
	}
}
