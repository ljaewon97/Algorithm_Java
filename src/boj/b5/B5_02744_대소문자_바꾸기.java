package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_02744_대소문자_바꾸기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String word = br.readLine();
		StringBuilder ans = new StringBuilder();
		
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) >= 'a') ans.append((char) (word.charAt(i) - 32));
			else ans.append((char) (word.charAt(i) + 32));
		}
		
		System.out.println(ans);
	}
}
