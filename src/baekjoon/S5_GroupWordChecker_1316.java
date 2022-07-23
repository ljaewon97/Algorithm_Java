package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_GroupWordChecker_1316 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int ans = 0;
		
		for(int t = 0; t < N; t++) {
			int[] checker = new int[26];
			String word = br.readLine();
			char prev = word.charAt(0);
			checker[prev - 97] = 1;
			boolean isGroupWord = true;
			
			for(int i = 1; i < word.length(); i++) {
				if(word.charAt(i) != prev && checker[word.charAt(i) - 97] == 0) {
					checker[word.charAt(i) - 97] = 1;
				}
				else if(word.charAt(i) != prev && checker[word.charAt(i) - 97] == 1) {
					isGroupWord = false;
				}
				prev = word.charAt(i);
			}
			
			if(isGroupWord) ans++;
		}
		
		System.out.println(ans);
	}
}