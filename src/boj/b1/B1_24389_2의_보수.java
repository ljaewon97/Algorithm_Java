package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_24389_2의_보수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s1 = Integer.toBinaryString(N);
		String s2 = Integer.toBinaryString(-N);
		
		int cnt = 0;
		
		for(int i = 0; i < Math.min(s1.length(), s2.length()); ++i) {
			if(s1.charAt(s1.length()-1-i) != s2.charAt(s2.length()-1-i)) ++cnt;
		}
		
		System.out.println(cnt+Math.abs(s1.length()-s2.length()));
	}
}
