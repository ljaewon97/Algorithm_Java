package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class G4_문자열폭발_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		char[] str = br.readLine().toCharArray();
		char[] exp = br.readLine().toCharArray();
		int strlen = str.length;
		int explen = exp.length;
		char lastChar = exp[explen - 1];
		
		for(int i = 0; i < strlen; i++) {
			stack.push(str[i]);
			
			if(stack.peek() == lastChar && stack.size() >= explen) {
				char[] temp = new char[explen];
				
				for(int j = 0; j < explen; j++) {
					temp[explen-1-j] = stack.pop();
				}
				
				boolean same = true;
				for(int j = 0; j < explen; j++) {
					if(temp[j] != exp[j]) {
						same = false;
						break;
					}
				}
				
				if(same) {
					continue;
				}
				else {
					for(int j = 0; j < explen; j++) {
						stack.push(temp[j]);
					}
				}
			}
		}
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {
			while(!stack.isEmpty()) {
				sb.append(stack.pop());
			}
			System.out.println(sb.reverse());
		}		
	}
}
