package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_15312_이름_궁합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = {3, 2, 1, 2, 3, 3, 2, 3, 3, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1};
		
		String A = br.readLine();
		String B = br.readLine();
		
		int[][] res = new int[2*A.length()][];
		
		int idx = 0;
		int len = 2*A.length();
		
		res[idx] = new int[len];
		for(int i = 0; i < A.length(); i++) {
			res[idx][2*i] = arr[A.charAt(i)-'A'];
			res[idx][2*i+1] = arr[B.charAt(i)-'A'];
		}
		
		while(true) {
			--len;
			if(len == 1) break;
			++idx;
			
			res[idx] = new int[len];
			
			for(int i = 0; i < len; i++) {
				res[idx][i] = (res[idx-1][i] + res[idx-1][i+1]) % 10;
			}
		}
		
		System.out.print(res[idx][0]);
		System.out.print(res[idx][1]);
	}
}
