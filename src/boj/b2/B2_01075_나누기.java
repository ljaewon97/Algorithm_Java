package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_01075_나누기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for(int i = 0; i < 100; i++) {
			N = (N/100) * 100 + i;
			if(N % F == 0) {
				ans = i;
				break;
			}
		}
		
		if(ans < 10) System.out.print(0);
		System.out.println(ans);
	}
}
