package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B3_26264_빅데이터_정보보호 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		String str = br.readLine();
		int sec = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == 's') sec++;
		}
		
		int data = N - sec;
		
		if(data > sec) System.out.println("bigdata?");
		else if(data < sec) System.out.println("security!");
		else System.out.println("bigdata? security!");
	}
}
