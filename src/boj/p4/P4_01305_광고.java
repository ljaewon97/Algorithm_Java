package boj.p4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P4_01305_광고 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		char[] S = br.readLine().toCharArray();
		
		int[] pi = prefixFunction(S);
		
		System.out.println(L - pi[L-1]);
	}
	
	static int[] prefixFunction(char[] S) {
		int L = S.length;
		int[] pi = new int[L];
		
		int j = 0;
		for(int i = 1; i < L; i++) {
			while(j > 0 && S[j] != S[i]) j = pi[j-1];
			
			if(S[j] == S[i]) j++;
			
			pi[i] = j;
		}
		
		return pi;
	}
}
