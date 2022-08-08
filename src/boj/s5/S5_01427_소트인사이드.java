package boj.s5;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class S5_01427_소트인사이드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Integer temp = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[10];
		int i = 0;
		while(temp > 0) {
			arr[i] = temp % 10;
			temp /= 10;
			i++;
		}
		arr = Arrays.copyOf(arr, i);
		Arrays.sort(arr, Collections.reverseOrder());
		for(int j = 0; j < arr.length; j++) {
			System.out.printf("%d", arr[j]);
		}
	}
}