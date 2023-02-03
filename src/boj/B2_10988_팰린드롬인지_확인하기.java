package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_10988_팰린드롬인지_확인하기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(check(br.readLine()));
	}
	
	static int check(String str) {
		for(int i = 0; i < str.length()>>1; ++i) {
			if(str.charAt(i) != str.charAt(str.length()-1-i)) return 0;
		}
		
		return 1;
	}
}
