package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B1_02309_일곱_난쟁이 {
	static StringBuilder sb = new StringBuilder();
	static int[] height = new int[9];
	static int[] result = new int[7];
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++) {
			height[i] = Integer.parseInt(br.readLine());
		}
		
		recur(0, 0);
		
		System.out.println(sb);
	}
	
	static void recur(int nth, int start) {
		if(flag) return;
		
		if(nth == 7) {
			int sum = 0;
			
			for(int i = 0; i < 7; i++) {
				sum += result[i];
			}
			
			if(sum == 100) {
				flag = true;
				Arrays.sort(result);
				
				for(int i = 0; i < 7; i++) {
					sb.append(result[i]).append("\n");
				}
			}
			
			return;
		}
		
		for(int i = start; i < 9; i++) {
			result[nth] = height[i];
			recur(nth+1, i+1);
		}
	}
}
