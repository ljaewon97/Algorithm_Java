package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S1_01342_행운의_문자열 {
	static char[] word, result;
	static boolean[] visited;
	static int[] fact, count;
	static int ans, len;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		len = S.length();
		word = new char[len];
		count = new int[26];
		result = new char[len];
		visited = new boolean[len];
		fact = new int[len+1];
		fact[0] = 1;
		
		for(int i = 1; i <= len; i++) {
			fact[i] = i * fact[i-1];
		}
		
		for(int i = 0; i < len; i++) {
			word[i] = S.charAt(i);
			count[S.charAt(i)-'a']++;
		}
		
		int div = 1;
		
		for(int i = 0; i < 26; i++) {
			if(count[i] > 0) div *= fact[count[i]];
		}
		
		solve(0);
		
		System.out.println(ans/div);
	}
	
	static void solve(int nth) {
		if(nth == len) {
			if(lucky()) ans++;
			return;
		}
		
		for(int i = 0; i < len; i++) {
			if(!visited[i]) {
				result[nth] = word[i];
				visited[i] = true;
				solve(nth+1);
				visited[i] = false;
			}
		}
	}
	
	static boolean lucky() {
		for(int i = 0; i < len-1; i++) {
			if(result[i] == result[i+1]) return false;
		}
		
		return true;
	}
}
