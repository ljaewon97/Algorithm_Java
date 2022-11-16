package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5_04354_문자열_제곱 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			char[] s = br.readLine().toCharArray();
			
			if(s[0] == '.') break;
			
			int l = s.length;
			int[] pi = new int[l];
			int j = 0;
			
			for(int i = 1; i < l; i++) {
				while(j > 0 && s[i] != s[j]) j = pi[j-1];
				if(s[i] == s[j]) j++;
				pi[i] = j;
			}
			
			sb.append(l % (l-pi[l-1]) == 0 ? l / (l-pi[l-1]) : 1).append("\n");
		}
		
		System.out.println(sb);
	}
}
