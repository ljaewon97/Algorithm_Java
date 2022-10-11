package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P4_13506_카멜레온_부분_문자열 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		int len = S.length();
		
		int[] pi = prefixFunction(S);
		
		String ans = null;
		
		for(int i = Math.max(1, len-pi[len-1]); i < len; i++) {
			if(S.charAt(0) != S.charAt(i)) continue;
			
			String cur = S.substring(i);
			int cnt = kmp(S, cur);
			
			if(cnt > 2) {
				ans = cur;
				break;
			}
		}
		
		System.out.println(ans == null ? -1 : ans);
	}
	
	static int[] prefixFunction(String S) {
		int len = S.length();
		int[] pi = new int[len];
		int j = 0;
		
		for(int i = 1; i < len; i++) {
			while(j > 0 && S.charAt(i) != S.charAt(j)) j = pi[j-1];
			if(S.charAt(i) == S.charAt(j)) j++;
			pi[i] = j;
		}
		
		return pi;
	}
	
	static int kmp(String S, String T) {
		int slen = S.length();
		int tlen = T.length();
		int[] pi = prefixFunction(T);
		int cnt = 0, j = 0;
		boolean ok = false;
		
		for(int i = 0; i < slen; i++) {
			while(j > 0 && S.charAt(i) != T.charAt(j)) j = pi[j-1];
			if(S.charAt(i) == T.charAt(j)) {
				if(j == tlen-1) {
					if(i-tlen+1 == 0) ok = true;
					cnt++;
					j = pi[j];
				}
				else j++;
			}
		}
		
		return ok ? cnt : 0;
	}
}
