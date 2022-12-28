package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_01371_가장_많은_글자 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int[] count = new int[26];
		
		String line = null;
		
		while((line = br.readLine()) != null) {
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) != ' ') count[line.charAt(i)-'a']++;
			}
		}
		
		int max = 0;
		
		for(int i = 0; i < 26; i++) {
			max = Math.max(max, count[i]);
		}
		
		for(int i = 0; i < 26; i++) {
			if(count[i] == max) sb.append((char) ('a'+i));
		}
		
		System.out.println(sb);
	}
}
