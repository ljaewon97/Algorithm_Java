package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4_10797_10부제 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int D = Integer.parseInt(br.readLine());
		int ans = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 5; i++) {
			if(Integer.parseInt(st.nextToken()) == D) ans++;
		}
		
		System.out.println(ans);
	}
}
