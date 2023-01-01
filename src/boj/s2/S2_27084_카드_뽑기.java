package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_27084_카드_뽑기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		final int MOD = 1000000007;
		int N = Integer.parseInt(br.readLine());
		
		int[] count = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			count[Integer.parseInt(st.nextToken())]++;
			
		}
		
		long a = 1;
		int b = 0;
		
		for(int i = 1; i <= N; i++) {
			if(count[i] >= 2) {
				a = (a * (count[i]+1)) % MOD;
				b += count[i];
			}
		}
		
		long m = 1;
		
		for(int i = 1; i <= N-b; i++) {
			m = (m * 2) % MOD;
		}
		
		long ans = (m * a - 1) % MOD;
		System.out.println(ans);
	}
}
