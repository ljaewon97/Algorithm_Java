package boj.s5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S5_02238_경매 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int U = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] count = new int[U+1];
		String[] person = new String[U+1];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			int price = Integer.parseInt(st.nextToken());
			
			count[price]++;
			if(person[price] == null) person[price] = name;
		}
		
		int min = Integer.MAX_VALUE;
		int idx = -1;
		
		for(int i = 1; i <= U; i++) {
			if(count[i] != 0 && count[i] < min) {
				min = count[i];
				idx = i;
			}
		}
		
		System.out.println(person[idx] + " " + idx);
	}
}