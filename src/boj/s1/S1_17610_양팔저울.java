package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_17610_양팔저울 {
	static int[] arr;
	static boolean[] ans;
	static int k;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k = Integer.parseInt(br.readLine());
		
		arr = new int[k];
		int sum = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i];
		}
		
		ans = new boolean[sum+1];
		
		solve(0, 0, 0);
		
		int cnt = 0;
		
		for(int i = 1; i <= sum; ++i) {
			if(!ans[i]) ++cnt;
		}
		
		System.out.println(cnt);
	}
	
	static void solve(int nth, int left, int right) {
		if(nth == k) {
			ans[Math.abs(left-right)] = true;
			return;
		}
		
		solve(nth+1, left+arr[nth], right);
		solve(nth+1, left, right+arr[nth]);
		solve(nth+1, left, right);
	}
}
