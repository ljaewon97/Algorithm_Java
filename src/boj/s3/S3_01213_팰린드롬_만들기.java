package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_01213_팰린드롬_만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String name = br.readLine();
		int len = name.length();
		
		int[] count = new int[26];
		
		for(int i = 0; i < len; i++) {
			count[name.charAt(i)-'A']++;
		}
		
		int cnt = 0, oddIdx = 0;
		
		for(int i = 0; i < 26; i++) {
			if(count[i] % 2 == 1) {
				cnt++;
				oddIdx = i;
			}
		}
		
		if(cnt > 1) {
			System.out.println("I'm Sorry Hansoo");
			return;
		}
		
		int idx = 0;
		char[] ans = new char[len];
		
		for(int i = 0; i < 26; i++) {
			while(count[i] >= 2) {
				ans[idx] = ans[len-1-idx] = (char) (65+i);
				idx++;
				count[i] -= 2;
			}
		}
		
		if(cnt == 1) ans[idx] = (char) (65+oddIdx);
		
		System.out.println(new String(ans));
	}
}
