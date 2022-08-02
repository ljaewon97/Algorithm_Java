package boj.g4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class G4_문자열폭발_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] str = br.readLine().toCharArray();
		char[] exp = br.readLine().toCharArray();
		int[] v = new int[str.length];
		int i = 0, j = 0;
		
		while(i < str.length && i >= 0) {
			if(v[i] == 1) {
				i++;
				continue;
			}
			
			if(str[i] != exp[j]) {
				j = 0;
			}
			
			if(str[i] == exp[j]) {
				j++;
			}
			
			i++;
			
			if(j == exp.length) {
				int cnt = j;
				while(cnt > 0) {
					i--;
					if(v[i] == 1) {
						continue;
					}
					else {
						cnt--;
						v[i] = 1;
					}
				}
				
				for(int k = 0; k < exp.length - 1; k++) {
					if(i == 0) {
						break;
					}
					i--;
				}
				j = 0;
			}
		}
		
		for(int k = 0; k < str.length; k++) {
			if(v[k] == 0) {
				sb.append(str[k]);
			}
		}
		
		if(sb.length() == 0) {
			System.out.println("FRULA");
		}
		else {
			System.out.println(sb);
		}
				
	}
}
