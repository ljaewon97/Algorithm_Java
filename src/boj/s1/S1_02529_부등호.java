package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class S1_02529_부등호 {
	static List<String> list = new ArrayList<>();
	static boolean[] used = new boolean[10];
	static char[] op;
	static int[] nums;
	static int k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		op = new char[k];
		nums = new int[k+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			op[i] = st.nextToken().charAt(0);
		}
		
		solve(0);
		
		Collections.sort(list);
		
		System.out.println(list.get(list.size()-1));
		System.out.println(list.get(0));
	}
	
	static void solve(int nth) {
		if(nth == k+1) {
			StringBuilder sb = new StringBuilder();
			
			for(int i: nums) {
				sb.append(i);
			}
			
			list.add(sb.toString());
			
			return;
		}
		
		if(nth == 0) {
			for(int i = 0; i < 10; i++) {
				nums[0] = i;
				used[i] = true;
				solve(nth+1);
				used[i] = false;
			}
		}
		else if(op[nth-1] == '>') {
			for(int i = nums[nth-1]-1; i >= 0; i--) {
				if(!used[i]) {
					nums[nth] = i;
					used[i] = true;
					solve(nth+1);
					used[i] = false;
				}
			}
		}
		else {
			for(int i = nums[nth-1]+1; i < 10; i++) {
				if(!used[i]) {
					nums[nth] = i;
					used[i] = true;
					solve(nth+1);
					used[i] = false;
				}
			}
		}
	}
}
