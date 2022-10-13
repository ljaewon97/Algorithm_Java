package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P5_11402_이항_계수_4 {
	static long N, K;
	static int M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Long.parseLong(st.nextToken());
		K = Long.parseLong(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] fact = new int[2001];
		fact[0] = 1;
		
		for(int i = 1; i <= 2000; i++) {
			fact[i] = (i * fact[i-1]) % M;
		}
		
		long ans = 1;
		
		while(N != 0 && K != 0) {
			int n = (int) (N % M);
			int k = (int) (K % M);
			
			if(n == 0 && k == 0) {
				N /= M;
				K /= M;
				continue;
			}
			
			if(n < k) {
				ans = 0;
				break;
			}
			
			ans = ((fact[n] % M) * power(fact[k] * fact[n-k] % M, M-2) % M) * ans % M;
			
			N /= M;
			K /= M;
		}
		
		System.out.println(ans);
	}
	
	static long power(long a, long b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		if(b % 2 == 0) {
			long temp = power(a, b/2) % M;
			return temp * temp % M;
		}
		else {
			long temp = power(a, b-1) % M;
			return a * temp % M;
		}
	}
}
