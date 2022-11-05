package boj.b5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B5_24736_Football_Scoring {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 2; i++) {
			st = new StringTokenizer(br.readLine());
			sb.append(6*Integer.parseInt(st.nextToken())+3*Integer.parseInt(st.nextToken())+2*Integer.parseInt(st.nextToken())+Integer.parseInt(st.nextToken())+2*Integer.parseInt(st.nextToken())).append(" ");
		}
		
		System.out.println(sb);
	}
}
