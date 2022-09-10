package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5_01786_찾기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] T = br.readLine().toCharArray();
		char[] P = br.readLine().toCharArray();
		
		int n = T.length;
		int m = P.length;
		
		int[] pi = prefixFunction(P);
		
		int j = 0, ans = 0;
		
		for(int i = 0; i < n; i++) {
			while(j > 0 && T[i] != P[j]) j = pi[j-1];
			
			if(T[i] == P[j]) {
				if(j == m-1) {
					ans++;
					sb.append(i-m+2).append(" ");
					j = pi[j];
				}
				else {
					j++;
				}
			}
		}
		
		System.out.println(ans);
		System.out.println(sb);
	}
	
	static int[] prefixFunction(char[] P) {
		int m = P.length;
		int[] pi = new int[m];
		
		int j = 0;
		for(int i = 1; i < m; i++) {
			while(j > 0 && P[j] != P[i]) j = pi[j-1];
			
			if(P[j] == P[i]) j++;
			
			pi[i] = j;
		}
		
		return pi;
	}
}
