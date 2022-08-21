package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class P4_01422_숫자의_신 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		String[] nums = new String[K];
		
		for(int i = 0; i < K; i++) {
			nums[i] = br.readLine();
		}
		
		Arrays.sort(nums, new Comparator<String>() {
			public int compare(String o1, String o2) {
				int l1 = o1.length();
				int l2 = o2.length();
				int i = 0;
				
				while(i < l1 + l2) {
					char v1 = i < l1 ? o1.charAt(i) : o2.charAt(i-l1);
					char v2 = i < l2 ? o2.charAt(i) : o1.charAt(i-l2);
					
					if(v1 != v2) {
						return v2 - v1;
					}
					
					i++;
				}
				
				return 0;
			}
		});
		
		String longest = nums[0];
		int idx = 0;
		
		for(int i = 0; i < K; i++) {
			if(nums[i].length() > longest.length()) {
				longest = nums[i];
				idx = i;
			}
		}
		
		for(int i = 0; i < K; i++) {
			if(i == idx) {
				for(int j = 0; j < N-K+1; j++) {
					sb.append(longest);
				}
			}
			else {
				sb.append(nums[i]);
			}	
		}
		
		System.out.println(sb);
	}
}
