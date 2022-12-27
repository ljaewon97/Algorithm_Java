package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_11365_밀비_급일 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			if(line.equals("END")) break;
			StringBuilder sbLine = new StringBuilder(line);
			sb.append(sbLine.reverse()).append("\n");
		}
		
		System.out.println(sb);
	}
}
