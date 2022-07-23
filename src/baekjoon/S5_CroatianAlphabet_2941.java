package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_CroatianAlphabet_2941 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		int i = 0, ans = 0;
		
		while(i < word.length()) {
			if(i < word.length() - 1) {
				if(word.charAt(i+1) == '=' && (word.charAt(i) == 'c' || word.charAt(i) == 's' || word.charAt(i) == 'z')) {
					ans++;
					i += 2;
					continue;
				}
				else if(word.charAt(i+1) == '-' && (word.charAt(i) == 'c' || word.charAt(i) == 'd')) {
					ans++;
					i += 2;
					continue;
				}
				else if(word.charAt(i+1) == 'j' && (word.charAt(i) == 'l' || word.charAt(i) == 'n')) {
					ans++;
					i += 2;
					continue;
				}
			}
			
			if(i < word.length() - 2 && word.substring(i, i+3).equals("dz=")) {
				ans++;
				i += 3;
				continue;
			}
			
			ans++;
			i++;
		}
		
		System.out.println(ans);
	}
}