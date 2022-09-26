package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B4_25640_MBTI {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String mbti = br.readLine();
		int ans = 0;
		
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			if(mbti.equals(br.readLine())) ans++;
		}
		
		System.out.println(ans);
	}
}
