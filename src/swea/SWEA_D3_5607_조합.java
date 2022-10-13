package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_D3_5607_조합 {
	static final long MOD = 1234567891;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		long[] fact = new long[1000001];
		fact[1] = 1;
		
		for(int i = 2; i <= 1000000; i++) {
			fact[i] = (i * fact[i-1]) % MOD;
		}
		
		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			
			long ans = (fact[N] % MOD) * (power(fact[R] * fact[N-R] % MOD, MOD-2)) % MOD;
			
			sb.append(String.format("#%d %d\n", t, ans));
		}
		
		System.out.println(sb);
	}
	
	static long power(long a, long b) {
		if(b == 0) return 1;
		if(b == 1) return a;
		if(b % 2 == 0) {
			long temp = power(a, b/2) % MOD;
			return temp * temp % MOD;
		}
		else {
			long temp = power(a, b-1) % MOD;
			return temp * a % MOD;
		}
	}
}
