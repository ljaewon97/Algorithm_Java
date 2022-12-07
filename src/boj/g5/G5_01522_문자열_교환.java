package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_01522_문자열_교환 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] S = br.readLine().toCharArray();
		
		int cnta = 0;
		
		for(int i = 0; i < S.length; i++) {
			if(S[i] == 'a') cnta++;
		}
		
		int a = 0, b = 0;
		
		for(int i = 0; i < cnta; i++) {
			if(S[i] == 'a') a++;
			else b++;
		}
		
		int min = b;
		
		for(int i = 0; i < S.length; i++) {
			if(S[i] == 'a') a--;
			else b--;
			
			if(S[(i+cnta)%S.length] == 'a') a++;
			else b++;
			
			min = Math.min(min, b);
		}
		
		System.out.println(min);
	}
}
