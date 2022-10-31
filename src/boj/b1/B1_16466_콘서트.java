package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B1_16466_콘서트 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] tickets = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			tickets[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(tickets);
		
		int ans = -1;
		
		for(int i = 0, j = 1; i < N; i++, j++) {
			if(tickets[i] != j) {
				ans = j;
				break;
			}
		}
		
		if(ans == -1) ans = tickets[N-1]+1;
		System.out.println(ans);
	}
}
