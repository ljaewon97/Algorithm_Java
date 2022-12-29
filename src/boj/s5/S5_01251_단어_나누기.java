package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_01251_단어_나누기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] word = br.readLine().toCharArray();
		String ans = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
		
		for(int i = 0; i < word.length; i++) {
			for(int j = i+1; j < word.length-1; j++) {
				char[] temp = new char[word.length];
				int idx = 0;
				for(int k = i; k >= 0; k--) temp[idx++] = word[k];
				for(int k = j; k >= i+1; k--) temp[idx++] = word[k];
				for(int k = word.length-1; k >= j+1; k--) temp[idx++] = word[k];
				String tempStr = new String(temp);
				if(tempStr.compareTo(ans) < 0) ans = tempStr;
			}
		}
		
		System.out.println(ans);
	}
}
