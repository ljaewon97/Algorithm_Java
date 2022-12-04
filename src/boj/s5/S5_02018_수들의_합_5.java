package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_02018_수들의_합_5 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 0, t = 0;
		
		for(int i = 1; (t = i*(i+1)/2) <= N; i++) {
			if((N-t) % i == 0) ans++;
		}
		
		System.out.println(ans);
	}
}
