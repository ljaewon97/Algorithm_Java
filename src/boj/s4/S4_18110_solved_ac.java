package boj.s4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class S4_18110_solved_ac {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] score = new int[n];
		
		for(int i = 0; i < n; i++) {
			score[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(score);
		
		int r = Math.round(n*0.15f);
		
		int sum = 0;
		
		for(int i = r; i < n-r; i++) {
			sum += score[i];
		}
		
		System.out.println(Math.round(1.0 * sum / (n-2*r)));
	}
}
