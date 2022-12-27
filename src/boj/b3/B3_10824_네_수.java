package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3_10824_네_수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder A = new StringBuilder();
		StringBuilder B = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		A.append(st.nextToken()).append(st.nextToken());
		B.append(st.nextToken()).append(st.nextToken());
		System.out.println(Long.parseLong(A.toString())+Long.parseLong(B.toString()));
	}
}
