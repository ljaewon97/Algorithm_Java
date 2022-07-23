package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1_SortingNumbers3_10989 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[10001];
		for(int i = 0; i < N; i++) {
			arr[Integer.parseInt(br.readLine())] += 1;
		}
		
		for(int i = 1; i < 10001; i++) {
			if(arr[i] != 0) {
				for(int j = 0; j < arr[i]; j++) {
					sb.append(i).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
}
