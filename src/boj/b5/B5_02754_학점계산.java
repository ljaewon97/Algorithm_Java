package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B5_02754_학점계산 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String grade = br.readLine();
		double ans = 0;
		
		ans += 69 - grade.charAt(0);
		
		if(ans == -1) {
			System.out.println("0.0");
			return;
		}
		
		if(grade.charAt(1) == '+') ans += 0.3;
		else if(grade.charAt(1) == '-') ans -= 0.3;
		
		System.out.println(ans);
	}
}
