package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_27467_수학_퀴즈 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		int p = 0, q = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) {
			int A = Integer.parseInt(st.nextToken());
			int mod = A%3;
			
			if(mod == 0) ++q;
			else if(mod == 1) ++p;
			else {
				--p;
				--q;
			}
		}
		
		System.out.println(p + " " + q);
	}
}
