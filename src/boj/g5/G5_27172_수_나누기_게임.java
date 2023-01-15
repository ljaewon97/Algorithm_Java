package boj.g5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class G5_27172_수_나누기_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[] card = new int[1000001];
		Arrays.fill(card, -1);
		int max = 0;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] players = new int[N];
		int[] score = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int x = Integer.parseInt(st.nextToken());
			players[i] = x;
			card[x] = i;
			max = Math.max(max, x);
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 2*players[i]; j <= max; j += players[i]) {
				if(card[j] >= 0) {
					score[card[j]]--;
					score[i]++;
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(score[i]).append(" ");
		}
		
		System.out.println(sb);
	}
}
