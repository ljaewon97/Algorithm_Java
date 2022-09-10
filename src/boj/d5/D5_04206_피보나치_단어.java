package boj.d5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class D5_04206_피보나치_단어 {
	static String[] F = new String[101];
	static String head, tail1, tail2;
	static int F_len = 27;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		F[0] = "0";
		F[1] = "1";
		
		for(int i = 2; i < 28; i++) {
			F[i] = F[i-1] + F[i-2];
		}
		
		head = F[26].substring(0, 100000);
		tail1 = F[26].substring(F[26].length() - 100000);
		tail2 = F[27].substring(F[27].length() - 100000);
		
		int tc = 0;
		String line = null;
		
		while((line = br.readLine()) != null) {
			int n = Integer.parseInt(line);
			String p = br.readLine();
			tc++;
			
			if(p == null || p.equals("")) break;
			
			int plen = p.length();
			
			if(n > F_len) computeF(n);
			
			long[] dp = new long[n+1];
			int[] pi = prefixFunction(p);
			
			if(n < 28) {
				sb.append(String.format("Case %d: %d\n", tc, kmp(F[n], p, pi)));
				continue;
			}
			
			dp[26] = kmp(F[26], p, pi);
			dp[27] = kmp(F[27], p, pi);
			
			for(int i = 28; i <= n; i++) {
				dp[i] = kmp(F[i].substring(100001-plen, 99999+plen), p, pi) + dp[i-1] + dp[i-2];
			}
			
			sb.append(String.format("Case %d: %d\n", tc, dp[n]));
		}
		
		System.out.println(sb);
	}
	
	static void computeF(int n) {
		for(int i = F_len+1; i <= n; i++) {
			F[i] = (i % 2 == 0 ? tail2 : tail1) + head;
		}
		
		F_len = n;
	}
	
	static int[] prefixFunction(String p) {
		int l = p.length();
		int[] pi = new int[l];
		
		int j = 0;
		
		for(int i = 1; i < l; i++) {
			while(j > 0 && p.charAt(j) != p.charAt(i)) j = pi[j-1];
			
			if(p.charAt(j) == p.charAt(i)) j++;
			
			pi[i] = j;
		}
		
		return pi;
	}
	
	static int kmp(String fn, String p, int[] pi) {
		int res = 0;
		int n = fn.length();
		int m = p.length();
		
		if(m > n) return 0;
		
		int j = 0;
		
		for(int i = 0; i < n; i++) {
			while(j > 0 && fn.charAt(i) != p.charAt(j)) j = pi[j-1];
			
			if(fn.charAt(i) == p.charAt(j)) {
				if(j == m-1) {
					res++;
					j = pi[j];
				}
				else {
					j++;
				}
			}
		}
		
		return res;
	}
}