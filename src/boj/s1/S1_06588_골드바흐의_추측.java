package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_06588_골드바흐의_추측 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		boolean[] prime = new boolean[1000001];
		
		for(int i = 2; i*i <= 1000000; i++) {
			if(prime[i]) continue;
			for(int j = 2*i; j <= 1000000; j += i) {
				prime[j] = true;
			}
		}
		
		while(true) {
			int n = Integer.parseInt(br.readLine());
			if(n == 0) break;
			boolean gb = false;
			
			for(int i = 3; i <= n/2; i += 2) {
				if(!prime[i] && !prime[n-i]) {
					sb.append(String.format("%d = %d + %d\n", n, i, n-i));
					gb = true;
					break;
				}
			}
			
			if(!gb) sb.append("Goldbach's conjecture is wrong.\n");
		}
		
		System.out.println(sb);
	}
}
