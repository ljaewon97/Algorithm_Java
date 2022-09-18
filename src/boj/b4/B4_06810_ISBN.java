package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_06810_ISBN {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int ans = 91;
		
		for(int i = 0; i < 3; i++) {
			ans += Integer.parseInt(br.readLine()) * (i % 2 == 0 ? 1 : 3);
		}
		
		System.out.printf("The 1-3-sum is %d\n", ans);
	}
}
