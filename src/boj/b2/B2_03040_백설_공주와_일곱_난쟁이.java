package boj.b2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2_03040_백설_공주와_일곱_난쟁이 {
	static int[] arr, result;
	static int sum = -100;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new int[9];
		
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
		}
		
		result = new int[2];
		comb(0, 0);
		
		System.out.println(sb);
	}
	
	static void comb(int nth, int start) {
		if(nth == 2) {
			if(arr[result[0]] + arr[result[1]] == sum) {
				for(int i = 0; i < 9; i++) {
					if(i == result[0] || i == result[1]) continue;
					sb.append(arr[i]).append("\n");
				}
			}
			return;
		}
		
		for(int i = start; i < 9; i++) {
			result[nth] = i;
			comb(nth+1, i+1);
		}
	}
}
