package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2_22966_가장_쉬운_문제를_찾는_문제 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		String easy = "";
		int min = 5;
		
		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int diff = Integer.parseInt(st.nextToken());
			if(diff < min) {
				min = diff;
				easy = name;
			}
		}
		
		System.out.println(easy);
	}
}
