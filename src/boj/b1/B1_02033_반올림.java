package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_02033_반올림 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		double N = Double.parseDouble(br.readLine());
		int con = 10;
		
		while(N > con) {
			N = Math.round(N/con) * con;
			con *= 10;
		}
		
		System.out.println((int) (N+0.1));
	}
}
