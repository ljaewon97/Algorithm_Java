package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class S3_02659_십자카드_문제 {
	static boolean[] exist = new boolean[10000];
	static List<Integer> cross = new ArrayList<>();
	static int[] num = {1,2,3,4,5,6,7,8,9};
	static int[] result = new int[4];
	static int no;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		solve(0);
		
		Collections.sort(cross);
		
		int[] nums = new int[4];
		
		for(int i = 0; i < 4; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int t = 10000;
		
		for(int i = 0; i < 4; i++) {
			int x = 0;
			
			for(int j = 0; j < 4; j++) {
				x = x*10 + nums[(i+j)%4];
			}
			
			t = Math.min(t, x);
		}
		
		for(int i = 0; i < cross.size(); i++) {
			if(cross.get(i) == t) {
				System.out.println(i+1);
				return;
			}
		}
	}
	
	static void solve(int nth) {
		if(nth == 4) {
			int min = 10000;
			
			for(int i = 0; i < 4; i++) {
				int x = 0;
				
				for(int j = 0; j < 4; j++) {
					x = x*10 + result[(i+j)%4];
				}
				
				min = Math.min(min, x);
			}
			
			if(!exist[min]) {
				cross.add(min);
				exist[min] = true;
			}
			
			return;
		}
		
		for(int i = 1; i <= 9; i++) {
			result[nth] = i;
			solve(nth+1);
		}
	}
}
