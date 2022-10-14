package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S3_11051_이항_계수_2 {
	static final int MOD = 10007;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] fact = new int[N+1];
		fact[0] = 1;
		
		for(int i = 1; i <= N; i++) {
			fact[i] = (i * fact[i-1]) % MOD;
		}
		
		int ans = (fact[N] % MOD) * power(fact[K] * fact[N-K] % MOD, MOD-2) % MOD;
		
		System.out.println(ans);
	}
	
	static int power(int a, int b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		if(b % 2 == 0) {
			int temp = power(a, b/2);
			return temp * temp % MOD;
		}
		else {
			int temp = power(a, b-1);
			return a * temp % MOD;
		}
	}
}
