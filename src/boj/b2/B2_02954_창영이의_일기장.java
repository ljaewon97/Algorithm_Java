package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_02954_창영이의_일기장 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		while(st.hasMoreTokens()) {
			String word = st.nextToken();
			
			for(int i = 0; i < word.length(); ++i) {
				if(isVowel(word.charAt(i))) {
					sb.append(word.charAt(i));
					i += 2;
				}
				else sb.append(word.charAt(i));
			}
			
			sb.append(" ");
		}
		
		System.out.println(sb);
	}
	
	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
}
