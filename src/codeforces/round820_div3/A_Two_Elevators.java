package codeforces.round820_div3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_Two_Elevators {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());
		
		while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			int e1 = Math.abs(a-1);
			int e2 = Math.abs(b-c) + Math.abs(c-1);
			
			if(e1 < e2) sb.append(1);
			else if(e1 > e2) sb.append(2);
			else sb.append(3);
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
