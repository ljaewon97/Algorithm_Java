package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_12871_무한_문자열 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String t = br.readLine();
		
		StringBuilder S = new StringBuilder();
		for(int i = 0; i < t.length(); i++) {
			S.append(s);
		}
		
		StringBuilder T = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			T.append(t);
		}
		
		System.out.println(S.toString().equals(T.toString()) ? 1 : 0);
	}
}
