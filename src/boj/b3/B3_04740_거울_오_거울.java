package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_04740_거울_오_거울 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String line = br.readLine();
			
			if(line.equals("***")) break;
			
			for(int i = line.length()-1; i >= 0; i--) {
				sb.append(line.charAt(i));
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
