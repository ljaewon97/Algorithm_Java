package boj.b3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B3_05073_삼각형과_세_변 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while(true) {
			int[] arr = new int[3];
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 3; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			if(arr[0] + arr[1] + arr[2] == 0) break;
			
			Arrays.sort(arr);
			
			if(arr[2] >= arr[0] + arr[1]) sb.append("Invalid\n");
			else if(arr[0] == arr[1] && arr[1] == arr[2]) sb.append("Equilateral\n");
			else if(arr[0] == arr[1] || arr[1] == arr[2]) sb.append("Isosceles\n");
			else sb.append("Scalene\n");
		}
		
		System.out.println(sb);
	}
}
