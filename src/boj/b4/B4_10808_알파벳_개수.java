package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_10808_알파벳_개수 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String S = br.readLine();
		int[] count = new int[26];
		
		for(int i = 0; i < S.length(); i++) {
			count[S.charAt(i)-'a']++;
		}
		
		for(int i = 0; i < 26; i++) {
			sb.append(count[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
