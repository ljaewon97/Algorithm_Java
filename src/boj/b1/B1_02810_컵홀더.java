package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_02810_컵홀더 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String S = br.readLine();
		
		int[] count = new int[2];
		
		for(int i = 0; i < N; i++) {
			if(S.charAt(i) == 'S') count[0]++;
			else count[1]++;
		}
		
		count[1] /= 2;
		
		int temp = count[0] + count[1] + 1;
		System.out.println(Math.min(N, temp));
	}
}
