package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_02576_홀수 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int sum = 0;
		int min = 100;
		
		for(int i = 0; i < 7; i++) {
			int n = Integer.parseInt(br.readLine());
			if(n % 2 == 1) {
				sum += n;
				min = Math.min(min, n);
			}
		}
		
		if(min == 100) System.out.println(-1);
		else {
			System.out.println(sum);
			System.out.println(min);
		}
	}
}
