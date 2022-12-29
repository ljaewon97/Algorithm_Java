package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S3_20310_타노스 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] S = br.readLine().toCharArray();
		boolean[] deleted = new boolean[S.length];
		int cnt0 = 0;
		
		for(int i = 0; i < S.length; i++) {
			if(S[i] == '0') cnt0++;
		}
		
		int cnt1 = (S.length - cnt0) / 2;
		cnt0 /= 2;
		int del0 = 0, del1 = 0;
		
		for(int i = 0; i < S.length; i++) {
			if(S[i] == '1') {
				deleted[i] = true;
				del1++;
				if(del1 == cnt1) break;
			}
		}
		
		for(int i = S.length-1; i >= 0; i--) {
			if(S[i] == '0') {
				deleted[i] = true;
				del0++;
				if(del0 == cnt0) break;
			}
		}
		
		for(int i = 0; i < S.length; i++) {
			if(!deleted[i]) sb.append(S[i]);
		}
		
		System.out.println(sb);
	}
}
