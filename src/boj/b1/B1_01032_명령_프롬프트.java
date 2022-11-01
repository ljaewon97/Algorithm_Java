package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_01032_명령_프롬프트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		char[] cmd = br.readLine().toCharArray();
		int len = cmd.length;
		
		for(int i = 0; i < N-1; i++) {
			String line = br.readLine();
			
			for(int j = 0; j < len; j++) {
				if(cmd[j] != '?' && cmd[j] != line.charAt(j)) cmd[j] = '?';
			}
		}
		
		System.out.println(new String(cmd));
	}
}
