package boj.b4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B4_02845_파티가_끝나고_난_뒤 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int n = L*P;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 5; i++) {
			sb.append(Integer.parseInt(st.nextToken()) - n).append(" ");
		}
		
		System.out.println(sb);
	}
}
