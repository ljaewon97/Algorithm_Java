package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G5_12919_A와_B_2 {
	static char[] S;
	static int L;
	static boolean ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		S = br.readLine().toCharArray();
		L = S.length;
		char[] T = br.readLine().toCharArray();
		
		solve(T);
		
		System.out.println(ans ? 1 : 0);
	}
	
	static void solve(char[] T) {
		if(ans) return;
		
		if(T.length == L) {
			if(same(S, T)) ans = true;
			return;
		}
		
		if(T[T.length-1] == 'A') solve(op1(T));
		if(T[0] == 'B') solve(op2(T));
	}
	
	static char[] op1(char[] T) {
		char[] ret = new char[T.length-1];
		
		for(int i = 0; i < T.length-1; ++i) {
			ret[i] = T[i];
		}
		
		return ret;
	}
	
	static char[] op2(char[] T) {
		char[] ret = new char[T.length-1];
		
		for(int i = 0; i < T.length-1; ++i) {
			ret[i] = T[T.length-1-i];
		}
		
		return ret;
	}
	
	static boolean same(char[] s1, char[] s2) {
		for(int i = 0; i < s1.length; ++i) {
			if(s1[i] != s2[i]) return false;
		}
		
		return true;
	}
}
