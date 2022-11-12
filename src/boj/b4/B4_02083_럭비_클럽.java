package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4_02083_럭비_클럽 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int age = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			if(name.equals("#")) break;
			
			sb.append(name).append(" ");
			
			if(age > 17 || w >= 80) sb.append("Senior");
			else sb.append("Junior");
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
