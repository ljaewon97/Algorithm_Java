package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_25703_포인터_공부 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		
		sb.append("int a;\n");
		sb.append("int *ptr = &a;\n");
		
		for(int i = 0; i < N-1; i++) {
			sb.append("int ");
			
			for(int j = 0; j < i+2; j++) {
				sb.append("*");
			}
			
			sb.append("ptr").append(i+2).append(" = &ptr");
			if(i != 0) sb.append(i+1);
			sb.append(";\n");
		}
		
		System.out.println(sb);
	}
}
