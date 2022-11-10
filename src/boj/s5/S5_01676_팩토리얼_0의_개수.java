package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class S5_01676_팩토리얼_0의_개수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N <= 4) {
			System.out.println(0);
			return;
		}
		
		BigInteger num = new BigInteger("24");
		for(int i = 5; i <= N; i++) {
			num = num.multiply(new BigInteger(String.valueOf(i)));
		}
		
		String result = num.toString();
		int ans = 0;
		
		for(int i = result.length()-1; i >= 0; i--) {
			if(result.charAt(i) == '0') ans++;
			else break;
		}
		
		System.out.println(ans);
	}
}
