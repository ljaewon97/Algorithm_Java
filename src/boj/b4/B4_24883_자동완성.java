package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_24883_자동완성 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		System.out.println(s.equals("N") || s.equals("n") ? "Naver D2" : "Naver Whale");
	}
}
