package boj.b2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_05622_다이얼 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String word = br.readLine();
		int[] seconds = new int[] {3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,8,9,9,9,10,10,10,10};
		int ans = 0;
		
		for(int i = 0; i < word.length(); i++) {
			ans += seconds[word.charAt(i) - 65];
		}
		
		System.out.println(ans);
	}
}