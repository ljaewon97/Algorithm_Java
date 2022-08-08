package boj.b3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_10990_별_찍기_15 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < N-i; j++) {
				sb.append(" ");
			}
			for(int j = 0; j < 2*i-1; j++) {
				sb.append((j == 0 || j == 2*i-2) ? "*" : " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}