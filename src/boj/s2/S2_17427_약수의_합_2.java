package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_17427_약수의_합_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		long ans = 0;
		
		for(int i = 1; i <= N; i++) {
			ans += i * (N/i);
		}
		
		System.out.println(ans);
	}
}
