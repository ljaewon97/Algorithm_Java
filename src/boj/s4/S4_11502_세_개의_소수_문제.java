package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_11502_세_개의_소수_문제 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		boolean[] prime = new boolean[1000];
		
		Arrays.fill(prime, true);
		
		prime[0] = prime[1] = false;
		
		for(int i = 2; i*i < 1000; i++) {
			for(int j = 2*i; j < 1000; j += i) {
				prime[j] = false;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			boolean flag = false;
			
			outer: for(int i = 2; i < K; i++) {
				if(!prime[i]) continue;
				for(int j = i; j < K; j++) {
					if(!prime[j]) continue;
					for(int k = j; k < K; k++) {
						if(!prime[k]) continue;
						
						if(i+j+k == K) {
							sb.append(String.format("%d %d %d\n", i, j, k));
							flag = true;
							break outer;
						}
					}
				}
			}
			
			if(!flag) sb.append("0\n");
		}
		
		System.out.println(sb);
	}
}
