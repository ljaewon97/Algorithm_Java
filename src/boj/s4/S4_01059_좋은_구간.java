package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S4_01059_좋은_구간 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int L = Integer.parseInt(br.readLine());
		
		int[] S = new int[L];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < L; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(S);
		
		int n = Integer.parseInt(br.readLine());
		int idx = -1;
		
		for(int i = 0; i < L; i++) {
			if(S[i] > n) {
				idx = i;
				break;
			}
			else if(S[i] == n) {
				System.out.println(0);
				return;
			}
		}
		
		int ans = 0;
		
		if(idx == 0) {
			for(int i = 1; i <= n; i++) {
				for(int j = Math.max(n, i+1); j < S[0]; j++) {
					ans++;
				}
			}
		}
		else {
			for(int i = S[idx-1]+1; i <= Math.min(n, S[idx]-1); i++) {
				for(int j = Math.max(n, i+1); j < S[idx]; j++) {
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
}
