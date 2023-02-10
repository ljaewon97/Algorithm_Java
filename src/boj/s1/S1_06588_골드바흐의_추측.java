package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class S1_06588_골드바흐의_추측 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		boolean[] sieve = new boolean[1000001];
		
		for(int i = 2; i*i <= 1000000; i++) {
			if(sieve[i]) continue;
			for(int j = 2*i; j <= 1000000; j += i) {
				sieve[j] = true;
			}
		}
		
		List<Integer> prime = new ArrayList<>();
		
		for(int i = 3; i <= 1000000; i++) {
			if(!sieve[i]) prime.add(i);
		}
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			boolean gb = false;
			
			for(int i: prime) {
				int j = n-i;
				
				if(i > j) break;
				
				if(!sieve[j]) {
					sb.append(n).append(" = ").append(i).append(" + ").append(j).append("\n");
					gb = true;
					break;
				}
			}
			
			if(!gb) sb.append("Goldbach's conjecture is wrong.\n");
		}
		
		System.out.println(sb);
	}
}
