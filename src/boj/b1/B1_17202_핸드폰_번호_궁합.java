package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_17202_핸드폰_번호_궁합 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] arr = new int[16];
		
		String A = br.readLine();
		String B = br.readLine();
		
		for(int i = 0; i < 8; i++) {
			arr[2*i] = A.charAt(i) - '0';
			arr[2*i+1] = B.charAt(i) - '0';
		}
		
		while(arr.length != 2) arr = match(arr);
		
		System.out.println(arr[0] + "" + arr[1]);
	}
	
	static int[] match(int[] arr) {
		int[] ret = new int[arr.length-1];
		
		for(int i = 0; i < arr.length-1; i++) {
			ret[i] = (arr[i] + arr[i+1]) % 10;
		}
		
		return ret;
	}
}
