package boj.s2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S2_12891_DNA_비밀번호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		char[] dna = br.readLine().toCharArray();
		int[] acgt = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			acgt[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] cnt = new int[4];
		
		for(int i = 0; i < P; i++) {
			cnt[code(dna[i])]++;
		}
		
		for(int i = P; i <= S; i++) {
			boolean check = true;
			for(int j = 0; j < 4; j++) {
				if(cnt[j] < acgt[j]) {
					check = false;
					break;
				}
			}
			
			if(check) {
				ans++;
			}
			
			if(i == S) break;
			
			cnt[code(dna[i])]++;
			cnt[code(dna[i-P])]--;
		}
		
		System.out.println(ans);
	}
	
	static int code(char c) {
		int cur = 0;
		switch(c) {
		case 'A': cur = 0; break;
		case 'C': cur = 1; break;
		case 'G': cur = 2; break;
		case 'T': cur = 3; break;
		}
		
		return cur;
	}
}
