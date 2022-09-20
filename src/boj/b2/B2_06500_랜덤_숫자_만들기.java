package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B2_06500_랜덤_숫자_만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			int N = Integer.parseInt(br.readLine());
			int cnt = 1;
			
			if(N == 0) break;
			
			Set<Integer> set = new HashSet<>();
			
			while(true) {
				set.add(N);
				
				N = N * N;
				N /= 100;
				N = N % 10000;
				
				if(set.contains(N)) break;
				cnt++;
			}
			
			sb.append(cnt).append("\n");
		}
		
		System.out.println(sb);
	}
}
