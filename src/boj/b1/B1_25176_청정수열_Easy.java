package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_25176_청정수열_Easy {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int ans = 1;
		
		for(int i = 1; i <= N; i++) {
			ans *= i;
		}
		
		System.out.println(ans);
	}
}
