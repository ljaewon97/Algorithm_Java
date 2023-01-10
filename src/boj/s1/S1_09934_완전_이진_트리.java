package boj.s1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S1_09934_완전_이진_트리 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int K = Integer.parseInt(br.readLine());
		
		int[] arr = new int[(1<<K)-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= K; i++) {
			for(int j = (1<<(K-i))-1; j < (1<<K)-1; j += (1<<(K-i+1))) {
				sb.append(arr[j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
