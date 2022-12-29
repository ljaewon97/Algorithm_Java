package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class S5_04659_비밀번호_발음하기 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			String pwd = br.readLine();
			
			if(pwd.equals("end")) break;
			
			boolean flag = true;
			int cnt = 0;
			
			for(int i = 0; i < pwd.length(); i++) {
				if(aeiou(pwd.charAt(i))) cnt++;
			}
			
			if(cnt == 0) flag = false;
			if(!checkCon(pwd)) flag = false;
			if(!checkAdj(pwd)) flag = false;
			
			if(flag) sb.append(String.format("<%s> is acceptable.\n", pwd));
			else sb.append(String.format("<%s> is not acceptable.\n", pwd));
		}
		
		System.out.println(sb);
	}
	
	static boolean aeiou(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}
	
	static boolean checkCon(String str) {
		if(str.length() < 3) return true;
		
		for(int i = 0; i < str.length()-2; i++) {
			if(aeiou(str.charAt(i)) && aeiou(str.charAt(i+1)) && aeiou(str.charAt(i+2))) return false;
			if(!aeiou(str.charAt(i)) && !aeiou(str.charAt(i+1)) && !aeiou(str.charAt(i+2))) return false;
		}
		
		return true;
	}
	
	static boolean checkAdj(String str) {
		if(str.length() < 2) return true;
		
		for(int i = 0; i < str.length()-1; i++) {
			if(str.charAt(i) == str.charAt(i+1)) {
				if(str.charAt(i) == 'e' || str.charAt(i) == 'o') continue;
				return false;
			}
		}
		
		return true;
	}
}
