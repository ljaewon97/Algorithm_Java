package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S2_17213_과일_서리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		int R = M - N;
		N = N + R - 1;
		
		if(N-R < R) R = N-R;
		
		long up = 1, down = 1;
		
		for(int i = 0; i < R; i++) {
			up *= N-i;
			if(up % (i+1) == 0) up /= i+1;
			else down *= i+1;
		}
		
		System.out.println(up/down);
	}
}
