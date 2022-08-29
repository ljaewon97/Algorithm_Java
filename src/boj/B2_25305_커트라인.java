package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2_25305_커트라인 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Integer[] scores = new Integer[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(scores, (o1, o2) -> o2 - o1);
		
		System.out.println(scores[K-1]);
	}
}
