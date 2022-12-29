package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_01475_방_번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String N = br.readLine();
		
		int[] count = new int[10];
		
		for(int i = 0; i < N.length(); i++) {
			count[N.charAt(i)-'0']++;
		}
		
		int max = 0;
		
		for(int i = 0; i < 10; i++) {
			if(i != 6 && i != 9) max = Math.max(max, count[i]);
		}
		
		max = Math.max(max, (count[6]+count[9]+1)/2);
		System.out.println(max);
	}
}
