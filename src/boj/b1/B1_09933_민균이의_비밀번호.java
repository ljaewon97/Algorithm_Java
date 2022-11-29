package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class B1_09933_민균이의_비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Set<String> set = new HashSet<>();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			set.add(br.readLine());
		}
		
		String ans = null;
		
		for(String word: set) {
			StringBuilder temp = new StringBuilder(word);
			
			if(set.contains(temp.reverse().toString())) {
				ans = word;
				break;
			}
		}
		
		System.out.println(ans.length() + " " + ans.charAt(ans.length()/2));
	}
}
