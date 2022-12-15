package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_24082_Cube {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine());
		System.out.println(X*X*X);
	}
}
