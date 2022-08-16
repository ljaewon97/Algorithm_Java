package boj.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class S3_18310_안테나 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] house = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(house);
		
		if(N % 2 == 1) {
			System.out.println(house[N/2]);
		}
		else {
			long sum1 = 0L, sum2 = 0L;
			int h1 = house[N/2-1], h2 = house[N/2];
			
			for(int i = 0; i < N; i++) {
				sum1 += Math.abs(house[i] - h1);
				sum2 += Math.abs(house[i] - h2);
			}
			
			if(sum1 <= sum2) {
				System.out.println(h1);
			}
			else {
				System.out.println(h2);
			}
		}
	}
}
