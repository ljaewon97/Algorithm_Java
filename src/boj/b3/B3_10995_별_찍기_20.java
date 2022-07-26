package boj.b3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_10995_별_찍기_20 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 1; i <= N; i++) {
			sb.append(i % 2 == 1 ? "" : " ");
			for(int j = 0; j < 2*N-1; j++) {
				sb.append(j % 2 == 0 ? "*" : " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}