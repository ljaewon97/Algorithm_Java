package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_02502_떡_먹는_호랑이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] fibo = new int[D];
		fibo[0] = fibo[1] = 1;
		
		for(int i = 2; i < D; i++) {
			fibo[i] = fibo[i-2] + fibo[i-1];
		}
		
		int a = fibo[D-3];
		int b = fibo[D-2];
		
		int A = 1;
		
		while(true) {
			int temp = K - a*A;
			
			if(temp % b == 0) {
				int B = temp / b;
				
				sb.append(String.format("%d\n%d\n", A, B));
				break;
			}
			
			A++;
		}
		
		System.out.println(sb);
	}
}
