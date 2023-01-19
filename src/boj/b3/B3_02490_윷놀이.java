package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_02490_윷놀이 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 3; i++) {
			int cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 4; j++) {
				if(st.nextToken().charAt(0) == '0') cnt++;
			}
			
			if(cnt == 0) sb.append("E");
			else sb.append((char)(64+cnt));
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
