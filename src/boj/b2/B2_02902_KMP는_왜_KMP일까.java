package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_02902_KMP는_왜_KMP일까 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("-");
		StringBuilder sb = new StringBuilder();
		for(String word: input) {
			sb.append(word.charAt(0));
		}
		System.out.println(sb);
	}
}
