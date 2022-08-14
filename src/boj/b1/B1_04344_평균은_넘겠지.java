package boj.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1_04344_평균은_넘겠지 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[] scores = new int[N];
			int sum = 0;
			
			for(int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
				sum += scores[i];
			}
			
			double avg = 1.0 * sum / N;
			int cnt = 0;
			
			for(int i = 0; i < N; i++) {
				if(scores[i] > avg) cnt++;
			}
			
			sb.append(String.format("%.3f%%\n", 100.0 * cnt / N));
		}
		
		System.out.println(sb);
	}
}
