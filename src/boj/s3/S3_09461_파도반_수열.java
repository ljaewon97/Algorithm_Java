package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_09461_파도반_수열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		long[] P = new long[101];
		
		P[1] = 1; P[2] = 1; P[3] = 1; P[4] = 2; P[5] = 2;
		for(int i = 6; i <= 100; i++) {
			P[i] = P[i-1] + P[i-5];
		}
			
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(P[N]).append("\n");
		}
		
		System.out.println(sb);
	}
}
