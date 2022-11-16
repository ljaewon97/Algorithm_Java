package boj.p5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P5_12104_순환_순열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] a = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		int l = a.length;
		
		char[] A = new char[2*l-1];
		
		for(int i = 0; i < l; i++) {
			if(i != l-1) {
				A[i] = A[i+l] = a[i];
			}
			else A[i] = a[i];
		}
		
		int[] pi = new int[l];
		
		int j = 0;
		
		for(int i = 1; i < l; i++) {
			while(j > 0 && B[i] != B[j]) j = pi[j-1];
			if(B[i] == B[j]) j++;
			pi[i] = j;
		}
		
		int ans = 0;
		j = 0;
		
		for(int i = 0; i < 2*l-1; i++) {
			while(j > 0 && A[i] != B[j]) j = pi[j-1];
			if(A[i] == B[j]) {
				if(j == l-1) {
					ans++;
					j = pi[j];
				}
				else j++;
			}
		}
		
		System.out.println(ans);
	}
}
